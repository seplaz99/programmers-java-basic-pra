# basic-board-token

세션 기반이었던 게시판 프로젝트를 **JWT(Access Token + Refresh Token) 기반 인증**으로 리팩토링한 프로젝트입니다. 이 문서는 그중에서도 **토큰이 어떻게 발급되고, 어디에 저장되고, 어떻게 검증/재발급/폐기되는지**에 초점을 둡니다.

## 왜 세션이 아니라 토큰인가

세션 방식은 서버가 로그인한 사용자 정보를 메모리(세션 저장소)에 들고 있어야 합니다. 서버를 여러 대로 늘리면 "어느 서버에 로그인했는지" 공유해야 하는 문제가 생깁니다.

토큰 방식은 서버가 아무것도 기억하지 않습니다(Stateless). 사용자 정보 자체를 토큰 안에 서명해서 담아 클라이언트에게 들려 보내고, 요청이 올 때마다 그 토큰의 서명만 검증합니다. 그래서 이 프로젝트는 `SecurityConfig`에서 세션 생성 정책을 아예 꺼버립니다.

```java
.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
```

## 토큰을 두 개로 나눈 이유

토큰 하나로만 처리하면 딜레마가 생깁니다.

- 유효기간을 **길게** 잡으면: 탈취당했을 때 오래 악용된다.
- 유효기간을 **짧게** 잡으면: 사용자가 몇 분마다 재로그인해야 해서 불편하다.

그래서 역할을 나눴습니다.

| | Access Token | Refresh Token |
|---|---|---|
| 용도 | 매 API 요청마다 신원 증명 | Access Token 재발급 전용 |
| 유효기간 | 2시간 (`jwt.access-token-validity`) | 7일 (`jwt.refresh-token-validity`) |
| 저장 위치 | 프론트 `localStorage` | `HttpOnly` 쿠키 |
| 노출 범위 | 모든 `/api/**` 요청 헤더 | `/api/tokens/refresh`, `/api/tokens/logout` 호출 시 브라우저가 자동 첨부 |
| JS로 값 접근 가능? | 가능 (XSS에 취약할 수 있음) | 불가능 (`HttpOnly`) |

Access Token은 자주 쓰이니 짧게, 대신 만료돼도 Refresh Token으로 조용히 재발급받아 사용자는 로그인 상태가 유지된다고 느낍니다. Refresh Token은 오래 살지만 `HttpOnly` 쿠키에만 있어서 JS(=XSS 공격)로 훔쳐갈 수 없고, 아주 가끔(재발급 시점)만 서버로 전송됩니다.

## 토큰 안에 뭐가 들어있나

`TokenProvider.makeToken()`이 서명해서 만드는 JWT의 payload:

```json
{
  "iss": "test@naver.com",   // 발급자 (jwt.issuer)
  "iat": 1735...,            // 발급 시각
  "exp": 1735...,            // 만료 시각
  "sub": "hong",             // subject = 로그인 아이디(userId)
  "id": 3,                   // 회원 PK
  "name": "홍길동"            // 이름 (화면 표시용)
}
```

서명은 `HS512`, 비밀키는 `jwt.secret-key`(Base64) 하나를 Access/Refresh 양쪽에 공용으로 씁니다. 비밀번호(해시조차도)는 토큰에 절대 담지 않습니다.

## 전체 라이프사이클

### 1) 로그인 — 최초 발급

```
POST /api/members/login  {username, password}
  → MemberService.login()이 AuthenticationManager에 위임
  → DaoAuthenticationProvider가 MemberDetailsService로 회원 조회 + PasswordEncoder로 대조
  → 성공 시 TokenService.issueTokenWithRefreshCookie() 호출
      - Access Token, Refresh Token 둘 다 생성 (TokenProvider.generateToken)
      - Refresh Token은 CookieUtil.addCookie()로 Set-Cookie 헤더에 실림 (HttpOnly)
      - Access Token은 응답 바디(LoginResponseDto.accessToken)로 내려감
  → 프론트(auth.js)가 응답의 accessToken을 localStorage에 저장
```

### 2) 이후 요청 — 검증

```
프론트 auth.js: $.ajaxSetup 이 모든 /api/** 요청에
  Authorization: Bearer <accessToken> 헤더를 자동으로 붙인다
       ↓
TokenAuthenticationFilter (OncePerRequestFilter, UsernamePasswordAuthenticationFilter 앞단에 배치)
  - 헤더에서 토큰 추출 (resolveToken)
  - TokenProvider.validateToken() 으로 서명/만료 검증
    · VALID   → 토큰 클레임으로 Member를 복원하고 SecurityContext에 인증 정보 세팅
    · EXPIRED → 즉시 401 응답, 필터 체인 중단
    · INVALID → 인증 세팅 없이 그냥 통과 (뒤의 authorizeHttpRequests가 미인증으로 걸러 401 처리)
       ↓
컨트롤러의 @AuthenticationPrincipal CustomUserDetails principal 로 "지금 이 요청을 보낸 사용자"를 받는다
  (BoardApiController.saveBoard/updateBoard/deleteBoard, CommentApiController.addComment 등)
```

여기서 중요한 설계 포인트: **인가(작성자 검증)에 쓰이는 사용자 식별값은 클라이언트가 보낸 값이 아니라, 이 필터가 토큰에서 복원한 값**입니다. 게시글 작성/수정/삭제 시 `dto.getUserId()`를 받지 않고 `principal.getUsername()`을 쓰는 이유가 이것입니다 — 그렇지 않으면 누구나 요청 바디의 아이디만 바꿔서 남의 이름으로 글을 쓸 수 있게 됩니다.

### 3) Access Token 만료

```
GET /api/boards  (만료된 Access Token으로 요청)
  → TokenAuthenticationFilter가 EXPIRED 판단 → 401
       ↓
프론트 auth.js
  → 전역 ajaxError 핸들러가 401을 감지
  → POST /api/tokens/refresh (쿠키의 Refresh Token은 브라우저가 자동 첨부, 헤더 안 붙임)
       ↓
TokenService.refresh()
  - 쿠키에서 Refresh Token 추출
  - validateToken() 으로 검증
  - 유효하면 Access/Refresh Token을 둘 다 새로 발급 (Refresh Token도 갱신 = 슬라이딩)
       ↓
성공 시: 새 accessToken을 응답 바디로 받아 localStorage 갱신
실패 시(Refresh Token도 만료/없음): /members/login 으로 이동
```

페이지에 처음 들어올 때도 같은 로직을 탑니다: `auth.js`의 `requireLogin()`이 `localStorage`에 유효해 보이는 Access Token이 없으면, 곧바로 로그인 페이지로 보내기 전에 **먼저 `/api/tokens/refresh`를 한 번 시도**합니다. 브라우저를 새로 열어도 Refresh Token 쿠키(7일)가 살아있으면 재로그인 없이 이어서 쓸 수 있게 하기 위함입니다.

### 4) 로그아웃 — 폐기

```
POST /api/tokens/logout
  → CookieUtil.deleteCookie() 로 Refresh Token 쿠키 삭제 (maxAge=0)
프론트
  → localStorage의 Access Token도 함께 제거
  → /members/login 으로 이동
```

주의: 로그아웃해도 그 시점까지 이미 발급된 Access Token 자체는 (탈취당했다면) 만료 시각까지는 여전히 유효합니다. Access Token 유효기간을 2시간으로 짧게 잡은 것이 사실상의 방어선입니다.

## 코드에서 각 조각이 있는 위치

| 역할 | 파일 |
|---|---|
| 토큰 생성/서명/검증/파싱 | `config/jwt/TokenProvider.java` |
| 발급 orchestration (Access+Refresh 세트, 쿠키 세팅, 재발급) | `service/TokenService.java` |
| 요청마다 토큰을 읽어 SecurityContext에 인증 심기 | `config/filter/TokenAuthenticationFilter.java` |
| 로그인 시 아이디/비번 조회 (`AuthenticationManager`가 내부적으로 사용) | `service/MemberDetailsService.java` |
| 토큰 payload → 인증 주체 객체 | `config/security/CustomUserDetails.java` |
| Refresh Token 쿠키 CRUD | `util/CookieUtil.java` |
| `jwt.*` 설정 바인딩 | `config/jwt/JwtProperties.java` |
| 인가 규칙(URL 단위)·필터 등록·401/403 처리 | `config/SecurityConfig.java` |
| 재발급/로그아웃 API | `controller/TokenApiController.java` |
| 프론트 - 저장/헤더 부착/재발급/로그아웃 | `static/js/auth.js` |

## 설정값 (`application.yaml`)

```yaml
jwt:
  issuer: test@naver.com
  secret-key: <Base64 인코딩된 HMAC 키>
  access-token-validity: 2h
  refresh-token-validity: 7d
```

- `secret-key`는 HS512용이라 최소 64바이트가 필요합니다. 짧은 문자열을 그냥 넣으면 `TokenProvider`의 `@PostConstruct`에서 키 생성이 실패합니다.
- 이 값은 지금 예시 값이 저장소에 그대로 커밋되어 있습니다. **운영 배포 시 반드시 환경변수 등으로 분리하고 새 값으로 교체해야 합니다** — 이 키를 아는 사람은 누구든 유효한 토큰을 위조할 수 있습니다.

## 토큰 관점에서의 알려진 제약

- Access Token을 `localStorage`에 저장 — 구현이 단순해지는 대신 XSS에 취약합니다. 운영 서비스라면 메모리 변수 보관 + 짧은 만료 + 재발급 전략을 더 촘촘히 가져가는 걸 고려해야 합니다.
- Refresh Token도 재발급 때마다 새로 발급(회전)되지만, 이전 Refresh Token을 명시적으로 폐기하진 않습니다 — 탈취된 Refresh Token이 재사용될 여지가 이론상 남아있습니다.

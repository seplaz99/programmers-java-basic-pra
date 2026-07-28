package com.example.formlogin.config.security;

import com.example.formlogin.domain.entity.User;
import com.example.formlogin.dto.SignInResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();// CustomUserDetails 가져오기
        User user = userDetails.getUser();

        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userName", user.getName());

        SignInResponseDto build = SignInResponseDto.builder()
                .isLoggedIn(true)
                .url("/")
                .userName(user.getName())
                .userId(user.getUserId())
                .build();

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");

        // objectMapper.writeValueAsString()으로 DTO를 JSON 문자열로 "직접" 변환해야한느 이유
        // 이 핸들러는 security 필터 안에서 실행된다. 즉 DispatcherServlet에 도달하기 전 단계라서,
        // 컨트롤러에서 쓰던 @RespnseBody + HttpMessageConverter의 자동 직렬화(객체 -> JSON)가 여기서는 등작하지 않는다.
        //Jackson(ObjectMapper)으로 직렬화를 직접 수행한 뒤 응답 본문에 써준다.
        response.getWriter().write(objectMapper.writeValueAsString(build));
    }
}

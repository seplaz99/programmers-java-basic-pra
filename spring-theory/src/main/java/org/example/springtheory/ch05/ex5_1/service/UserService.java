package org.example.springtheory.ch05.ex5_1.service;

import org.example.springtheory.ch05.ex5_1.dao.Level;
import org.example.springtheory.ch05.ex5_1.dao.UserDAO;
import org.example.springtheory.ch05.ex5_1.domain.User;

import java.sql.SQLException;

// UserService - 사용자 레벨 관리 '비즈니스 로직'을 담는 계층

// 왜 DAO가 아니라 별도의 서비스 계층인가?
// UserDAO는 'DB에 어떻게 넣고 빼는가(데이터 접근)'를 책임진다.
// 레벨 업그레이드 조건/순서 같은 '업무 규칙(비즈니스 로직)'은 데이터 접근과 성격이 다르다.
// -> 이 둘을 한 클래스에 두면 책임이 섞인다.(SRP 위반) 그래서 서비스 계층으로 분리
// UserService는 UserDAO에 의존하되, 인터페이스가 아니라 구현을 직접 쓰더라도 DI로 주입받는다.

// [업그레이드 규칙]
//  - BASIC  + 로그인 50회 이상  -> SILVER
//  - SILVER + 추천 30회 이상    -> GOLD
//  - GOLD   -> 더 이상 업그레이드 없음
public class UserService {
    // 업그레이드 기준값을 상수로 둔다.
    //  - 매직 넘버(50, 30)를 코드 곳곳에 흩지 않고 한곳에서 의미를 드러낸다.
    //  - 기준이 바뀌면 여기만 고치면 된다(변경 지점의 집중).
    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // 신규 가입
    public void add(User user) throws SQLException, ClassNotFoundException {
        user.setLevel(Level.BASIC);
        userDAO.add(user);
    }
}

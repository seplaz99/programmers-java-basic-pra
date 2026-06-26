package org.example.springtheory.ch03.ex3_3.dao;

import org.example.springtheory.ch03.ex3_3.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 전략 패턴의 적용
// 컨텍스트
// 변하지 않는 부분 : JDBC 커넥션 / 실행 / 자원관리 공통 흐름
// 전략
// 변하는 부분 : 어떤 PreparedStatement를 만들지 -> 인터페이스로 추상화
// 컨텍스트는 '인터페이스(StatementStrategy)에만' 의존하고, 실제 전략은 런타임에 주입받는다.
// 그래서 새 기능을 추가해도 컨텍스트 코드는 닫혀 있고(수정X) 전략만 새로 만들면 된다(확장O) = OCP.

public class UserDAO {

    private SimpleConnectionMaker simpleConnectionMaker;

    public UserDAO(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = new DConnectionMaker();
    }

    protected UserDAO() {}

    public void jdbcContextWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException, ClassNotFoundException {
        try (
                Connection conn = simpleConnectionMaker.makeNewConnection();
                PreparedStatement pstmt = statementStrategy.makeStatement(conn);    //  변하는 부분을 전략에 위임
        ) {
            pstmt.executeUpdate();
        }
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        class UserDAOAdd implements StatementStrategy {
            @Override
            public PreparedStatement makeStatement(Connection conn) throws SQLException {
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (id, name, password) VALUES (?, ?, ?)");

                pstmt.setString(1, user.getId());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getPassword());

                return pstmt;
            }
        }

        StatementStrategy strategy = new UserDAOAdd();;
        jdbcContextWithStatementStrategy(strategy);
    }

    // 테스트 시작전에 호출해 DB를 깨씃한 상태로 만드는 용도
    public void deleteAll() throws SQLException, ClassNotFoundException {

        class UserDAODeleteAll implements StatementStrategy {
            @Override
            public PreparedStatement makeStatement(Connection conn) throws SQLException {
                return conn.prepareStatement("delete from users");
            }
        }

        jdbcContextWithStatementStrategy(new UserDAODeleteAll());
    }
}
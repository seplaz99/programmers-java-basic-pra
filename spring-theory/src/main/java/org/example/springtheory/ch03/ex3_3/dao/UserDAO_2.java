package org.example.springtheory.ch03.ex3_3.dao;

import org.example.springtheory.ch03.ex3_3.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 익명 내부 클래스
public class UserDAO_2 {

    private SimpleConnectionMaker simpleConnectionMaker;

    public UserDAO_2(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = new DConnectionMaker();
    }

    protected UserDAO_2() {}

    public void jdbcContextWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException, ClassNotFoundException {
        try (
                Connection conn = simpleConnectionMaker.makeNewConnection();
                PreparedStatement pstmt = statementStrategy.makeStatement(conn);    //  변하는 부분을 전략에 위임
        ) {
            pstmt.executeUpdate();
        }
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        StatementStrategy strategy = new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection conn) throws SQLException {
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (id, name, password) VALUES (?, ?, ?)");

                pstmt.setString(1, user.getId());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getPassword());

                return pstmt;
            }
        };

        jdbcContextWithStatementStrategy(strategy);
    }

    // 테스트 시작전에 호출해 DB를 깨씃한 상태로 만드는 용도
    public void deleteAll() throws SQLException, ClassNotFoundException {

        StatementStrategy strategy = new StatementStrategy() {

            @Override
            public PreparedStatement makeStatement(Connection conn) throws SQLException {
                return conn.prepareStatement("delete from users");
            }
        };

        jdbcContextWithStatementStrategy(strategy);
    }
}
package org.example.springtheory.ch03.ex3_2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAODeleteAll implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Connection conn) throws SQLException {
        return conn.prepareStatement("delete from users");
    }
}

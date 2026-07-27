package org.example.springtheory.ch02.ex2_1.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface SimpleConnectionMaker {
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException;
}

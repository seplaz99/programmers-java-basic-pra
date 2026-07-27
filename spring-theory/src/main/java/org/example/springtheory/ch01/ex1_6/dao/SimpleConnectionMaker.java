package org.example.springtheory.ch01.ex1_6.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface SimpleConnectionMaker {
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException;
}

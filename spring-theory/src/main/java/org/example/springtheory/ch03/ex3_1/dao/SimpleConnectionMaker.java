package org.example.springtheory.ch03.ex3_1.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface SimpleConnectionMaker {
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException;
}

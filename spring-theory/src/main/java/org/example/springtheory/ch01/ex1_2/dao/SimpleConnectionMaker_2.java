package org.example.springtheory.ch01.ex1_2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// interface를 사용하는 userDAO 입장에서
// 어떤 클래스로 만들어졌는지 상관없이 makeNewConnection를 호출하면
// Connection 타입의 오브젝트를 들려줄 것이라고 기대할 수 있다.
interface SimpleConnectionMaker_2 {
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException;
}

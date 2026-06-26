package org.example.springtheory.strategyrefactoring.dao;

import org.example.springtheory.strategyrefactoring.domain.User;

import java.util.*;

public class Database {
    private List<User> users = new ArrayList<>();
    void open()  { System.out.println("[컨텍스트] 연결 열기"); }
    void close() { System.out.println("[컨텍스트] 연결 닫기"); }
    public List<User> getUsers() { return users; }

}

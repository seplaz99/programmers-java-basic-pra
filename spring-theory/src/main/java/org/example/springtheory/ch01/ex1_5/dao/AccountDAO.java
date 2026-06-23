package org.example.springtheory.ch01.ex1_5.dao;

public class AccountDAO {
    private SimpleConnectionMaker simpleConnectionMaker;

    // 생성자 주입 받는다라고 표현
    public AccountDAO(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = simpleConnectionMaker;
    }
}

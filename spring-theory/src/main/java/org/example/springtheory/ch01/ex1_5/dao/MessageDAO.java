package org.example.springtheory.ch01.ex1_5.dao;

public class MessageDAO {
    private SimpleConnectionMaker simpleConnectionMaker;

    public MessageDAO(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = simpleConnectionMaker;
    }
}

package org.example.springtheory.ch01.singletonbasic;

public class TicketMachine {
    private static final TicketMachine instance = new TicketMachine();
    private int lastNumber = 0;

    private TicketMachine(){}
    static TicketMachine getInstance(){
        return instance;
    }

    int issue(){
        lastNumber++;
        return lastNumber;
    }
}

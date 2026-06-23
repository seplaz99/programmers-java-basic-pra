package org.example.springtheory.ch01.singletonbasic;

public class NaiveTicketMachine {
    private int lastNumber = 0;

    public int issue() {
        lastNumber++;
        return lastNumber;
    }
}

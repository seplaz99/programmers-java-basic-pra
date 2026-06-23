package org.example.springtheory.ch01.singletonadvanced;

public class GreetingServiceGood {
    private static final GreetingServiceGood instance = new GreetingServiceGood();

    private GreetingServiceGood() {}

    public static GreetingServiceGood getInstance() {
        return instance;
    }

    public String greet(String reqName) {
        // reqName 각 스레드의 스택에 독립적으로 보관
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return reqName;
    }
}

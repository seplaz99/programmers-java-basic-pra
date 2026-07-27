package org.example.springtheory.ch01.singletonadvanced;

public class GreetingServiceBad {
    private static final GreetingServiceBad instance = new GreetingServiceBad();
    // 모든 스레드가 공유
    private String name;

    private GreetingServiceBad() {}

    public static GreetingServiceBad getInstance() {
        return instance;
    }

    public String greet(String reqName) {
        // 들어온 요청 데이터를 필드에 저장 (덮어쓰기)
        this.name = reqName;

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.name;
    }
}

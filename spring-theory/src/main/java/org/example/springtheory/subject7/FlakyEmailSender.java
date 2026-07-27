package org.example.springtheory.subject7;

public class FlakyEmailSender implements NotificationSender {
    private int attempt = 0;

    @Override
    public void send(String to, String message) {
        attempt++;
        if (attempt < 3) {
            throw new RuntimeException("일시적 네크워크 오류 (시도 " + attempt + ")");
        }
        System.out.printf("[EMAIL] (시도 %d 성공) to = %s : %s%n", attempt, to, message);
    }
}

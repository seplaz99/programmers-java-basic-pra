package org.example.springtheory.subject7;

public class SmsNotificationSender implements NotificationSender {
    @Override
    public void send(String to, String message) {
        // 실제론 메일 서버 호출 여기서는 콘솔 출력으로 흉내
        System.out.printf("[SMS] to = %s : %s%n", to, message);
    }
}

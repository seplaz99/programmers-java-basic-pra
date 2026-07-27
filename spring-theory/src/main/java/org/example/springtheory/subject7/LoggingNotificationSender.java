package org.example.springtheory.subject7;

public class LoggingNotificationSender implements NotificationSender{
    private final NotificationSender delegate;

    public LoggingNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(String to, String message) {
        System.out.println("[LOG] 알림 발송 시작 -> 수신처 : " + to);
        delegate.send(to, message);
        System.out.println("[LOG] 알림 발송 완료");
    }
}

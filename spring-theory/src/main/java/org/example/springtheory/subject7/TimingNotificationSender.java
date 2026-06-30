package org.example.springtheory.subject7;

public class TimingNotificationSender implements NotificationSender{
    private final NotificationSender delegate;

    public TimingNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(String to, String message) {
        long startTime = System.currentTimeMillis();

        delegate.send(to, message);

        long endTime = System.currentTimeMillis();
        System.out.println("[TIMER] 발송 소요 시간: " + (endTime - startTime) + "ms");
    }
}

package org.example.springtheory.subject7;

public class RetryNotificationSender implements NotificationSender {
    private final NotificationSender delegate;

    public RetryNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(String to, String message) {
        int maxAttempts = 3;
        for (int i = 1; i <= maxAttempts; i++) {
            try {
                delegate.send(to, message);
                return;
            } catch (RuntimeException e) {
                System.out.println("[RETRY] 발송 실패, 재시도 중... (" + i + "/" + maxAttempts + ")");
                if (i == maxAttempts) {
                    throw e;
                }
            }
        }
    }
}

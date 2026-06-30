package org.example.springtheory.subject7;

public class NotificationService {
    private final NotificationSender sender;    // 인터페이스 타입

    public NotificationService(NotificationSender sender) {
        this.sender = sender;
    }

    public void notifyUser(String to, String message) {
        sender.send(to, message);
    }
}

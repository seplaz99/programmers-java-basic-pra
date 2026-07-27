package org.example.springtheory.subject7;

public class Main {

    public static void main(String[] args) {
        // Part A
        System.out.println("===== Part A =====");
        NotificationService emailService = new NotificationService(new EmailNotificationSender());
        NotificationService smsService = new NotificationService(new SmsNotificationSender());

        emailService.notifyUser("email", "이메일");
        smsService.notifyUser("sms", "문자");

        // Part C-1
        System.out.println("\n===== Part C-1 =====");
        NotificationSender combinedSender =
                new TimingNotificationSender(
                        new LoggingNotificationSender(
                                new RetryNotificationSender(
                                        new FlakyEmailSender()
                                )
                        )
                );
        new NotificationService(combinedSender).notifyUser("c1", "c1");

        // Part C-2
        // 1) logging -> retry
        System.out.println("\n===== Part C-2(logging -> retry) =====");
        NotificationSender order1 =
                new LoggingNotificationSender(
                        new RetryNotificationSender(
                                new FlakyEmailSender()
                        )
                );
        new NotificationService(order1).notifyUser("order1", "순서1");

        // 2) retry -> logging
        System.out.println("\n===== Part C-2(retry -> logging) =====");
        NotificationSender orderB =
                new RetryNotificationSender(
                        new LoggingNotificationSender(
                                new FlakyEmailSender()
                        )
                );
        new NotificationService(orderB).notifyUser("order2", "순서2");
    }
}

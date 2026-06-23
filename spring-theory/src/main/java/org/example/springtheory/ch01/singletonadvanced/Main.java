package org.example.springtheory.ch01.singletonadvanced;

public class Main {
    private static int badMismatch = 0;
    private static int goodMismatch = 0;

    public static void main(String[] args) throws InterruptedException {
        int N = 30;

        System.out.println("===== 같은 싱글톤을 " + N + "개 스레드가 동시에 사용 =====");

        // 1. GreetingServiceBad 테스트
        Thread[] badThreads = new Thread[N];
        for (int i = 0; i < N; i++) {
            final String myName = "손님" + i;
            badThreads[i] = new Thread(() -> {
                String result = GreetingServiceBad.getInstance().greet(myName);
                if (!result.equals(myName)) {
                    synchronized (Main.class) {
                        badMismatch++;
                    }
                }
            });
        }

        for (Thread t : badThreads) t.start();
        for (Thread t : badThreads) t.join();

        // 2. GreetingServiceGood 테스트
        Thread[] goodThreads = new Thread[N];
        for (int i = 0; i < N; i++) {
            final String myName = "손님" + i;
            goodThreads[i] = new Thread(() -> {
                String result = GreetingServiceGood.getInstance().greet(myName);
                if (!result.equals(myName)) {
                    synchronized (Main.class) {
                        goodMismatch++;
                    }
                }
            });
        }

        for (Thread t : goodThreads) t.start();
        for (Thread t : goodThreads) t.join();

        System.out.println("[필드에 저장] 데이터 엉킴: " + badMismatch + "건 / " + N + "건     <- 위험! 거의 다 섞임");
        System.out.println("[파라미터로]  데이터 엉킴: " + goodMismatch + "건 / " + N + "건      <- 안전! 하나도 안 섞임");

        // 3. 필드 참조가 안전한 경우 확인 (UserDao)
        System.out.println("\n===== 필드에 둬도 되는 것: 다른 싱글톤 참조 =====");
        UserDao userDao = UserDao.getInstance();
        System.out.println(userDao.findUser("kim"));
        System.out.println(userDao.findUser("lee"));

        UserDao anotherUserDao = UserDao.getInstance();
        System.out.println("같은 DAO인가? " + (userDao == anotherUserDao));
    }
}

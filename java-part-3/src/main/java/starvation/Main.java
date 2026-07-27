package starvation;

public class Main {

    public static void main(String[] args) {
        //  starvation(기아 상태)
        // 어떤 스레드가 계속해서 실행되지 못하고, 자원을 할당받지 못하는 상태를 말한다.

        SharedResource sharedResource = new SharedResource();
        new WorkedThread(sharedResource, "WorkedThread-1").start();
        new WorkedThread(sharedResource, "WorkedThread-2").start();
        new WorkedThread(sharedResource, "WorkedThread-3").start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    sharedResource.makeResourceAvailable();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}

package starvation;

public class SharedResource {
    private volatile boolean isAvailable = false;

    public synchronized void waitForResource(String threadName) {
        while (!isAvailable) {
            try {
                System.out.println(threadName + ": Waiting for resource");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(threadName + ": Got the resource");
        isAvailable = false;
    }

    public synchronized void makeResourceAvailable() {
        isAvailable = true;
        System.out.println("Resource is now available");
        // notify();
        notifyAll();
    }
}

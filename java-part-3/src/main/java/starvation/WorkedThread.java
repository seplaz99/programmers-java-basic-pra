package starvation;

public class WorkedThread extends Thread {
    private SharedResource sharedResource;
    private String name;

    public WorkedThread(SharedResource sharedResource, String name) {
        this.sharedResource = sharedResource;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            sharedResource.waitForResource(name);
            try {
                Thread.sleep(1000);
            }  catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

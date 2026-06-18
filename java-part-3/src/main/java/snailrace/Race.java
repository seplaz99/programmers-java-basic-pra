package snailrace;

public class Race {
    private volatile boolean over = false;

    public boolean isOver() {
        return over;
    }

    public synchronized void finish(String name) {
        if (!over) {
            over = true;
            System.out.println(name + " is the winner!");
        }
    }
}

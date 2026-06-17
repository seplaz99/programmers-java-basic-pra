package snailrace;

import java.util.Random;

public class Snail extends Thread {
    private String name;
    private int position = 0;
    private static final int FINISH = 15;
    private Random rand = new Random();
    private Race race;

    public Snail(String name, Race race) {
        this.name = name;
        this.race = race;
    }

    public void run() {
        while (position < FINISH && !race.isOver()) {
            position += rand.nextInt(3) + 1;
            printProgress();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (position >= FINISH) {
                race.finish(name);
            }
        }

        System.out.println(name + " has reached the finish line!");
    }

    private void printProgress() {
        System.out.println(name + " : " + "=".repeat(position) + ">");
    }
}

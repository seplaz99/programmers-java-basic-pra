package snailrace;

public class Main {

    public static void main(String[] args) {
        Race race = new Race();
        Snail s1 = new Snail("달팽이 1", race);
        Snail s2 = new Snail("달팽이 2", race);
        Snail s3 = new Snail("달팽이 3", race);

        s1.start();
        s2.start();
        s3.start();
    }
}

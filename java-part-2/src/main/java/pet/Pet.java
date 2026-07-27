package pet;

public class Pet {
    private String name;
    private int fullness;
    private int happiness;

    public Pet(String name) {
        this.name = name;
        this.fullness = 50;
        this.happiness = 50;

        System.out.println(name + "이(가) 태어났어요!");
    }

    public void feed() {
        if (fullness == 100) {
            System.out.println("배가 가득 찼어요.");
            return;
        }

        System.out.println(name + "에게 먹이를 줬어요! 냠냠");
        fullness += 20;
        if (fullness > 100) {
            fullness = 100;
        }

        happiness += 5;
        if (happiness > 100) {
            happiness =100;
        }
    }

    public void showStatus() {
        System.out.println("[" + name + "] 포만감: " + fullness + " / 행복: " + happiness);
    }

    public void play() {
        System.out.println(name + "와(과) 신나게 놀았어요!");
        happiness += 20;
        if (happiness > 100) {
            happiness =100;
        }

        fullness -= 10;
        if (fullness < 50) {
            System.out.println(name + "이(가) 배가 고픈 상태예요");
        }
        if (fullness < 0) {
            fullness =0;
        }
    }
}

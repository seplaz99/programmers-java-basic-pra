package rpggame;

public class Main {

    public static void main(String[] args) {
        int inflation = 0;

        System.out.println("=== 전투 시작! 용사 vs 슬라임 ===");
        Character hero = new Character("용사", 100, 25);
        String[] monsters = {"슬라임", "고블린", "오크", "용"};

        hero.showStatus();

        for (int i = 0; i < 4; i++) {
            Character monster = new Character(monsters[i], 30 + inflation, 5 + inflation);
            monster.showStatus();

            while(true) {
                hero.attack(monster);
                monster.showStatus();
                if (!monster.isAlive()) {
                    System.out.println(monster.getName() + "을/를 쓰러뜨렸다!");
                    break;
                }
                monster.attack(hero);
                hero.showStatus();
                if(!hero.isAlive()) {
                    System.out.println(hero.getName() + "이 쓰러졌다!");
                    return;
                }
            }

            if (i < monsters.length - 1) {
                System.out.println("\n=== 다음 상대: " + monsters[i+1] + " ===");
            } else {
                System.out.println("축하합니다! 모든 몬스터를 물리치고 평화를 되찾았습니다!");
            }

            inflation += 5;
        }
    }
}

package rpggame;

public class Character {
    private String name;
    private int hp;
    private int power;

    public Character(String name, int hp, int power) {
        this.name = name;
        this.hp = hp;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void showStatus() {
        System.out.println(name + " (Hp: " + hp + ", Power: " + power + ")");
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) hp = 0;
    }

    public void attack(Character target) {
        System.out.println(name + "의 공격! " + target.name + "에게 " + power + " 피해");
        target.takeDamage(power);
    }
}

package vendingmachine;

public class Water extends Drink{
    public Water() {
        super("물", 100);
    }

    @Override
    public void dispense() {
        System.out.println("물이 나왔습니다.");
    }
}

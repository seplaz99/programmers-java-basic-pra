package vendingmachine_abstract;

public class Cider extends Drink{
    public Cider() {
        super("사이다", 300);
    }

    @Override
    public void dispense() {
        System.out.println("사이다가 나왔습니다.");
    }
}

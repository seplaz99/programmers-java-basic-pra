package vendingmachine_abstract;

public class Fanta extends Drink{

    public Fanta() {
        super("환타", 200);
    }

    @Override
    public void dispense() {
        System.out.println("환타가 나왔습니다.");
    }
}

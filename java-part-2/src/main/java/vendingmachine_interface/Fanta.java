package vendingmachine_interface;

public class Fanta implements Drink {
    private String name = "환타";
    private int price = 200;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void dispense() {
        System.out.println("환타가 나왔습니다.");
    }
}

package vendingmachine_interface;

public class Water implements Drink {
    private String name = "물";
    private int price = 100;

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
        System.out.println("물이 나왔습니다.");
    }
}

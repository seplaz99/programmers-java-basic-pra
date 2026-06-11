package vendingmachine_interface;

public class Cider implements Drink {
    private String name = "사이다";
    private int price = 300;

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
        System.out.println("사이다가 나왔습니다.");
    }
}

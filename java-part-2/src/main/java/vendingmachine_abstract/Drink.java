package vendingmachine_abstract;

// 모든 음료의 공통 틀
// 이름, 가격
// 음료가 나올 때 동작 -> dispense() : 추상 메서드

public abstract class Drink {
    // 자식 클래스에서 물려받아 쓰도록 protected
    protected String name;
    protected int price;

    public Drink(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    // 음료가 나올 때의 동작
    public abstract void dispense();
}

package vendingmachine;

public class Coke extends Drink{
    // 부모 클래스의 생성자를 초기화 시켜줘야함
    public Coke() {
        super("콜라", 500);
    }

    @Override
    public void dispense() {
        System.out.println("콜라가 나왔습니다.");
    }
}

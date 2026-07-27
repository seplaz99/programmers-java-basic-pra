package vendingmachine_interface;

public class VendingMachine {
    private int totalMoney;
    private Drink[] drinks;     // 다형성

    public  VendingMachine() {
        totalMoney = 0;
        // 부모 타입 (Drink) 배열에 자식 객체들을 담는다(다형성)
        drinks = new Drink[] {new Coke(), new Cider(), new Fanta(), new Water()};
    }

    // 돈 넣기 : insertMoney
    public void insertMoney(int money) {
        System.out.println(money + "원을 넣었습니다.");
        totalMoney += money;
    }

    // 음료 구매 : buy - 메뉴 번호(1~4)로 선택
    public void buy(int menuNumber) {

        Drink drink = drinks[menuNumber - 1];

        if (totalMoney < drink.getPrice()) {
            System.out.println("돈이 부족합니다.");
            return;
        }

        totalMoney -= drink.getPrice();

        drink.dispense();
    }

    // 종료 시 잔돈 반환
    public int returnMoney() {
        System.out.println(totalMoney + "원을 반환합니다.");
        totalMoney = 0;
        return totalMoney;
    }

    // 메뉴 출력
    public void printMenu() {
        System.out.println("================================= 자판기 ================================");
        System.out.println("[1]콜라-500원 [2]사이다-300원 [3]환타-200원 [4]물-100원 [5]돈넣기 [6]종료");
        System.out.println("현재 금액 : " + totalMoney + "원");
        System.out.println("==========================================================================");
    }
}

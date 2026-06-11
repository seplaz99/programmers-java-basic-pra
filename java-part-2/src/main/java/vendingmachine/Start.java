package vendingmachine;

import java.util.Scanner;

public class Start {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        VendingMachine vendingMachine = new VendingMachine();

        while (true) {
            vendingMachine.printMenu();

            int menuNum = sc.nextInt();
            if (menuNum >= 1 && menuNum <= 4) {
                vendingMachine.buy(menuNum);
            } else if (menuNum == 5) {
                System.out.println("넣을 금액 : ");
                int money = sc.nextInt();
                vendingMachine.insertMoney(money);
            } else if (menuNum == 6) {
                vendingMachine.returnMoney();
                System.out.println("프로그램을 종료합니다.");
                return;
            } else {
                System.out.println("잘못된 번호를 입력하였습니다.");
            }
        }
    }
}

package main.java.accountbook;

import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountBook book = new AccountBookImpl();
        boolean flag = true;

        while (flag) {
            System.out.println();
            System.out.println("===== 가계부 =====");
            System.out.println("1. 내역 추가 \n2. 내역 조회 \n3. 전체 삭제 \n4. 내역 삭제 \n5. 종료 \n번호 입력");
            int menu = Integer.parseInt(sc.nextLine());
            switch (menu) {
                case 1:
                    book.addAccount();
                    break;
                case 2:
                    book.showAccounts();
                    break;
                case 3:
                    book.removeAll();
                    break;
                case 4:
                    book.removeAccount();
                    break;
                case 5:
                    System.out.println("종료합니다");
                    flag = false;
                    break;
                default:
                    System.out.println("잘못된 번호입니다");
            }
        }
    }
}

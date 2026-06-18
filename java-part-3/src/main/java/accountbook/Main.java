package accountbook;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        AccountBookImpl accountBook = new AccountBookImpl(sc);

        while (true){
            System.out.println("===== 가계부 =====");
            System.out.println("1. 내역 추가");
            System.out.println("2. 내역 조회");
            System.out.println("3. 전체 삭제");
            System.out.println("4. 내역 삭제");
            System.out.println("5. 종료");

            System.out.println("원하는 작업을 선택하세요: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice){
                case 1:
                    accountBook.addAccount();
                    break;
                case 2:
                    accountBook.showAccount();
                    break;
                case 3:
                    accountBook.deleteAll();
                    break;
                case 4:
                    accountBook.deleteItem();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }



    }
}

package membermanagement_interface;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int num = Print.printPricePlan();
        MemberManagement manager = new MemberManagement(num);

        Scanner sc = new Scanner(System.in);

        System.out.println("=== 회원 관리 프로그램을 시작합니다 ===");

        while (true) {int current = manager.getMemberCnt();
            int total = manager.getTotalCnt();
            int choice = Print.printMenu(current, total);

            switch (choice) {
                case 1:
                    manager.addMember();
                    break;
                case 2:
                    System.out.println("찾고자 하는 회원의 이메일을 적어주세요.");
                    String searchEmail = sc.next();
                    manager.selectEmail(searchEmail);
                    break;
                case 3:
                    System.out.println("찾고자 하는 회원의 이름을 적어주세요.");
                    String searchName = sc.next();
                    manager.selectName(searchName);
                    break;
                case 4:
                    manager.selectAll();
                    break;
                case 5:
                    System.out.println("수정하고자 하는 회원의 이메일을 적어주세요.");
                    String updateEmail = sc.next();
                    manager.updateMember(updateEmail);
                    break;
                case 6:
                    System.out.println("삭제하고자 하는 회원의 이메일을 적어주세요.");
                    String deleteEmail = sc.next();
                    manager.deleteMember(deleteEmail);
                    break;
                case 7:
                    System.out.println("이용해주셔서 감사합니다.");
                    sc.close();
                    return;
                default: System.out.println("올바른 번호를 입력하세요.");
            }
            System.out.println();
        }
    }
}

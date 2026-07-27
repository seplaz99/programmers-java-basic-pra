package membermanagement_interface;

import java.util.Scanner;

public class Print {
    private static Scanner sc = new Scanner(System.in);

    public static int printPricePlan() {
        System.out.println("[요금제를 선택하세요]");
        System.out.println("[1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명");
        return sc.nextInt();
    }

    public static int printMenu(int memberCnt, int totalCnt) {
        System.out.printf("[수행할 업무를 선택하세요 - 현재 회원수 : %d/%d] \n", memberCnt, totalCnt);
        System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
        System.out.println("[4]회원전체조회 [5]회원정보 수정 [6]회원삭제");
        System.out.println("[7]프로그램 종료");
        return sc.nextInt();
    }

    public static Member inputMemberInfo() {
        System.out.println("등급 [1]일반 [2]VIP");
        int grade = sc.nextInt();

        System.out.println("회원 정보를 입력하세요.");

        System.out.print("회원 이름 : ");
        String name = sc.next();
        System.out.print("회원 이메일 : ");
        String email = sc.next();
        System.out.print("회원 연락처 : ");
        String phoneNum = sc.next();

        if (grade == 2) return new VipMember(name, email, phoneNum);
        else return new NormalMember(name, email, phoneNum);
    }
}

package main.java;

import java.util.*;

public class K_member_management {
    static Scanner sc = new Scanner(System.in);
    static int totalCnt = 0;
    static int memberCnt = 0;

    public static int printPricePlan() {
        System.out.println("[요금제를 선택하세요]");
        System.out.println("[1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명");

        return sc.nextInt();
    }

    public static int printMenu() {
        System.out.printf("[수행할 업무를 선택하세요 - 현재 회원수 : %d/%d] \n", memberCnt, totalCnt);
        System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
        System.out.println("[4]회원전체조회 [5]회원정보 수정 [6]회원삭제");
        System.out.println("[7]프로그램 종료");

        return sc.nextInt();
    }

    public static String[] inputMemberInfo() {
        System.out.println("회원 정보를 입력하세요.");

        System.out.print("회원 이름 : ");
        String name = sc.next();
        System.out.print("회원 이메일 : ");
        String email = sc.next();
        System.out.print("회원 연락처 : ");
        String phoneNum = sc.next();

        return new String[]{name, email, phoneNum};
    }

    public static void addMember(String[][] members) {
        if(memberCnt >= totalCnt) {
            System.out.println("회원이 꽉 찼습니다.");
            return;
        }

        String[] info = inputMemberInfo();
        String name = info[0];
        String email = info[1];
        String phoneNum = info[2];

        if (checkEmail(members, email)) {
            System.out.println("이미 존재하는 회원입니다.");
            return;
        }

        members[memberCnt][0] = name;
        members[memberCnt][1] = email;
        members[memberCnt][2] = phoneNum;

        memberCnt++;
        System.out.println("회원 등록이 완료되었습니다.");
    }


    public static boolean checkEmail(String[][] members, String email) {
        for (int i = 0; i < memberCnt; i++) {
            if (members[i][1].equals(email)) {
                return true;
            }
        }

        return false;
    }

    public static void selectEmail(String[][] members) {
        System.out.println("찾고자 하는 회원의 이메일을 적어주세요.");
        String email = sc.next();

        for (int i = 0; i < memberCnt; i++) {
            if (members[i][1].equals(email)) {
                System.out.println("[이름] " + members[i][0] + ", [이메일] " + members[i][1] + ", [연락처] " + members[i][2]);
                return;
            }
        }

        System.out.println("찾으시는 정보가 없습니다.");
    }

    public static void selectName(String[][] members) {
        System.out.println("찾고자 하는 회원의 이름을 적어주세요.");
        String name = sc.next();

        boolean found = false;

        for (int i = 0; i < memberCnt; i++) {
            if (members[i][0].equals(name)) {
                System.out.println("[이름] " + members[i][0] + ", [이메일] " + members[i][1] + ", [연락처] " + members[i][2]);
                found  = true;
            }
        }

        if (!found) {
            System.out.println("찾으시는 정보가 없습니다.");
        }
    }

    public static void selectAll(String[][] members) {
        if (memberCnt == 0) {
            System.out.println("저장된 회원 정보가 없습니다.");
            return;
        }

        for (int i = 0; i < memberCnt; i++) {
            System.out.println("[이름] " + members[i][0] + ", [이메일] " + members[i][1] + ", [연락처] " + members[i][2]);
        }
    }

    public static void updateMember(String[][] members) {
        System.out.println("수정하고자 하는 회원의 이메일을 적어주세요.");
        String targetEmail = sc.next();

        for (int i = 0; i < memberCnt; i++) {
            if (members[i][1].equals(targetEmail)) {
                String[] info = inputMemberInfo();
                String newName = info[0];
                String newEmail = info[1];
                String newPhoneNum = info[2];

                if (!targetEmail.equals(newEmail) && checkEmail(members, newEmail)) {
                    System.out.println("이미 존재하는 회원입니다.");
                    return;
                }

                members[i][0] = newName;
                members[i][1] = newEmail;
                members[i][2] = newPhoneNum;

                System.out.println("회원 정보가 수정되었습니다.");
                return;
            }
        }

        System.out.println("수정하고자하는 회원이 없습니다.");
    }

    public static void deleteMember(String[][] members) {
        System.out.println("삭제하고자 하는 회원의 이메일을 적어주세요.");
        String targetEmail = sc.next();

        for (int i = 0; i < memberCnt; i++) {
            if (members[i][1].equals(targetEmail)) {
                for (int j = i; j < memberCnt - 1; j++) {
                    members[j][0] = members[j + 1][0];
                    members[j][1] = members[j + 1][1];
                    members[j][2] = members[j + 1][2];
                }

                members[memberCnt - 1][0] = null;
                members[memberCnt - 1][1] = null;
                members[memberCnt - 1][2] = null;

                System.out.println("회원 정보가 삭제되었습니다.");
                memberCnt--;
                return;
            }
        }

        System.out.println("삭제하고자하는 회원이 없습니다.");
    }

    public static void main(String[] args) {
        int num = printPricePlan();
        String[][] members = new String[num * 10][3];
        totalCnt = num * 10;

        while (true) {
            int choice = printMenu();

            switch (choice) {
                case 1:
                    addMember(members);
                    break;
                case 2:
                    selectEmail(members);
                    break;
                case 3:
                    selectName(members);
                    break;
                case 4:
                    selectAll(members);
                    break;
                case 5:
                    updateMember(members);
                    break;
                case 6:
                    deleteMember(members);
                    break;
                case 7: System.out.println("이용해주셔서 감사합니다."); return;
                default: System.out.println("올바른 번호를 입력하세요.");
            }
        }
    }
}

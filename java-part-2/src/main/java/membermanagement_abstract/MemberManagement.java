package membermanagement_abstract;

public class MemberManagement {

    private Member[] members;
    private int totalCnt;
    private int memberCnt;

    public MemberManagement(int num) {
        this.totalCnt = num * 10;
        this.members = new Member[totalCnt];
        this.memberCnt = 0;
    }

    public int getMemberCnt() { return  memberCnt; }
    public int getTotalCnt() { return totalCnt; }

    public void addMember() {
        if (memberCnt >= totalCnt) {
            System.out.println("회원이 꽉 찼습니다.");
            return;
        }

        Member newMember = Print.inputMemberInfo();

        if (checkEmail(newMember.getEmail())) {
            System.out.println("이미 존재하는 회원입니다.");
            return;
        }

        members[memberCnt] = newMember;
        memberCnt++;
        System.out.println("회원 등록이 완료되었습니다.");
    }

    public void selectEmail(String email) {
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getEmail().equals(email)) {
                printMember(members[i]);
                return;
            }
        }
        System.out.println("찾으시는 정보가 없습니다.");
    }

    public void selectName(String name) {
        boolean found = false;
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getName().equals(name)) {
                printMember(members[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("찾으시는 정보가 없습니다.");
        }
    }

    public void selectAll() {
        if (memberCnt == 0) {
            System.out.println("저장된 회원 정보가 없습니다.");
            return;
        }
        for (int i = 0; i < memberCnt; i++) {
            printMember(members[i]);
        }
    }

    public void updateMember(String targetEmail) {
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getEmail().equals(targetEmail)) {
                Member newInfo = Print.inputMemberInfo();

                if (!targetEmail.equals(newInfo.getEmail()) && checkEmail(newInfo.getEmail())) {
                    System.out.println("이미 존재하는 회원입니다.");
                    return;
                }

                members[i] = newInfo;
                System.out.println("회원 정보가 수정되었습니다.");
                return;
            }
        }
        System.out.println("수정하고자 하는 회원이 없습니다.");
    }

    public void deleteMember(String targetEmail) {
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getEmail().equals(targetEmail)) {
                for (int j = i; j < memberCnt - 1; j++) {
                    members[j] = members[j + 1];
                }
                members[memberCnt - 1] = null;
                memberCnt--;

                System.out.println("회원 정보가 삭제되었습니다.");
                return;
            }
        }
        System.out.println("삭제하고자 하는 회원이 없습니다.");
    }

    private boolean checkEmail(String email) {
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private void printMember(Member m) {
        System.out.println("[" + m.getGrade() + "] " + m.getName() + " / " + m.getEmail()
                + " / " + m.getPhoneNumber() + " (혜택: " + m.getBenefit() + ")");
    }
}

package membermanagement_abstract;

public class NormalMember extends Member {
    public NormalMember(String name, String email, String phoneNum) {
        super(name, email, phoneNum);
    }

    @Override
    public String getGrade() {
        return "일반";
    }

    @Override
    public String getBenefit() {
        return "기본 서비스";
    }
}

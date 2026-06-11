package membermanagement_interface;

public class NormalMember implements Member {

    private String name, email, phoneNumber;

    public NormalMember(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void updateInfo(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
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

package membermanagement_interface;

public class VipMember implements Member {
    private String name, email, phoneNumber;

    public VipMember(String name, String email, String phoneNumber) {
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
        return "VIP";
    }

    @Override
    public String getBenefit() {
        return "10% 할인 + 무료배송";
    }
}

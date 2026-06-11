package membermanagement_interface;

public interface Member {
    void updateInfo(String name, String email, String phoneNumber);
    String getName();
    String getEmail();
    String getPhoneNumber();
    String getGrade();
    String getBenefit();
}

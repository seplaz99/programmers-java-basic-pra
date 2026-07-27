public enum B_day_2 {
    SUNDAY("Holiday"),
    MONDAY("Workday"),
    TUESDAY("Workday");

    // 싱글톤을 따르고 있다.
    // 필드
    private String desc;

    // 생성자
    B_day_2(String desc) {
        this.desc = desc;
    }

    // 메서드
    public String getDesc() {
        return desc;
    }
}

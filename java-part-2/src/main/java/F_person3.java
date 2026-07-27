public class F_person3 {
    String name;
    int age;

    // 기본 생성
    public F_person3() {
        this.name = "Unknown";
        this.age = 0;
    }

    // 매개변수가 있는 생성
    public F_person3(String name) {
        this.name = name;
    }

    public F_person3(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println(name + " " + age);
    }
}

public class F_person5 {
    String name;
    int age;

    public F_person5() {
        // F_person5("John", 15) -> 반드시 this로 호출해야됨
        this("John", 15);   // 매개변수가 있는 생성자를 가리킴
    }

    public F_person5(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println(name + " " + age);
    }
}

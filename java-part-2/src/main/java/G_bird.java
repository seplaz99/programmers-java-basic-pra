public class G_bird extends G_animal {

    String wing;    // 확장, 부모에게 없는 속성

    public void fly() {
        System.out.println(kind + " is flying." + wing);
    }

    @Override
    public void walk() {
        super.walk();
        System.out.println("사뿐사뿐");
    }
}

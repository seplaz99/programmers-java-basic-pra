package solid;

// ❌ 나쁜 예: 부모는 "날 수 있다"고 약속했는데 자식이 그 약속을 깸
/*class Bird {
    void fly() { System.out.println("훨훨 납니다"); }
}
class Penguin extends Bird {
    void fly() { throw new RuntimeException("펭귄은 못 날아요!"); } // 💥
}*/

class Bird {
    void eat() {
        System.out.println("모이를 먹습니다");
    }

    void breathe() {
        System.out.println("숨을 쉽니다");
    }
}

class FlyingBird extends Bird {
    void fly() {
        System.out.println("훨훨 납니다");
    }
}

class Sparrow extends FlyingBird {}

class Penguin extends Bird {
    void swim() {
        System.out.println("수영을 합니다");
    }
}

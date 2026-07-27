// 다형성
// 동일한 인터페이스나 부모 클래스를 공유하는 여러 객체들이 각자 다른 방식으로 동작하도록 할 수 있는 기능을 말한다.
// 다형성은 "하나의 인터페이스로 여러 형태의 객체를 처리할 수 있다"는 의미를 가지고 있다.
// 이를 통해 코드의 유연성과 확장성을 크게 향상시킬 수 있다.

class K_animal {
    public void sound() {
        System.out.println("Animal Sound");
    }
}

class K_dog extends K_animal {
    @Override
    public void sound() {
        System.out.println("Dog Sound");
    }
}

class K_cat extends K_animal {
    @Override
    public void sound() {
        System.out.println("Cat sound");
    }
}

public class K_polymorphism {
    public static void main(String[] args) {

        K_animal myAnimal = new K_animal(); // animal 타입의 객체
        K_animal myDog = new K_dog();   // Dog타입의 객체, Animal 타입으로 업캐스팅
        K_animal myCat = new K_cat();   // // Cat타입의 객체, Animal 타입으로 업캐스팅

        myAnimal.sound();
        myDog.sound();  // 런타임 시점에 dog의 sound()호출
        myCat.sound();  // 런타임 시점에 cat의 sound()호출

        // 다운캐스팅을 통해 Dog타입으로 반환
        // K_dog myDog2 = (K_dog) new K_animal();

        // myDog2.sound();

    }
}

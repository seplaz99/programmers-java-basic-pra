package org.example.springtheory.ch01.ioc;

public class Main {

    public static void main(String[] args) {
        /*System.out.println("===== 1. 직접 제어 상태 =====");
        CoffeeMaker maker = new CoffeeMaker();
        maker.brew();*/

        System.out.println("===== 2. DI: 제어를 바깥(main)으로 =====");
        new CoffeeMaker(new ColombiaBean()).brew();
        new CoffeeMaker(new EthiopiaBean()).brew();

        System.out.println("\n===== 3. IoC 컨테이너: 조립까지 위임 =====");
        CoffeeContainer container = new CoffeeContainer();
        // main에서 new를 하지 않음
        CoffeeMaker maker = container.getCoffeeMaker();
        maker.brew();

        System.out.println("\n===== 4. 헐리우드 원칙: 흐름의 역전 =====");
        Button button = new Button();
        button.setListener(new LikeAction());
        button.press();
    }
}

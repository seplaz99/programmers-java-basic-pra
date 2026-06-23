package org.example.springtheory.ch01.ioc;

public class CoffeeMaker {
    // IoC 적용 전
    /*private Bean bean = new ColombiaBean();
    // private Bean bean = new EthiopiaBean();

    void brew() {
        System.out.println(bean.name() + "로 커피를 내립니다.");
    }*/

    private Bean bean;
    // 생성자를 통해 외부에서 주입 (DI)
    CoffeeMaker(Bean bean) {
        this.bean = bean;
    }
    void brew() {
        System.out.println(bean.name() + "로 커피를 내립니다.");
    }

}

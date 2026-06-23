package org.example.springtheory.ch01.ioc;

public class CoffeeContainer {
    CoffeeMaker getCoffeeMaker() {
        Bean bean = new ColombiaBean();
        return new CoffeeMaker(bean);
    }
}

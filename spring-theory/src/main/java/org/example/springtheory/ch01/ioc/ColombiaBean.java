package org.example.springtheory.ch01.ioc;

public class ColombiaBean implements Bean {
    @Override
    public String name() {
        return "콜롬비아 원두";
    }
}

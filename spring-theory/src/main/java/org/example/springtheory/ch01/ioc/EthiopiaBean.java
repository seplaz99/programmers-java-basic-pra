package org.example.springtheory.ch01.ioc;

public class EthiopiaBean implements Bean {
    @Override
    public String name() {
        return "에티오피아 원두";
    }
}

package org.example.springtheory.ch01.singletonbasic;

public class Settings {
    private static Settings instance = null;
    private String theme = "dark";

    public Settings() {}
    static Settings getInstance(){
        if (instance == null){
            instance = new Settings();
        }
        return instance;
    }

    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
}

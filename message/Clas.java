package azerot.azerot.message;

import java.util.Random;

public enum Clas {
    tank("Бронебойный", ""),
    mage("Чародей",""),
    palach("Палач", ""),
    vor("Вор", "");


    private String subtitle;

    private String title;
    Clas(String title, String subtitle){
        this.title = title;
        this.subtitle = subtitle;

    }
    public String getSubtitle(){
        return this.subtitle;
    }
    public String getTitle(){
        return this.title;
    }
}

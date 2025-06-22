package azerot.azerot.message;

import java.util.Random;

public enum lvlup {
    one("§a§lНужны штаны побольше ", ""),
    two("§a§lМоя сила растёт!", "" );

    private String subtitle;

    private String title;
    Random random = new Random();

    lvlup(String title, String subtitle){
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

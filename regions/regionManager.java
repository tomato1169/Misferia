package azerot.azerot.regions;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum regionManager {
    region1("§6Деревня Гинс", " §aВы вошли в безопасную зону!", true, true, false),
    region2("§6Дом Генри", " §aВы вошли в безопасную зону!", true, true, false),
    region3("§6Элькорн", " §aВы вошли в безопасную зону!", true, true, false),
    region5("§6Остров мертвых", " §aВы вошли на §7[§6Остров мертвых§7]", false, true, true),
    region6("§6Подножие", " §aВы вошли в безопасную зону!", true, true, false),
    region7("§6Исследовательская зона", " §aВы вошли в безопасную зону!", true, true, false),
    region8("§6Скрытая пещера", " §aВы вошли в безопасную зону!", true, true, false),
    region9("§6Сборище", " §aВы вошли в безопасную зону!", false, true, false);




    private String name;
    private String textFromChat;
    private  boolean SafeZone;
    private  boolean Title;

    private boolean Bossbar;

    regionManager(String name, String textFromchat, boolean Safezone, boolean title, boolean bossbar){
        this.name = name;
        this.textFromChat = textFromchat;
        this.SafeZone = Safezone;
        this.Title = title;
        this.Bossbar = bossbar;

    }
}

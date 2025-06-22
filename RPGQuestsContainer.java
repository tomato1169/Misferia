package azerot.azerot;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class RPGQuestsContainer
{
    private static final Map<Integer, RPGQuestsContainer> rpgquests = new HashMap<>();
    private int id;
    private String name;
    private String lore;
    private int rLevel;
    private String rQuest;
    private String reward;
    private String rInPQuest;
    private String cQuest;
    private int xp;
    private String teleport;
    private String enter_items;

    private String items;

    private String mobs;
    private int type;
    private String Dialogue;


    public RPGQuestsContainer(ResultSet result) {
        try {
            if (result == null) {
                this.id = 0;
                this.name = null;
                this.lore = null;
                this.cQuest = null;
                this.items = null;
                this.mobs = null;
                this.reward = null;
                this.rInPQuest = null;
                this.rLevel = 0;
                this.rQuest = null;
                this.xp= 0;
                this.type = 0;
                this.enter_items = null;
                this.teleport = null;
                this.Dialogue = null;
            } else {
                this.id = result.getInt("id");
                this.name = result.getString("name");
                this.lore = result.getString("lore");
                this.cQuest = result.getString("cQuest");
                this.items = result.getString("items");
                this.mobs = result.getString("mobs");
                this.reward = result.getString("reward");
                this.rInPQuest = result.getString("rInPQuest");
                this.rLevel = result.getInt("rLevel");
                this.rQuest = result.getString("rQuest");
                this.xp= result.getInt("xp");
                this.type = result.getInt("type");
                this.enter_items = result.getString("enter_items");
                this.teleport = result.getString("teleport");
                this.Dialogue = result.getString("Dialogue");

                rpgquests.put(this.id, this);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static Map<Integer, RPGQuestsContainer> getRpgquests() {
        return rpgquests;
    }
    public static RPGQuestsContainer getRPGQuestsContainer(int id) {
        return rpgquests.get(Integer.valueOf(id));
    }
}

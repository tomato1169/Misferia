package azerot.azerot.Quest;

import azerot.azerot.RPGQuestsContainer;
import azerot.azerot.WorldUtils;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class tasksInQuest {
    public static HashMap<String, tasksInQuest> quests = new HashMap<>();
    private HashMap<String, String> tasks = new HashMap<>();
    private int stage = 0;
    public tasksInQuest(String id) throws SQLException {
        switch (id){
            case "1":
                stage = 1;
                tasks.put("kill", "1 5;2 5;3 5");

                quests.put(id, this);
                break;
        }
    }
}

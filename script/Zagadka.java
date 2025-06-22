package azerot.azerot.script;

import azerot.azerot.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Zagadka {
    public static void onDeath(Entity e, Entity t) {
        Bukkit.broadcastMessage("§c§lХозяин чертог пал....");
    }
    static HashMap<String, Integer> attack1 = new HashMap<>();

}

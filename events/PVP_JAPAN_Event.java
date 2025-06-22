package azerot.azerot.events;

import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import com.grinderwolf.swm.api.SlimePlugin;
import com.grinderwolf.swm.api.exceptions.CorruptedWorldException;
import com.grinderwolf.swm.api.exceptions.NewerFormatException;
import com.grinderwolf.swm.api.exceptions.UnknownWorldException;
import com.grinderwolf.swm.api.exceptions.WorldInUseException;
import com.grinderwolf.swm.api.loaders.SlimeLoader;
import com.grinderwolf.swm.api.world.SlimeWorld;
import com.grinderwolf.swm.plugin.SWMPlugin;
import com.grinderwolf.swm.plugin.config.WorldData;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class PVP_JAPAN_Event {

    static HashMap<String, PVP_JAPAN_Event> games = new HashMap<>();

    private HashMap<String, Integer> blueTeam = new HashMap<>();

    private HashMap<String, Integer> redTeam = new HashMap<>();

    private int FullPOintsBlue = 0;

    private int FullPOintsRed = 0;


    public HashMap<String, Integer> getBlueTeam() {
        return blueTeam;
    }
    public HashMap<String, Integer> getRedTeam() {
        return redTeam;
    }

    public static PVP_JAPAN_Event getGames(String id) {
        return games.get(id);
    }

    public int getFullPOintsBlue() {
        return FullPOintsBlue;
    }
    public int getFullPOintsRed(){
        return FullPOintsRed;
    }

    public void setFullPOintsBlue(int fullPOintsBlue) {
        FullPOintsBlue = fullPOintsBlue;
    }

    public void setFullPOintsRed(int fullPOintsRed) {
        FullPOintsRed = fullPOintsRed;
    }

    public void startGame() throws SQLException {
        games.put("1-1", this);
        SlimeWorld Japan_World;
        SlimePlugin slimePlugin = (SlimePlugin) SWMPlugin.getInstance();
        SlimeLoader loader = slimePlugin.getLoader("file");

        WorldData worldData = new WorldData();
        worldData.setLoadOnStartup(true);
        worldData.setSpawn("54, 75, 74");
        worldData.setDifficulty("easy");
        worldData.setAllowAnimals(false);
        worldData.setAllowMonsters(false);
        worldData.setReadOnly(false);
        SlimeWorld.SlimeProperties props = SlimeWorld.SlimeProperties.builder().difficulty(1).allowAnimals(false).allowMonsters(true).spawnX(54).spawnY(75).spawnZ(74).pvp(true).readOnly(true).build();
        try {
            Japan_World = slimePlugin.loadWorld(loader, "pvp_event",  props);
        } catch (UnknownWorldException ep) {
            throw new RuntimeException(ep);
        } catch (IOException ep) {
            throw new RuntimeException(ep);
        } catch (CorruptedWorldException ep) {
            throw new RuntimeException(ep);
        } catch (NewerFormatException ep) {
            throw new RuntimeException(ep);
        } catch (WorldInUseException ep) {
            throw new RuntimeException(ep);
        }
        slimePlugin.generateWorld(Japan_World);
        Location locBoss = new Location(Bukkit.getWorld("pvp_event"),54,75,74);
        Location locMob = new Location(Bukkit.getWorld("pvp_event"),98,68,74);
        Location locMob1 = new Location(Bukkit.getWorld("pvp_event"),12,68,74);
        Location locMob2 = new Location(Bukkit.getWorld("pvp_event"),-36,60,40);
        Location locMob3 = new Location(Bukkit.getWorld("pvp_event"),-65,60,73);
        Location locMob4 = new Location(Bukkit.getWorld("pvp_event"),-22,61,107);
        Location locMob5 = new Location(Bukkit.getWorld("pvp_event"),54,53,179);
        Location locMob6 = new Location(Bukkit.getWorld("pvp_event"),67,53,148);
        Location locMob7 = new Location(Bukkit.getWorld("pvp_event"),50,55,121);
        Location locMob8 = new Location(Bukkit.getWorld("pvp_event"),31,53,142);
        Location locMob9 = new Location(Bukkit.getWorld("pvp_event"),146,61,110);
        Location locMob10 = new Location(Bukkit.getWorld("pvp_event"),183,60,81);
        Location locMob11 = new Location(Bukkit.getWorld("pvp_event"),153,60,35);
        Location locMob12 = new Location(Bukkit.getWorld("pvp_event"),44,53,-22);
        Location locMob13 = new Location(Bukkit.getWorld("pvp_event"),39,53,26);
        Location locMob14 = new Location(Bukkit.getWorld("pvp_event"),68,55,7);
        Location locMob15 = new Location(Bukkit.getWorld("pvp_event"),99,53,7);
        Location miniBossRed = new Location(Bukkit.getWorld("pvp_event"),133,69,74);
        Location miniBossBlue = new Location(Bukkit.getWorld("pvp_event"),-23,69,74);
        (new BukkitRunnable() {
            @SneakyThrows
            public void run() {
                WorldUtils.spawnMob("1002", locBoss, "0");
                WorldUtils.spawnMob("1001", miniBossBlue, "0");
                WorldUtils.spawnMob("1001", miniBossRed, "0");
                WorldUtils.spawnMob("1000", locMob, "0");
                WorldUtils.spawnMob("1000", locMob1, "0");
                WorldUtils.spawnMob("1000", locMob2, "0");
                WorldUtils.spawnMob("1000", locMob3, "0");
                WorldUtils.spawnMob("1000", locMob4, "0");
                WorldUtils.spawnMob("1000", locMob5, "0");
                WorldUtils.spawnMob("1000", locMob6, "0");
                WorldUtils.spawnMob("1000", locMob7, "0");
                WorldUtils.spawnMob("1000", locMob8, "0");
                WorldUtils.spawnMob("1000", locMob9, "0");
                WorldUtils.spawnMob("1000", locMob10, "0");
                WorldUtils.spawnMob("1000", locMob11, "0");
                WorldUtils.spawnMob("1000", locMob12, "0");
                WorldUtils.spawnMob("1000", locMob13, "0");
                WorldUtils.spawnMob("1000", locMob14, "0");
                WorldUtils.spawnMob("1000", locMob15, "0");
            }
        }).runTaskLater((Plugin) azerot.getInstance(), 20L);
    }

    public void endGame(){
        if(FullPOintsBlue > FullPOintsRed){
            //Победа синих
        }else {
            //Победа красных
        }
        if(FullPOintsRed == FullPOintsBlue){
            //Ничья. никто ничего не получает
        }
    }
}

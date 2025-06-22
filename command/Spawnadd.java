package azerot.azerot.command;

import azerot.azerot.ItemsForGui;
import azerot.azerot.WorldUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import azerot.azerot.azerot;

public class Spawnadd extends AbstractCommand {
    public Spawnadd() {
        super("spawnadd");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        try {
            if (!sender.hasPermission("op")) {
                sender.sendMessage(WorldUtils.worldName() + " проблема 1");
                return;
            }
            if (args.length == 0) {
                sender.sendMessage(WorldUtils.worldName() + " проблема 2" );
                return;
            }
            if (args.length > 0) {
                Player player = Bukkit.getPlayer(sender.getName());
                File file = new File(azerot.getInstance().getDataFolder() + File.separator + "spawners.yml");
                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
                if (args[1].equals("add")) {
                    ((Player)sender).getInventory().addItem(new ItemStack[] { ItemsForGui.entityCreator(args[0]) });
                    return;
                }
                List<Location> locationList = new ArrayList<>();
                Location loc = player.getLocation();
                loc.setX(Math.floor(Double.valueOf(loc.getX()).doubleValue()));
                loc.setY(Math.round(Double.valueOf(loc.getY()).doubleValue()));
                loc.setZ(Math.floor(Double.valueOf(loc.getZ()).doubleValue()));
                if(azerot.getLocationsMobs().get(Integer.valueOf(args[0])) == null || Integer.parseInt(args[1]) == 1){
                    locationList.add(0, loc);
                    locationList.add(1, loc);
                    azerot.getLocationsMobs().put(Integer.valueOf(args[0]),  locationList);
                    Bukkit.broadcastMessage("Это первый моб");
                }else{
                    azerot.getLocationsMobs().get(Integer.valueOf(args[0])).add(Integer.parseInt(args[1]), loc);
                    Bukkit.broadcastMessage("Это второй моб");
                }
                try {
                    WorldUtils.spawnMob(args[0], loc, args[1]);
                    player.sendMessage(WorldUtils.worldName() + " успех" );
                            yamlConfiguration.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        } catch (Exception exception) {
            sender.sendMessage(WorldUtils.worldName() + " проблема 3" );
            exception.printStackTrace();
        }
    }
}

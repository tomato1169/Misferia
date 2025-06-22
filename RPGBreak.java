package azerot.azerot;

import azerot.azerot.questsScripts.ThreePatch;
import azerot.azerot.script.alchemist;
import io.netty.util.internal.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class RPGBreak implements Listener {
    Location location = new Location(Bukkit.getWorld("world"), -4394, 82, 3889);
Random random = new Random();


    @EventHandler
    public void povodok(BlockBreakEvent event) throws SQLException {
        Bukkit.broadcastMessage("" + event.getBlock().getLocation());
        rpgplayer p = rpgplayer.getRPGPlayer(event.getPlayer());
        Block block = event.getBlock();
        if(Objects.equals(block.getLocation(), new Location(Bukkit.getWorld("realm2"), -1734, 137, 674))){
            if(block.getType().equals(Material.TRIPWIRE_HOOK)){
                if(p.getQuestsStage().get("9") != null && p.getQuestsStage().get("9") == 3){
                    p.getQuestsStage().put("9",4);
                    ThreePatch.ScriptsForThreePatch(p.toPlayer(), "9","0",false, 0);
                    ItemStack item = new RPGItem().getRPGItem(300, 0, 1,  0, 0,0,0,0, p);
                    p.toPlayer().getInventory().addItem(item);
                }
            }

        }
    }


    @EventHandler
    public void ScriptClassik(PlayerInteractEvent e) {
        rpgplayer p = rpgplayer.getRPGPlayer(e.getPlayer());
        Block block = e.getClickedBlock();
        if(block == null || e.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        if (block.getType().equals(Material.GRAVEL)) {
            String[] droplist = { "52 0 2 100 5", "53 1 1 15 10", "63 1 1 0.5 15" };
            miningReward(e.getPlayer(), droplist);
            ItemStack hand = p.toPlayer().getInventory().getItemInMainHand();
            if(hand.getItemMeta().getDisplayName().equals("Кирка")) {
                p.toPlayer().getInventory().remove(hand);
                p.toPlayer().getInventory().addItem(new ItemStack[]{(new RPGItem()).getRPGItem(22, 0, 1,  0, 0,0, 0,0,p)});
                ItemStack item = new RPGItem().getRPGItem(102, 0, 1,  0, 0,0,0,0, p);
                p.toPlayer().getInventory().setItemInMainHand(item);
            }


        }
        if (block.getType().equals(Material.IRON_ORE)){
            if(azerot.getLocationsBrakeFirst().contains(block.getLocation())){
                String[] droplist = { "109 0 3 25", "110 1 4 70", "111 1 3 35" };
                miningReward(e.getPlayer(), droplist);
                onRespawn(block.getLocation());
            }
        }
    }

    public void onRespawn(Location loc){
        Location location = loc.clone();
        location.getBlock().setType(Material.COBBLESTONE);
        (new BukkitRunnable() {
            public void run() {
                location.getBlock().setType(Material.IRON_ORE);
            }
        }).runTaskLater((Plugin)azerot.getInstance(), 6000L);
    }

    public void miningReward(Player p, String[] droplist) {
        rpgplayer player = rpgplayer.getRPGPlayer(p);
        for (String i : droplist) {
            String[] it = i.trim().split(" ");
            float dropid = Float.valueOf(Float.valueOf(it[0]).floatValue()).floatValue();
            float minamount = Float.valueOf(Float.valueOf(it[1]).floatValue()).floatValue();
            float maxamount = Float.valueOf(Float.valueOf(it[2]).floatValue()).floatValue();
            double chance = Double.valueOf(Double.valueOf(it[3]).doubleValue()).doubleValue();
            Random random1 = new Random();
            ItemStack item = null;
            if (random1.nextInt(ThreadLocalRandom.current().nextInt(1, 100)) <= chance) {
                item = (new RPGItem()).getRPGItem((int)dropid, 0, ((maxamount > 1.0F) ? (int) (Math.random() * maxamount + minamount) : (int) minamount), 0, 0, 0,0, 0, rpgplayer.getRPGPlayer(p));
                if (item != null && item.getAmount() > 0) {
                    p.sendMessage(WorldUtils.worldName() + " §aДобыто " + RPGItemContainer.getRPGItemContainer((int) dropid).getName() + " §fx" + item.getAmount());
                    p.getInventory().addItem(new ItemStack[] { item });
                }
            }
        }
    }
}

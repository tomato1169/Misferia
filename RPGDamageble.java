package azerot.azerot;

import azerot.azerot.events.PVP_JAPAN_Event;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class RPGDamageble {
    public int health;

    public int maxhealth;

    public void RpgDamageable(Entity entity) {
        this.health = this.maxhealth = entity.hasMetadata("health") ? ((MetadataValue)entity.getMetadata("health").get(0)).asInt() : ((entity instanceof Player) ? 20 : 1);
        this.maxhealth = entity.hasMetadata("maxhealth") ? ((MetadataValue)entity.getMetadata("maxhealth").get(0)).asInt() : ((entity instanceof Player) ? 20 : 1);
    }

    public static void damage(Entity entity, double damage, String type, String nameDamager) {
        Player player = Bukkit.getPlayer(entity.getName());
        rpgplayer p = rpgplayer.getRPGPlayer(player);
        if (p == null)
            return;
        int amount = (int) Math.max(p.getHealth() - damage, 0);
        setHealth(entity, amount, type, nameDamager);
        p.getBuff().put("CT", System.currentTimeMillis() + 9000L);
        if (damage > 0) {
            if (p.getSettingsDamage())
                player.sendMessage("§c-" + damage + "♥");
            Handler.unmount(player);
        }
    }



    public static void setHealth(Entity entity, double amount, String type, String nameDamager) {
        Player player = Bukkit.getPlayer(entity.getName());
        rpgplayer p = rpgplayer.getRPGPlayer(player);
        if (p == null)
            return;
        if (amount <= 0) {
            death(entity, type, nameDamager);
            return;
        }
        entity.setMetadata("health", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), Integer.valueOf((int) Math.min(amount, p.getMaxHealth()))));
        double percenthealth = p.getHealth() / p.getMaxHealth() * 100.0D;
        double mathhealth = Math.max(1.0D, 0.2D * percenthealth);
        player.setHealth(mathhealth);
    }

    public static void setMana(Entity entity, int amount) {
        Player player = Bukkit.getPlayer(entity.getName());
        rpgplayer p = rpgplayer.getRPGPlayer(player);
        if (p == null)
            return;
        if (p.getCurseMana() > 0 &&
                p.getMana() <= amount)
            return;
        player.setMetadata("mana", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), Math.min(amount, p.getMaxMana())));
    }


    public static void death(Entity entity, String type, String nameDamager) {
        final Player player = Bukkit.getPlayer(entity.getName());
        final rpgplayer p = rpgplayer.getRPGPlayer(player);
        if (p == null)
            return;
        if(p.getTalents().get("SecondChance") != null && p.getTalents().get("SecondChance") > 0){
            if(p.getSpellcooldown().get("SecondChance") == null || p.getSpellcooldown().get("SecondChance") <= System.currentTimeMillis()){
                setHealth(player, 100, null, null);
                player.sendMessage(WorldUtils.worldName() + " §2Сработал второй шанс!");
                p.getSpellcooldown().put("SecondChance", (System.currentTimeMillis() + 300000L));
                return;
            }

        }
            MobSpells.cancellSpells(player, "all");
            setHealth((Entity) player, p.getMaxHealth(), null, null);
            ArrayList<String> text = new ArrayList<>();
            p.setXP((int) (p.getXP() - p.getXP() * 0.01D));
            p.setNowDie(true);
            player.setHealth(20.0D);
            player.sendMessage("§4§lВы погибли! Потерян 1% опыта");
            if (p.getUnwantedPlayer() >= System.currentTimeMillis()) {
                player.sendMessage("");
                player.sendMessage("");
                for (int i = 9; i < 36; i++) {
                    ItemStack item = player.getInventory().getItem(i);
                    if (ItemStat.getSoulbound(ItemStat.getID(item)) == 0 &&
                            item != null) {
                        player.getLocation().getWorld().dropItem(player.getLocation(), item);
                        item.setAmount(0);
                    }
                }
            }
            String death1 = "§cСмерть";
            String death2 = "";
            if(p.isPVP_Japan_active()){
                if (type != null &&
                        type.equals("player")) {
                    if(Bukkit.getPlayer(nameDamager) != null){
                        if(p.isPVP_Japan_active()){
                            String team = p.getPVP_JAPAN_Event_Team();
                            if(team.equals("red")){
                                PVP_JAPAN_Event.getGames("1-1").getBlueTeam().put(nameDamager, PVP_JAPAN_Event.getGames("1-1").getBlueTeam().get(nameDamager) + 1);
                            }
                            if(team.equals("blue")){
                                PVP_JAPAN_Event.getGames("1-1").getRedTeam().put(nameDamager, PVP_JAPAN_Event.getGames("1-1").getRedTeam().get(nameDamager) + 1);
                            }
                        }
                    }
                }
                player.sendTitle(death1, death2, 10, 70, 20);
                for (PotionEffect playereffect : player.getActivePotionEffects()) {
                    if (!playereffect.getType().equals(PotionEffectType.INCREASE_DAMAGE))
                        player.removePotionEffect(playereffect.getType());
                }
                String team = p.getPVP_JAPAN_Event_Team();
                Handler.unmount(player);
                player.setFoodLevel(20);
                player.setGameMode(GameMode.SPECTATOR);
                player.setFlySpeed(0.0F);
                player.setWalkSpeed(0.0F);
                player.setVelocity(new Vector(0.0D, 1.3D, 0.0D));
                final Location l = player.getLocation();
                (new BukkitRunnable() {
                    public void run() {
                        cancel();
                        if (!player.isOnline()){
                            cancel();
                            return;
                        }
                        Location loc = null;
                        if(team.equals("red")){
                            loc = new Location(Bukkit.getWorld("pvp_event"),224, 60, 74);
                        }else if (team.equals("blue")) {
                            loc = new Location(Bukkit.getWorld("pvp_event"),-114, 60, 74);
                        }
                        player.teleport(loc);
                        player.setGameMode(GameMode.SURVIVAL);
                        player.setFlySpeed(0.2F);
                        player.setWalkSpeed(0.2F);
                        p.setNowDie(false);
                    }
                }).runTaskTimer((Plugin) azerot.getPlugin(azerot.class), 90L, 0L);

            }else{
                if (type != null &&
                        type.equals("player")) {
                        player.sendMessage("§4Вы ослаблены на 5 минут! Урон по игрокам уменьшен");
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6000, 0, true, false), true);
                        death2 = "§4Вы ослаблены!";
                    }
                player.sendTitle(death1, death2, 10, 70, 20);
                for (PotionEffect playereffect : player.getActivePotionEffects()) {
                    if (!playereffect.getType().equals(PotionEffectType.INCREASE_DAMAGE))
                        player.removePotionEffect(playereffect.getType());
                }
                Handler.unmount(player);
                player.setFoodLevel(20);
                player.setGameMode(GameMode.SPECTATOR);
                player.setFlySpeed(0.0F);
                player.setWalkSpeed(0.0F);
                player.setVelocity(new Vector(0.0D, 1.3D, 0.0D));
                final Location l = player.getLocation();
                (new BukkitRunnable() {
                    public void run() {
                        cancel();
                        if (!player.isOnline()){
                            cancel();
                            return;
                        }
                        Location loc = player.getLocation();
                        player.teleport(RPGLocations.nearestLoc(l, rpgplayer.getRPGPlayer(player)));
                        player.setGameMode(GameMode.SURVIVAL);
                        player.setFlySpeed(0.2F);
                        player.setWalkSpeed(0.2F);
                        p.setNowDie(false);
                    }
                }).runTaskTimer((Plugin) azerot.getPlugin(azerot.class), 30L, 0L);
                }
            }


    }



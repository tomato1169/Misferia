package azerot.azerot;

import azerot.azerot.mobs.MobSystem;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.LibsDisguises;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import javax.swing.plaf.basic.BasicIconFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class SpellScript {


    public static void SpellScript(final Player caster, final ItemStack item, final int spellid, int type) throws SQLException {
        final Player player = caster;
        final rpgplayer p = rpgplayer.getRPGPlayer(player);
        final HashMap<Integer, Integer> statsBeforeSpell = new HashMap<>();
        Location location = p.toPlayer().getLocation();
        int radius = 0;
        int alertId = 0;
        if (type == 1) {
            Vector velocity;
            switch (spellid) {
                case 1:
                    if(p.getBuff().get("CDJump") == null || p.getBuff().get("CDJump") <= System.currentTimeMillis()){
                        if(WorldUtils.checkItems(p, 1001, 0) != 0){
                            caster.sendMessage(WorldUtils.worldName() + " §4Вы не можете использовать прыжок, пока у вас в инвентаре находится сундук");
                            return;
                        }
                        velocity = caster.getLocation().getDirection().normalize().multiply(1.5D).setY(0.5D);
                        caster.setVelocity(velocity);
                        p.getBuff().put("CDJump", System.currentTimeMillis() + 1000L);
                    }
                    break;
                case 4:

                    break;
                case 2:
                    DisguiseAPI.disguiseEntity(player, new MobDisguise(DisguiseType.PIG));
                    break;

                case 5:
                    radius = 5;

                    for(Entity entity : location.getWorld().getNearbyEntities(location, radius, radius, radius)) {
                        if(entity.getType() == EntityType.PLAYER){
                            if(entity != caster) {
                                RPGDamageble.damage(entity, 100, null, null);
                            }
                        }else {
                            return;
                        }
                    }
                    break;
                case 3:


            }
        }

        }
    }

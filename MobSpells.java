package azerot.azerot;

import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import de.slikey.effectlib.effect.SphereEffect;
import de.slikey.effectlib.effect.StarEffect;
import java.util.HashMap;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class MobSpells {
    public static void cold(Entity entity, Player target) {
        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1, true, false), true);
        if (rpgplayer.getRPGPlayer(target).getPLevel() < 10) {
            RPGDamageble.damage((Entity)target, 3, null, null);
        } else {
            RPGDamageble.damage((Entity)target, (int)(rpgplayer.getRPGPlayer(target).getMaxHealth() * 0.015D), null, null);
        }
        target.sendMessage("§9Вы скованы!");
    }

    public static void brokenweapon(Player target) {
        if (target == null)
            return;
        ItemStack[] contents = target.getInventory().getContents();
        Random r = new Random();
        ItemStack hand = target.getInventory().getItemInMainHand();
        if (hand == null || hand.getType() == Material.GOLD_NUGGET)
            return;
        int slot_id = r.nextInt(36);
        ItemStack selected = contents[slot_id];
        if (selected == hand)
            return;
        if (slot_id == 7 || slot_id == 8)
            return;
        target.getInventory().setItemInMainHand(selected);
        target.getInventory().setItem(slot_id, hand);
        target.sendMessage("§8У вас выбили оружие из рук!");
    }

    public static void curseBlood(Entity entity, final Player target, int delay, double power) {
        rpgplayer p = rpgplayer.getRPGPlayer(target);
        SphereEffect sphereEffect = new SphereEffect(azerot.getEffectManager());
        sphereEffect.setEntity((Entity)target);
        sphereEffect.iterations = 20;
        sphereEffect.start();
        p.setCurseBlood(power);
        target.sendMessage("§4На вас наложено проклятие крови, вы получаете урон за каждую нанесенную атаку!");
        final String tname = "curseBlood-" + target.getUniqueId();
        new Task(tname, 1, delay, 50) {
            public void onTick() {
                if (!target.isOnline()) {
                    Task.getTask(tname).cancel();
                    return;
                }
                MobSpells.cancellSpells(target, "curseBlood");
            }
        };
    }

    public static void curseFear(Entity entity, final Player target, int delay, int percentStats) {
        rpgplayer p = rpgplayer.getRPGPlayer(target);
        StarEffect starEffect = new StarEffect(azerot.getEffectManager());
        starEffect.setEntity((Entity)target);
        starEffect.particle = Particle.END_ROD;
        starEffect.iterations = 1;
        starEffect.spikeHeight = 0.5F;
        starEffect.spikesHalf = 2;
        starEffect.start();
        p.setCurseFear(1);
        p.resetArmor();
        target.sendMessage("§4На вас наложен страх, ваши характеристики упали на " + percentStats + "%");
        final String tname = "curseFear-" + target.getUniqueId();
        new Task(tname, 1, delay, 50) {
            public void onTick() {
                if (!target.isOnline()) {
                    Task.getTask(tname).cancel();
                    return;
                }
                MobSpells.cancellSpells(target, "curseFear");
            }
        };
    }

    public static void curseDamage(Entity entity, final Player target, int delay, double power) {
        rpgplayer p = rpgplayer.getRPGPlayer(target);
        SphereEffect sphereEffect = new SphereEffect(azerot.getEffectManager());
        sphereEffect.setEntity((Entity)target);
        sphereEffect.iterations = 5;
        sphereEffect.particle = Particle.FLAME;
        sphereEffect.start();
        p.setCurseDamage(power);
        target.sendMessage("");
        final String tname = "curseDamage-" + target.getUniqueId();
        new Task(tname, 1, delay, 50) {
            public void onTick() {
                if (!target.isOnline()) {
                    Task.getTask(tname).cancel();
                    return;
                }
                MobSpells.cancellSpells(target, "curseDamage");
            }
        };
    }

    public static void curseMovement(Entity entity, final Player target, int delay, int power) {
        final rpgplayer p = rpgplayer.getRPGPlayer(target);
        if (p.getCurseMovement() > 0.0D)
            return;
        p.setCurseMovement(power);
        target.sendMessage("");
        final String tname = "curseMovement-" + target.getUniqueId();
        new Task(tname, 1, delay, 50) {
            public void onTick() {
                if (!target.isOnline()) {
                    Task.getTask(tname).cancel();
                    return;
                }
                MobSpells.cancellSpells(target, "curseMovement");
            }
        };
        new Task("damage" + tname, -1, 500, 500) {
            public void onTick() {
                if (!target.isOnline() || p.getCurseMovement() == 0.0D || rpgplayer.getRPGPlayer(target) == null) {
                    if (Task.getTask("damage" + tname) != null)
                        Task.getTask("damage" + tname).cancel();
                    return;
                }
                Location loc1 = target.getLocation();
                if (rpgplayer.getRPGPlayer(target).getCurseMovementLoc() == null) {
                    rpgplayer.getRPGPlayer(target).setCurseMovementLoc(target.getLocation());
                    return;
                }
                Location loc2 = rpgplayer.getRPGPlayer(target).getCurseMovementLoc();
                String sloc1 = "" + loc1.getX() + " " + loc1.getY() + " " + loc1.getZ();
                String sloc2 = "" + loc2.getX() + " " + loc2.getY() + " " + loc2.getZ();
                if (sloc1.equals(sloc2)) {
                    int damage = (int)(rpgplayer.getRPGPlayer(target).getMaxHealth() * p.getCurseMovement() / 100.0D);
                    RPGDamageble.damage((Entity)target, damage, null, null);
                    target.damage(0.1D);
                }
                rpgplayer.getRPGPlayer(target).setCurseMovementLoc(target.getLocation());
            }
        };
    }

    public static void curseRevenge(Entity entity, final Player target, int delay, double power) {
        final rpgplayer p = rpgplayer.getRPGPlayer(target);
        if (p.getCurseRevenge() > 0.0D)
            return;
        p.setCurseRevenge(power);
        target.sendMessage("");
        final String tname = "curseRevenge-" + target.getUniqueId();
        new Task(tname, 1, delay, 50) {
            public void onTick() {
                if (!target.isOnline()) {
                    Task.getTask(tname).cancel();
                    return;
                }
                MobSpells.cancellSpells(target, "curseRevenge");
            }
        };
        new Task("damage" + tname, -1, 700, 700) {
            public void onTick() {
                if ((!target.isOnline() || p.getCurseRevenge() == 0.0D || rpgplayer.getRPGPlayer(target) == null) &&
                        Task.getTask("damage" + tname) != null)
                    Task.getTask("damage" + tname).cancel();
                if (rpgplayer.getRPGPlayer(target).getCurseRevengeTime() == 0.0D) {
                    int damage = (int)(rpgplayer.getRPGPlayer(target).getMaxHealth() * p.getCurseRevenge() / 100.0D);
                    RPGDamageble.damage((Entity)target, damage, null, null);
                    target.damage(0.1D);
                }
                rpgplayer.getRPGPlayer(target).setCurseRevengeTime(0);
            }
        };
    }

    public static void curseSilence(Entity entity, final Player target, int delay) {
        rpgplayer p = rpgplayer.getRPGPlayer(target);
        SphereEffect sphereEffect = new SphereEffect(azerot.getEffectManager());
        sphereEffect.setEntity((Entity)target);
        sphereEffect.iterations = 20;
        sphereEffect.start();
        p.setCurseSilence(1);
        target.sendMessage("");
        final String tname = "curseSilence-" + target.getUniqueId();
        new Task(tname, 1, delay, 50) {
            public void onTick() {
                if (!target.isOnline()) {
                    Task.getTask(tname).cancel();
                    return;
                }
                MobSpells.cancellSpells(target, "curseSilence");
            }
        };
    }

    public static void curseMana(Entity entity, final Player target, int delay) {
        rpgplayer p = rpgplayer.getRPGPlayer(target);
        SphereEffect sphereEffect = new SphereEffect(azerot.getEffectManager());
        sphereEffect.setEntity((Entity)target);
        sphereEffect.iterations = 20;
        sphereEffect.particle = Particle.SPELL_WITCH;
        sphereEffect.start();
        p.setCurseMana(1);
        target.sendMessage("");
        final String tname = "curseMana-" + target.getUniqueId();
        new Task(tname, 1, delay, 50) {
            public void onTick() {
                if (!target.isOnline()) {
                    Task.getTask(tname).cancel();
                    return;
                }
                MobSpells.cancellSpells(target, "curseMana");
            }
        };
    }

    public static void curseCorrosion(Entity entity, final Player target, int delay) {
        rpgplayer p = rpgplayer.getRPGPlayer(target);
        SphereEffect sphereEffect = new SphereEffect(azerot.getEffectManager());
        sphereEffect.setEntity((Entity)target);
        sphereEffect.iterations = 5;
        sphereEffect.particle = Particle.VILLAGER_HAPPY;
        sphereEffect.start();
        p.setCurseCorrosion(1);
        target.sendMessage("");
        final String tname = "curseCorrosion-" + target.getUniqueId();
        new Task(tname, 1, delay, 50) {
            public void onTick() {
                if (!target.isOnline()) {
                    Task.getTask(tname).cancel();
                    return;
                }
                MobSpells.cancellSpells(target, "curseCorrosion");
            }
        };
    }

    public static void curseVortex(Entity entity, final Player target, int delay, final int damage) {
        final rpgplayer p = rpgplayer.getRPGPlayer(target);
        p.setVortex(damage);
        final String tname = "vortex-" + target.getUniqueId();
        new Task(tname, 1, delay, 50) {
            public void onTick() {
                if (!target.isOnline()) {
                    Task.getTask(tname).cancel();
                    return;
                }
                MobSpells.cancellSpells(target, "vortex");
            }
        };
        new Task("damage" + tname, -1, 0, 250) {
            public void onTick() {
                if (!target.isOnline() || p.getVortex() == 0) {
                    Task.getTask("damage" + tname).cancel();
                    return;
                }
                target.setVelocity(MobSpells.getVortexVec(p.getVortexVec()));
                p.setVortexVec(p.getVortexVec() + 1);
                if (p.getVortexVec() > 3)
                    p.setVortexVec(0);
                RPGDamageble.damage((Entity)target, damage, null, null);
                target.damage(0.1D);
            }
        };
    }


    public static Vector getVortexVec(int last) {
        switch (last) {
            case 0:
                return new Vector(0.0D, 0.25D, 1.0D);
            case 1:
                return new Vector(-1.0D, 0.25D, 0.0D);
            case 2:
                return new Vector(0.0D, 0.25D, -1.0D);
        }
        return new Vector(1.0D, 0.25D, 0.0D);
    }

    public static void cancellSpells(Player target, String spell) {
        rpgplayer p = rpgplayer.getRPGPlayer(target);
        switch (spell) {
            case "curseBlood":
                if (p.getCurseBlood() > 0.0D) {
                    p.setCurseBlood(0.0D);
                    target.sendMessage("§aВы больше не прокляты кровью.");
                            String str = "curseBlood-" + target.getUniqueId();
                    if (Task.getTask(str) != null)
                        Task.getTask(str).cancel();
                }
                return;
            case "curseDamage":
                if (p.getCurseDamage() > 0.0D) {
                    p.setCurseDamage(0.0D);
                    target.sendMessage("");
                            String str = "curseDamage-" + target.getUniqueId();
                    if (Task.getTask(str) != null)
                        Task.getTask(str).cancel();
                }
                return;
            case "curseMovement":
                if (p.getCurseMovement() > 0.0D) {
                    p.setCurseMovement(0.0D);
                    target.sendMessage("");
                }
               String tname = "curseMovement-" + target.getUniqueId();
                if (Task.getTask(tname) != null)
                    Task.getTask(tname).cancel();
                if (Task.getTask("damagecurseMovement-" + target.getUniqueId()) != null)
                    Task.getTask("damagecurseMovement-" + target.getUniqueId()).cancel();
                return;
            case "curseFear":
                if (p.getCurseFear() > 0) {
                    p.setCurseFear(0);
                    target.sendMessage("§aСтрах прошел...");
                    p.resetArmor();
                            tname = "curseFear-" + target.getUniqueId();
                    if (Task.getTask(tname) != null)
                        Task.getTask(tname).cancel();
                }
                return;
            case "curseRevenge":
                if (p.getCurseRevenge() > 0.0D) {
                    p.setCurseRevenge(0.0D);
                    target.sendMessage("");
                }
                tname = "curseRevenge-" + target.getUniqueId();
                if (Task.getTask(tname) != null)
                    Task.getTask(tname).cancel();
                if (Task.getTask("damagecurseRevenge-" + target.getUniqueId()) != null)
                    Task.getTask("damagecurseRevenge-" + target.getUniqueId()).cancel();
                return;
            case "curseSilence":
                if (p.getCurseSilence() > 0) {
                    p.setCurseSilence(0);
                    target.sendMessage("");
                            tname = "curseSilence-" + target.getUniqueId();
                    if (Task.getTask(tname) != null)
                        Task.getTask(tname).cancel();
                }
                return;
            case "curseMana":
                if (p.getCurseMana() > 0) {
                    p.setCurseMana(0);
                    target.sendMessage("");
                            tname = "curseMana-" + target.getUniqueId();
                    if (Task.getTask(tname) != null)
                        Task.getTask(tname).cancel();
                }
                return;
            case "curseCorrosion":
                if (p.getCurseCorrosion() > 0) {
                    p.setCurseCorrosion(0);
                    target.sendMessage("");
                            tname = "curseCorrosion-" + target.getUniqueId();
                    if (Task.getTask(tname) != null)
                        Task.getTask(tname).cancel();
                }
                return;
            case "vortex":
                if (p.getVortex() > 0) {
                    p.setVortex(0);
                    tname = "vortex-" + target.getUniqueId();
                    if (Task.getTask(tname) != null)
                        Task.getTask(tname).cancel();
                }
                if (Task.getTask("damagevortex-" + target.getUniqueId()) != null)
                    Task.getTask("damagevortex-" + target.getUniqueId()).cancel();
                return;
        }
        String tname = "tickdamage-" + target.getUniqueId();
        if (Task.getTask(tname) != null)
            Task.getTask(tname).cancel();
        if (p.getCurseBlood() > 0.0D) {
            p.setCurseBlood(0.0D);
            target.sendMessage("§aВы больше не прокляты кровью.");
                    tname = "curseBlood-" + target.getUniqueId();
            if (Task.getTask(tname) != null)
                Task.getTask(tname).cancel();
        }
        if (p.getCurseDamage() > 0.0D) {
            p.setCurseDamage(0.0D);
            target.sendMessage("");
                    tname = "curseDamage-" + target.getUniqueId();
            if (Task.getTask(tname) != null)
                Task.getTask(tname).cancel();
        }
        if (p.getCurseMovement() > 0.0D) {
            p.setCurseMovement(0.0D);
            target.sendMessage("");
        }
        tname = "curseMovement-" + target.getUniqueId();
        if (Task.getTask(tname) != null)
            Task.getTask(tname).cancel();
        if (Task.getTask("damagecurseMovement-" + target.getUniqueId()) != null)
            Task.getTask("damagecurseMovement-" + target.getUniqueId()).cancel();
        if (p.getCurseFear() > 0) {
            p.setCurseFear(0);
            target.sendMessage("");
                    tname = "curseFear-" + target.getUniqueId();
            if (Task.getTask(tname) != null)
                Task.getTask(tname).cancel();
        }
        if (p.getCurseRevenge() > 0.0D) {
            p.setCurseRevenge(0.0D);
            target.sendMessage("");
        }
        tname = "curseRevenge-" + target.getUniqueId();
        if (Task.getTask(tname) != null)
            Task.getTask(tname).cancel();
        if (Task.getTask("damagecurseRevenge-" + target.getUniqueId()) != null)
            Task.getTask("damagecurseRevenge-" + target.getUniqueId()).cancel();
        if (p.getCurseSilence() > 0) {
            p.setCurseSilence(0);
            target.sendMessage("");
                    tname = "curseSilence-" + target.getUniqueId();
            if (Task.getTask(tname) != null)
                Task.getTask(tname).cancel();
        }
        if (p.getCurseMana() > 0) {
            p.setCurseMana(0);
            target.sendMessage("");
                    tname = "curseMana-" + target.getUniqueId();
            if (Task.getTask(tname) != null)
                Task.getTask(tname).cancel();
        }
        if (p.getCurseCorrosion() > 0) {
            p.setCurseCorrosion(0);
            target.sendMessage("");
                    tname = "curseCorrosion-" + target.getUniqueId();
            if (Task.getTask(tname) != null)
                Task.getTask(tname).cancel();
        }
        if (p.getVortex() > 0) {
            p.setVortex(0);
            tname = "vortex-" + target.getUniqueId();
            if (Task.getTask(tname) != null)
                Task.getTask(tname).cancel();
        }
        if (Task.getTask("damagevortex-" + target.getUniqueId()) != null)
            Task.getTask("damagevortex-" + target.getUniqueId()).cancel();
    }

    public static void tickdamage(Entity entity, final Player target, final int damage, int period, int duration, String message) {
        if (message != null)
            target.sendMessage(message);
        final String tname = "tickdamage-" + target.getUniqueId();
        if (Task.getTask(tname) != null)
            Task.getTask(tname).cancel();
        new Task("tickdamage-" + target.getUniqueId(), duration, 0, period) {
            public void onTick() {
                if (!target.isOnline()) {
                    Task.getTask(tname).cancel();
                    return;
                }
                RPGDamageble.damage((Entity)target, damage, null, null);
                target.damage(0.1D);
            }
        };
    }

    public static void healthChange(Entity entity, Player target) {
        SphereEffect sphereEffect = new SphereEffect(azerot.getEffectManager());
        sphereEffect.setEntity((Entity)target);
        sphereEffect.iterations = 5;
        sphereEffect.start();
        MobInfo mobInfo = MobInfoManager.getMobInfo(entity);
        int entityhealth = mobInfo.getHealth();
        int targethealth = rpgplayer.getRPGPlayer(target).getHealth();
        MobSystem.setHealth(entity, targethealth);
        RPGDamageble.setHealth((Entity)target, Math.max(1, entityhealth), null, null);
        target.sendMessage("§8С вами поменялись здоровьем!");
    }

    public static void vortex(final Entity entity, final Player target, final int damage, int duration, final double range) {
        String tname = "vortex-spell-" + entity.getUniqueId();
        if (Task.getTask(tname) != null)
            Task.getTask(tname).cancel();
        new Task("vortex-spell-" + entity.getUniqueId(), duration, 0, 250) {
            private final HashMap<String, Vector> center = new HashMap<>();

            private final HashMap<String, Integer> last = new HashMap<>();

            public void onTick() {
                LivingEntity le = (LivingEntity) entity;
                if (le == null || le.isDead()) {
                    cancel();
                    return;
                }
                for (Player p : MobSystem.getNearbyPlayers(le, range)) {
                    Vector current, loc = this.center.get(p.getName());
                    if (loc == null) {
                        loc = p.getLocation().toVector();
                        this.center.put(p.getName(), loc);
                        current = getCurrent(-1);
                        this.last.put(p.getName(), Integer.valueOf(0));
                    } else {
                        int last = ((Integer) this.last.get(p.getName())).intValue();
                        current = getCurrent(last);
                        this.last.put(p.getName(), Integer.valueOf((last + 1) % 4));
                    }
                    p.setVelocity(current);
                    RPGDamageble.damage((Entity) target, damage, null, null);
                    target.damage(0.1D);
                }
            }

            private Vector getCurrent(int last) {
                switch (last) {
                    case 0:
                        return new Vector(0.0D, 0.25D, 1.0D);
                    case 1:
                        return new Vector(-1.0D, 0.25D, 0.0D);
                    case 2:
                        return new Vector(0.0D, 0.25D, -1.0D);
                }
                return new Vector(1.0D, 0.25D, 0.0D);
            }
        };
    }
}

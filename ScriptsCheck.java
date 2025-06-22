package azerot.azerot;

import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.script.*;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.MetadataValue;

import java.sql.SQLException;

public class ScriptsCheck {
    public static void ScriptsOnAttack(Entity entity, Entity target, int damage) throws SQLException {
        String script = MobInfoManager.getMobInfo(entity).getScript();
        if (script == null)
            return;
        String[] scr = script.trim().split(" ");
        if (script.equals("slowness"))
            Slowness.onAttack(entity, target);
        if (scr[0].equals("poison"))
            Poison.onAttack(entity, target, Integer.parseInt(scr[1]), Integer.parseInt(scr[2]));

        if(script.equals("fire"))
            fire.onAttack(entity, target, Integer.parseInt(scr[1]));
        if(script.equals("scriptArсherFor85Mob"))
            scriptArсherFor85Mob.onAttack(entity);

    }

    public static void ScriptsOnDamaged(Entity entity, Entity target, int damage, boolean isBow) throws SQLException {
        String script = MobInfoManager.getMobInfo(entity).getScript();
        if (script == null)
            return;
        switch (script){
            case "souls":
                souls.onDamaged(entity, target, damage);
                break;
            case "classik":
                classik.onDamaged(entity, target, damage);
                break;
            case "alchemist":
                alchemist.onDamaged(entity,target,damage);
                break;
            case "sunOfBoss":
                sunOfBoss.onDamaged(entity,target,damage);
                break;
            case "resinBoss":
                resinBoss.onDamaged(entity,target,damage);
                break;
            case "nightboss":
                nightboss.onDamaged(entity,target,damage);
                break;
            case "naruto":
                naruto.onDamaged(entity, target, damage);
        }


    }

    public static void ScriptsOnRegen(Entity entity, boolean onSpawn) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(entity);
        String script = MobInfoManager.getMobInfo(entity).getScript();
        if (script == null)
            return;

    }

    public static void ScriptsOnSpawn(Entity entity) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(entity);
        String script = MobInfoManager.getMobInfo(entity).getScript();
        if (script == null)
            return;
        if(script.equals("gaze"))
            gaze.onSpawn(entity);
        if(script.equals("alchemist"))
            alchemist.onSpawn(entity);
        if(script.equals("nightboss"))
            nightboss.onSpawn(entity);
        if(script.equals("naruto"))
            naruto.onSpawn(entity);

        ScriptsOnRegen(entity, true);
    }

    public static void ScriptsOnDeath(Entity entity, Entity target) {
        String script = MobInfoManager.getMobInfo(entity).getScript();
        ;
        if (script == null)
            return;
    }
}


package azerot.azerot.RPG;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import org.bukkit.Location;

public class RPGGrave {
    static Map<Integer, RPGGrave> grave = new HashMap<>();

    private int id = 0;

    private String world;

    private int x = 0;

    private int y = 0;

    private int z = 0;

    private float yaw = 0.0F;

    private float pitch = 0.0F;

    private int teleportTo = 0;

    @Getter
    private int requiredQuest = 0;
    @Getter
    private int requiredStage = 0;

    public RPGGrave(ResultSet result) throws SQLException {
        try {
            if (result == null) {
                this.id = 0;
                this.world = "";
                this.x = 0;
                this.y = 0;
                this.z = 0;
                this.yaw = 0;
                this.pitch = 0;
                this.teleportTo = 0;
                this.requiredQuest = 0;
                this.requiredStage = 0;
            } else {
                this.id = result.getInt("id");
                this.world = result.getString("world");
                this.x = result.getInt("x");
                this.y = result.getInt("y");
                this.z = result.getInt("z");
                this.yaw = result.getFloat("yaw");
                this.pitch = result.getFloat("pitch");
                this.teleportTo = result.getInt("teleportTo");
                this.requiredQuest = result.getInt("requiredQuest");
                this.requiredStage = result.getInt("requiredStage");
            }
            grave.put(this.id, this);
        } catch (Throwable $ex) {
            throw $ex;
        }
    }

    public static RPGGrave getRPGGrave(int id) {
        return grave.get(Integer.valueOf(id));
    }

    public static Map<Integer, RPGGrave> getRPGGrave() {
        return grave;
    }

    public String getWorld() {
        return this.world;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public int getTeleportTo() {
        return this.teleportTo;
    }
}
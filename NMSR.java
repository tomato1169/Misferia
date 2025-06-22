package azerot.azerot;


import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityTypes;
import net.minecraft.server.v1_12_R1.MinecraftKey;

public class NMSR {
    public static void registerEntity(String name, int type, Class<? extends Entity> customClass) {
        registerEntityKey(name, type, customClass);
    }

    public static void registerEntityKey(String name, int type, Class<? extends Entity> customClass) {
        MinecraftKey key = new MinecraftKey(name);
        EntityTypes.b.a(type, key, customClass);
        if (!EntityTypes.d.contains(key))
            EntityTypes.d.add(key);
    }
}

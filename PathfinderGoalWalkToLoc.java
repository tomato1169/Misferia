package azerot.azerot;

import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagDouble;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagString;
import net.minecraft.server.v1_12_R1.PathfinderGoal;

public class PathfinderGoalWalkToLoc extends PathfinderGoal {
    protected final EntityCreature a;

    protected double b;

    protected double c;

    protected double d;

    protected final double e;

    protected int f;

    protected boolean g;

    public PathfinderGoalWalkToLoc(EntityCreature paramEntityCreature, double x, double y, double z, double speed) {
        this(paramEntityCreature, x, y, z, speed, 5);
    }

    public PathfinderGoalWalkToLoc(EntityCreature paramEntityCreature, double x, double y, double z, double speed, int timeBetweenMovement) {
        this.a = paramEntityCreature;
        this.e = speed;
        this.b = x;
        this.c = y;
        this.d = z;
        this.f = timeBetweenMovement;
        a(1);
    }

    public boolean a() {
        if (!this.g) {
            if (this.a.bW() >= 100)
                return false;
            if (this.a.getRandom().nextInt(this.f) != 0)
                return false;
        }
        this.g = false;
        return true;
    }

    public boolean b() {
        return !this.a.getNavigation().o();
    }

    public void c() {
        if(this.a != null){
            this.a.getNavigation().a(this.b, this.c, this.d, this.e);
        }
    }

    public void i() {
        this.g = true;
    }

    public void setTimeBetweenMovement(int paramInt) {
        this.f = paramInt;
    }

    public static void setFollowRange(Entity entity, double followRange) {
        setOrAdAttribute(entity, "generic.followRange", followRange);
    }

    public static void setOrAdAttribute(Entity entity, String name, double base) {
        NBTTagList attributeList;
        NBTTagCompound compound = new NBTTagCompound();
        entity.c(compound);
        NBTTagCompound attribute = new NBTTagCompound();
        attribute.set("Name", (NBTBase)new NBTTagString(name));
        attribute.set("Base", (NBTBase)new NBTTagDouble(base));
        if (compound.hasKey("Attributes")) {
            attributeList = compound.getList("Attributes", 10);
            for (int compt = 0; compt < attributeList.size(); compt++) {
                if (attributeList.get(compt).get("Name").equals(attribute.get("Name")))
                    attributeList.remove(compt);
            }
            attributeList.add((NBTBase)attribute);
        } else {
            attributeList = new NBTTagList();
            attributeList.add((NBTBase)attribute);
        }
        compound.set("Attributes", (NBTBase)attributeList);
        ((EntityLiving)entity).a(compound);
    }
}

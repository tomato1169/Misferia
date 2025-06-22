package azerot.azerot;

import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PathfinderGoalMeleeAttack extends PathfinderGoal {
    World a;

    protected EntityCreature b;

    protected int c;

    double d;

    boolean e;

    PathEntity f;

    private int h;

    private double i;

    private double j;

    private double k;

    private long attackTime = 0L;

    public PathfinderGoalMeleeAttack(EntityCreature entitycreature, double d0, boolean flag) {
        this.b = entitycreature;
        this.a = entitycreature.world;
        this.d = d0;
        this.e = flag;
        a(3);
    }

    public boolean a() {
        EntityLiving var1 = this.b.getGoalTarget();
        if (var1 == null)
            return false;
        if (!var1.isAlive())
            return false;
        this.f = this.b.getNavigation().a((Entity)var1);
        if (this.f != null)
            return true;
        return (a(var1) >= this.b.d(var1.locX, (var1.getBoundingBox()).b, var1.locZ));
    }

    public boolean b() {
        EntityLiving var1 = this.b.getGoalTarget();
        if (var1 == null)
            return false;
        if (!var1.isAlive())
            return false;
        if (!this.e)
            return !this.b.getNavigation().o();
        if (!this.b.f(new BlockPosition((Entity)var1)))
            return false;
        return (!(var1 instanceof EntityHuman) || (!((EntityHuman)var1).isSpectator() && !((EntityHuman)var1).z()));
    }

    public void c() {
        this.b.getNavigation().a(this.f, this.d);
        this.h = 0;
    }

    public void d() {
        EntityLiving entityliving = this.b.getGoalTarget();
        if (entityliving instanceof EntityHuman && (((EntityHuman)entityliving).isSpectator() || ((EntityHuman)entityliving).z()))
            this.b.setGoalTarget(null);
        this.b.getNavigation().p();
    }

    public void e() {
        EntityLiving var1 = this.b.getGoalTarget();
        if (var1 == null)
            return;
        this.b.getControllerLook().a((Entity)var1, 30.0F, 30.0F);
        double var2 = this.b.d(var1.locX, (var1.getBoundingBox()).b, var1.locZ);
        this.h--;
        if ((this.e || this.b.getEntitySenses().a((Entity)var1)) && this.h <= 0 && ((this.i == 0.0D && this.j == 0.0D && this.k == 0.0D) || var1.d(this.i, this.j, this.k) >= 1.0D || this.b.getRandom().nextFloat() < 0.05F)) {
            this.i = var1.locX;
            this.j = (var1.getBoundingBox()).b;
            this.k = var1.locZ;
            this.h = 4 + this.b.getRandom().nextInt(7);
            if (var2 > 1024.0D) {
                this.h += 10;
            } else if (var2 > 256.0D) {
                this.h += 5;
            }
            if (!this.b.getNavigation().a((Entity)var1, this.d))
                this.h += 15;
        }
        this.c = Math.max(this.c - 1, 0);
        a(var1, var2);
    }

    protected void a(EntityLiving entityliving, double d0) {
        boolean move = (this.b.motX != 0.0D && this.b.motZ != 0.0D);
        MobInfo info = MobInfoManager.getMobInfo(this.b.getBukkitEntity());
        boolean has = canAttack(info, entityliving);
        boolean attack = (this.attackTime + 1000.0D * info.getAttackSpeed() / 20.0D < System.currentTimeMillis());
        if (MobSystem.getTarget(this.b.getBukkitEntity()) != null && has && attack) {
            this.c = move ? 10 : 5;
            this.b.a(EnumHand.MAIN_HAND);
            this.b.B((Entity)entityliving);
            this.attackTime = System.currentTimeMillis();
        }
    }

    protected boolean canAttack(MobInfo info, EntityLiving var1) {
        if (var1 instanceof EntityHuman && ((EntityHuman)var1).isSpectator())
            return false;
        if (!this.b.getWorld().getWorld().getUID().equals(var1.getWorld().getWorld().getUID()))
            return false;
        double range = info.getRange();
        return (var1.getBukkitEntity().getLocation().distance(this.b.getBukkitEntity().getLocation()) <= range);
    }

    protected double a(EntityLiving entityliving) {
        return (this.b.width * 3.0F * this.b.width * 3.0F + entityliving.width);
    }
}

package azerot.azerot;

import azerot.azerot.mobs.MobInfo;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;
import azerot.azerot.mobs.MobInfoManager;

import java.util.Random;

public class PathfinderGoalBow<T extends EntityMonster & IRangedEntity> extends PathfinderGoal {
    private final T a;

    private final double b;

    private int c;

    private final float d;

    private int e = -1;

    private int f;

    private boolean g;

    private boolean h;

    private int i = -1;

    private final boolean skeleton;

    public PathfinderGoalBow(T var1, double var2, int var4, float var5, boolean skeleton) {
        this.a = var1;
        this.b = var2;
        this.c = var4;
        this.d = var5 * var5;
        this.skeleton = skeleton;
        a(3);
    }

    public void b(int var1) {
        this.c = var1;
    }

    public boolean a() {
        return (this.a.getGoalTarget() != null && !this.a.getItemInMainHand().isEmpty() && this.a.getItemInMainHand().getItem() == Items.BOW);
    }

    public boolean b() {
        return ((a() || !this.a.getNavigation().o()) && !this.a.getItemInMainHand().isEmpty() && this.a.getItemInMainHand().getItem() == Items.BOW);
    }

    public void c() {
        super.c();
        if (this.skeleton)
            ((IRangedEntity)this.a).p(true);
    }

    public void d() {
        super.d();
        ((IRangedEntity)this.a).p(false);
        this.f = 0;
        this.e = -1;
        this.a.cN();
    }

    public void e() {
        EntityLiving var1 = this.a.getGoalTarget();

        if (var1 != null) {

            boolean hasBow = !this.a.getItemInMainHand().isEmpty() && this.a.getItemInMainHand().getItem() == Items.BOW;
            boolean var5;
            double var2 = this.a.d(var1.locX, var1.getBoundingBox().b, var1.locZ);
            boolean var4 = this.a.getEntitySenses().a(var1);
            var5 = this.f > 0;
            if (var4 != var5) this.f = 0;
            if (var4) ++this.f;
            else --this.f;

            if (!(var2 > (double) this.d) && this.f >= 20) {
                this.a.getNavigation().p();
                ++this.i;
            } else {
                this.a.getNavigation().a(var1, this.b); // всегда приближается к цели
                this.i = -1;
            }
            if (this.i >= 20) {
                if ((double) this.a.getRandom().nextFloat() < 0.3)
                    this.g = !this.g;
                if ((double) this.a.getRandom().nextFloat() < 0.3)
                    this.h = !this.h;
                this.i = 0;
            }
            if (this.i > -1) {
                if (var2 > (double) (this.d * 0.75f))
                    this.h = false;
                else if (var2 < (double) (this.d * 0.25f))
                    this.h = true;
                this.a.getControllerLook().a(var1, 30.0f, 30.0f);
                this.a.a(var1, 30.0f, 30.0f);
            } else
                this.a.getControllerLook().a(var1, 30.0f, 30.0f);

            
            if (this.a.isHandRaised()) {
                int var6;
                if (!var4 && this.f < -60) this.a.cN();
                else if (var4 && (var6 = this.a.cL()) >= MobInfoManager.getMobInfo(this.a.getBukkitEntity()).getAttackSpeed()) {
                    this.a.cN();
                    // Проверка на наличие лука
                    if (!this.a.getItemInMainHand().isEmpty() && this.a.getItemInMainHand().getItem() == Items.BOW) {
                        if (!this.canAttack(var1)){
                            Projectile projectile = (Projectile)RPGPlayerShoot.spawnProjectileWithShooter(Arrow.class,  this.a.getBukkitEntity());
                            projectile.setVelocity(shoot(var1, ItemBow.b(var6)));
                        }
                        else {
                            this.a.B(var1);
                            Bukkit.broadcastMessage("Милли атака - при луке");
                            //ближний бой, если цель очень близко
                            this.a.c(EnumHand.MAIN_HAND);
                        }
                    } else {
                        if(canAttackMelee(MobInfoManager.getMobInfo(this.a.getBukkitEntity()), var1)){
                            this.a.B(var1);
                            Bukkit.broadcastMessage("Милли атака - при оружии");
                        }
                    }
                }
            } else if (this.f >= -60) this.a.c(EnumHand.MAIN_HAND);
        }
    }

    protected boolean canAttackMelee(MobInfo info, EntityLiving var1) {
        if (var1 instanceof EntityHuman && ((EntityHuman)var1).isSpectator())
            return false;
        double range = info.getRange();
        return (var1.getBukkitEntity().getLocation().distance(this.a.getBukkitEntity().getLocation()) <= range);
    }

    public Vector shoot(EntityLiving entityliving, float f) {
        EntityArrow entityarrow = arrow(f);
        double d0 = entityliving.locX - this.a.locX;
        double d1 = (entityliving.getBoundingBox()).b + (entityliving.length / 3.0F) - entityarrow.locY;
        double d2 = entityliving.locZ - this.a.locZ;
        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (14 - ((EntityMonster)this.a).world.getDifficulty().a() * 4));
        return entityarrow.getBukkitEntity().getVelocity();
    }

    protected EntityArrow arrow(float f) {
        EntityTippedArrow entitytippedarrow = new EntityTippedArrow(((EntityMonster)this.a).world, (EntityLiving)this.a);
        entitytippedarrow.a((EntityLiving)this.a, f);
        return (EntityArrow)entitytippedarrow;
    }

    protected boolean canAttack(EntityLiving var1) {
        double range = 2D;
        return (var1.getBukkitEntity().getLocation().distance(this.a.getBukkitEntity().getLocation()) <= range);
    }
}

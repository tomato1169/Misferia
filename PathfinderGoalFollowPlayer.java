package azerot.azerot;

import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PathfinderGoal;

public class PathfinderGoalFollowPlayer extends PathfinderGoal {

    private EntityInsentient entity;
    private EntityPlayer Target;
    private double speed;
    private float distanceSquared;

    public PathfinderGoalFollowPlayer(EntityInsentient entity, EntityPlayer target, double speed, float distance) {
        this.entity = entity;
        this.Target = target;
        this.speed = speed;
        this.distanceSquared = distance * distance;
        this.a(3);
    }

    @Override
    public boolean a() {
        return (Target != null && Target.isAlive() && this.entity.h(Target) > (double)distanceSquared);
    }

    @Override
    public void e() {
        this.entity.getNavigation().a(Target, this.speed);
    }
}
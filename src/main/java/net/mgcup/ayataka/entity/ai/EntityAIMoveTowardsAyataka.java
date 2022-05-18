package net.mgcup.ayataka.entity.ai;

import net.mgcup.ayataka.entity.EntityAyataka;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityAIMoveTowardsAyataka extends EntityAIBase {
    private final EntityCreature creature;
    private EntityAyataka targetEntity;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private final double speed;

    public EntityAIMoveTowardsAyataka(EntityCreature creature, double speedIn)
    {
        this.creature = creature;
        this.speed = speedIn;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        World world = this.creature.world;

        if (this.targetEntity == null || !this.targetEntity.isEntityAlive()) {
            return false;
        }

        Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.creature, 32, 8, new Vec3d(this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ));

        if (vec3d == null) {
            return false;
        }
        else {
            this.movePosX = vec3d.x;
            this.movePosY = vec3d.y;
            this.movePosZ = vec3d.z;
            return true;
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.targetEntity != null && this.targetEntity.isEntityAlive();
    }

    @Override
    public void resetTask() {
        this.targetEntity = null;
    }

    @Override
    public void startExecuting() {
        this.creature.getNavigator().tryMoveToEntityLiving(this.targetEntity, this.speed);
    }

    public void setTarget(@Nullable EntityAyataka targetIn) {
        this.targetEntity = targetIn;
    }

    @Nullable
    public EntityAyataka getTarget() {
        return this.targetEntity;
    }
}

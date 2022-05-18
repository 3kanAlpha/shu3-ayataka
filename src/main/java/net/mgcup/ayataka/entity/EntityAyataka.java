package net.mgcup.ayataka.entity;

import net.mgcup.ayataka.AyatakaMod;
import net.mgcup.ayataka.entity.ai.EntityAIMoveTowardsAyataka;
import net.mgcup.ayataka.init.ModItems;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EntityAyataka extends EntityArrow {
    private List<EntityAIMoveTowardsAyataka> ais;

    private static final int ALIVE_TIME = 200;

    public EntityAyataka(World worldIn) {
        super(worldIn);
    }

    public EntityAyataka(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        this.pickupStatus = PickupStatus.DISALLOWED;
    }

    public EntityAyataka(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.ticksExisted % 10 == 0) {
            if (!this.world.isRemote) {
                this.tryToCatchMobs();
            }
        }

        if (this.ticksExisted > ALIVE_TIME) {
            if (world.isRemote) {
                for (int i = 0; i < 8; i++) {
                    this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
                }

                this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_OFF, SoundCategory.PLAYERS, 0.5f, 0.5f, true);
            }

            if (!world.isRemote) {
                this.setDead();
            }
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ModItems.AYATAKA);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        super.onHit(raytraceResultIn);
    }

    private void tryToCatchMobs() {
        this.ais = new ArrayList<>();

        AxisAlignedBB bb = new AxisAlignedBB(this.posX - 16, this.posY - 4, this.posZ - 16, this.posX + 16, this.posY + 4, this.posZ + 16);
        List<EntityCreature> list = this.world.getEntitiesWithinAABB(EntityCreature.class, bb);

        for (EntityCreature creature : list) {
            if (this.canBeAttracted(creature)) {
                EntityAIMoveTowardsAyataka move = this.checkTasks(creature);
                if (move == null) {
                    move = new EntityAIMoveTowardsAyataka(creature, 1.0d);
                    creature.targetTasks.addTask(0, move);
                    this.ais.add(move);
                    AyatakaMod.logger.debug("AI injected to " + creature.getName());
                }

                if (move.getTarget() == null) {
                    move.setTarget(this);
                }

                move.startExecuting();
            }
        }
    }

    private boolean canBeAttracted(EntityCreature creature) {
        if (creature instanceof EntityIronGolem) {
            return true;
        }

        return creature instanceof EntityMob;
    }

    @Nullable
    private EntityAIMoveTowardsAyataka checkTasks(EntityCreature creature) {
        Set<EntityAITasks.EntityAITaskEntry> entries = creature.targetTasks.taskEntries;

        for (EntityAITasks.EntityAITaskEntry entry : entries) {
            if (entry.action instanceof EntityAIMoveTowardsAyataka) {
                return (EntityAIMoveTowardsAyataka) entry.action;
            }
        }

        return null;
    }

    @Override
    public void setDead() {
        if (!this.world.isRemote) {
            for (EntityAIMoveTowardsAyataka x : this.ais) {
                if (x.getTarget() == this) {
                    x.setTarget(null);
                }
            }
        }

        super.setDead();
    }
}

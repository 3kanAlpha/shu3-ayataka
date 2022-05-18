package net.mgcup.ayataka.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityMurderousAyataka extends EntityAyataka {

    public EntityMurderousAyataka(World worldIn) {
        super(worldIn);
    }

    public EntityMurderousAyataka(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    public void onUpdate() {
        if (this.world.isRemote && this.rand.nextInt(100) % 2 == 0) {
            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5d, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        super.onUpdate();
    }

    @Override
    public void setDead() {
        if (!this.world.isRemote) {
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, 3.0f, true);
        }

        super.setDead();
    }
}

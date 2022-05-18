package net.mgcup.ayataka.item;

import net.mgcup.ayataka.entity.EntityAyataka;
import net.mgcup.ayataka.entity.EntityMurderousAyataka;
import net.mgcup.ayataka.init.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class ItemAyataka extends Item {
    private final boolean isMurderous;

    public ItemAyataka(boolean flag) {
        this.setMaxStackSize(16);
        this.isMurderous = flag;
    }

    public boolean isMurderous() {
        return this.isMurderous;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = ForgeEventFactory.onArrowLoose(stack, worldIn, player, i, true);
            if (i < 0) return;

            float v = ItemBow.getArrowVelocity(i);

            if (v >= 0.1f) {
                if (!worldIn.isRemote) {
                    EntityArrow arrow = this.isMurderous() ? new EntityMurderousAyataka(worldIn, player) : new EntityAyataka(worldIn, player);
                    arrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 3.0f * v, 1.0f);

                    worldIn.spawnEntity(arrow);
                }

                worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + v * 0.5F);

                if (!player.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }

                player.addStat(StatList.getObjectUseStats(this));
            }
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        ActionResult<ItemStack> ret = ForgeEventFactory.onArrowNock(stack, worldIn, playerIn, handIn, true);
        if (ret != null) return ret;

        playerIn.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}

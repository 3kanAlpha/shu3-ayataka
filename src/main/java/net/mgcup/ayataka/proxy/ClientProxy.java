package net.mgcup.ayataka.proxy;

import net.mgcup.ayataka.entity.EntityAyataka;
import net.mgcup.ayataka.entity.EntityMurderousAyataka;
import net.mgcup.ayataka.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy {
    @Override
    public void preInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntityAyataka.class, manager -> new RenderSnowball<>(manager, ModItems.AYATAKA, Minecraft.getMinecraft().getRenderItem()));

        RenderingRegistry.registerEntityRenderingHandler(EntityMurderousAyataka.class, manager -> new RenderSnowball<>(manager, ModItems.MURDEROUS_AYATAKA, Minecraft.getMinecraft().getRenderItem()));
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}

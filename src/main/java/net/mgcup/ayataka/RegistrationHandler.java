package net.mgcup.ayataka;

import net.mgcup.ayataka.item.ItemAyataka;
import net.mgcup.ayataka.util.RegistryUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod.EventBusSubscriber(modid = AyatakaMod.MOD_ID)
public class RegistrationHandler {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        final Item[] items = {
                RegistryUtil.setItemName(new ItemAyataka(false), "ayataka").setCreativeTab(CreativeTabs.MATERIALS),
                RegistryUtil.setItemName(new ItemAyataka(true), "murderous_ayataka").setCreativeTab(CreativeTabs.MATERIALS)
        };

        event.getRegistry().registerAll(items);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        final EntityEntry[] entries = {};

        event.getRegistry().registerAll(entries);
    }
}

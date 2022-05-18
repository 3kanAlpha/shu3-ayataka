package net.mgcup.ayataka.util;

import net.mgcup.ayataka.AyatakaMod;
import net.minecraft.item.Item;

public class RegistryUtil {
    public static Item setItemName(final Item item, final String name) {
        item.setRegistryName(AyatakaMod.MOD_ID, name).setUnlocalizedName(AyatakaMod.MOD_ID + "." + name);
        return item;
    }
}

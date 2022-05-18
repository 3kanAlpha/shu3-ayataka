package net.mgcup.ayataka.init;

import net.mgcup.ayataka.AyatakaMod;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.mgcup.ayataka.util.InjectionUtil.Null;

@GameRegistry.ObjectHolder(AyatakaMod.MOD_ID)
public class ModItems {
    public static final Item AYATAKA = Null();
    public static final Item MURDEROUS_AYATAKA = Null();
}

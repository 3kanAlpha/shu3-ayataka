package net.mgcup.ayataka;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = AyatakaMod.MOD_ID, name = AyatakaMod.MOD_NAME, version = AyatakaMod.VERSION)
public class AyatakaMod {
    public static final String MOD_ID = "ayataka";
    public static final String MOD_NAME = "Ayataka Mod";
    public static final String VERSION = "1.0.0";

    public static Logger logger;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }
}

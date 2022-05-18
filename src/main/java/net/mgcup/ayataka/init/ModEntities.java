package net.mgcup.ayataka.init;

import net.mgcup.ayataka.AyatakaMod;
import net.mgcup.ayataka.entity.EntityAyataka;
import net.mgcup.ayataka.entity.EntityMurderousAyataka;
import net.minecraft.entity.EntityTracker;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import static net.mgcup.ayataka.util.InjectionUtil.Null;

public class ModEntities {
    public static EntityEntry ayataka = Null();
    public static EntityEntry murderous_ayataka = Null();

    public static void init() {
        int id = 0;

        ayataka = EntityEntryBuilder.create().entity(EntityAyataka.class)
                    .id(new ResourceLocation(AyatakaMod.MOD_ID, "ayataka"), id++)
                    .name("ayataka")
                    .tracker(64, 20, true)
                    .build();

        murderous_ayataka = EntityEntryBuilder.create().entity(EntityMurderousAyataka.class)
                                .id(new ResourceLocation(AyatakaMod.MOD_ID, "murderous_ayataka"), id++)
                                .name("murderous_ayataka")
                                .tracker(64, 20, true)
                                .build();
    }

}

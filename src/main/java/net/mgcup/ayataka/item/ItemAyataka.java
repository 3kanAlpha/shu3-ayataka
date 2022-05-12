package net.mgcup.ayataka.item;

import net.minecraft.item.Item;

public class ItemAyataka extends Item {
    private final boolean isMurderous;

    public ItemAyataka(boolean flag) {
        this.setMaxStackSize(16);
        this.isMurderous = flag;
    }

    public boolean isMurderous() {
        return this.isMurderous;
    }

}

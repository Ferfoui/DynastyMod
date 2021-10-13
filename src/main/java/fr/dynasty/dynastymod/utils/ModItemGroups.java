package fr.dynasty.dynastymod.utils;

import fr.dynasty.dynastymod.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {

    public static final ItemGroup TAB_DYNASTYMOD = new ItemGroup("dynastymod_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SOUL.get());
        }
    };

}

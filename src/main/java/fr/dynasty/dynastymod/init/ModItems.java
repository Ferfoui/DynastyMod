package fr.dynasty.dynastymod.init;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.utils.ModItemGroups;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DynastyMod.MODID);

    public static final RegistryObject<Item> SOUL = ITEMS.register("soul", () -> new Item(new Item.Properties().stacksTo(16).tab(ModItemGroups.TAB_DYNASTYMOD)));

    public static final RegistryObject<Item> PURIFIED_GOLD_INGOT = ITEMS.register("purified_gold_ingot", () -> new Item(new Item.Properties().tab(ModItemGroups.TAB_DYNASTYMOD).stacksTo(64)));
    public static final RegistryObject<Item> AZURITE = ITEMS.register("azurite", () -> new Item(new Item.Properties().tab(ModItemGroups.TAB_DYNASTYMOD)));
    public static final RegistryObject<Item> SELENITE = ITEMS.register("selenite", () -> new Item(new Item.Properties().tab(ModItemGroups.TAB_DYNASTYMOD)));
    public static final RegistryObject<Item> SOLARITE = ITEMS.register("solarite", () -> new Item(new Item.Properties().tab(ModItemGroups.TAB_DYNASTYMOD)));

    public static final RegistryObject<Item> SELENITE_FRAGMENT = ITEMS.register("selenite_fragment", () -> new Item(new Item.Properties().tab(ModItemGroups.TAB_DYNASTYMOD)));
    public static final RegistryObject<Item> SOLARITE_FRAGMENT = ITEMS.register("solarite_fragment", () -> new Item(new Item.Properties().tab(ModItemGroups.TAB_DYNASTYMOD)));

    public static final RegistryObject<Item> PAPYRUS_FIBER = ITEMS.register("papyrus_fiber", () -> new Item(new Item.Properties().tab(ModItemGroups.TAB_DYNASTYMOD)));

    public static final RegistryObject<Item> RASKA_FANG = ITEMS.register("raska_fang", () -> new Item(new Item.Properties().tab(ModItemGroups.TAB_DYNASTYMOD)));

}

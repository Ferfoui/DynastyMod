package fr.dynasty.dynastymod.data.client.language;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public abstract class AbstractLanguageGenerator extends LanguageProvider {
    public final String modid = DynastyMod.MODID;

    public AbstractLanguageGenerator(DataGenerator gen, String locale) {
        super(gen, DynastyMod.MODID, locale);
    }

    public void addTab(Supplier<? extends ItemGroup> key, String name) {
        add(key.get(), name);
    }

    public void add(ItemGroup key, String name) {
        add("itemGroup." + key.getRecipeFolderName(), name);
    }

    public void addKeyBinding(Supplier<? extends KeyBinding> key, String name) {
        add(key.get(), name);
    }

    public void add(KeyBinding key, String name) {
        add(key.getName(), name);
    }

    public void addKeyCategory(String name) {
        this.add("key.categories." + modid, name);
    }

    public void addContainerBlock(Block key, String name) {
        String namespace = key.getRegistryName().getNamespace();
        String path = key.getRegistryName().getPath();
        add(key, name);
        add("container." + namespace + "." + path, name);
    }

    public void addSign(String name, Block Sign1, Block Sign2) {
        add(Sign1.getDescriptionId(), name + " Sign");
        add(Sign2.getDescriptionId(), name + " Wall Sign");
    }

    public void addDescription(Item key, String description) {
        add(key.getDescriptionId() + ".desc", description);
    }

}

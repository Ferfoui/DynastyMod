package fr.dynasty.dynastymod.data;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.data.client.*;
import fr.dynasty.dynastymod.data.client.language.LanguageGenerator;
import fr.dynasty.dynastymod.data.loottable.LootTableGenerator;
import fr.dynasty.dynastymod.data.recipe.RecipeGenerator;
import fr.dynasty.dynastymod.data.tag.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = DynastyMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent e) {

        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();

        if (e.includeClient()) {

            LanguageGenerator.generateLanguages(generator);

            generator.addProvider(new BlockStateGenerator(generator, existingFileHelper));
            generator.addProvider(new ItemModelGenerator(generator, existingFileHelper));
        }

        if (e.includeServer()) {

            BlockTagsGenerator blockTags = new BlockTagsGenerator(generator, existingFileHelper);

            generator.addProvider(blockTags);
            generator.addProvider(new ItemTagsGenerator(generator, blockTags, existingFileHelper));
            generator.addProvider(new RecipeGenerator(generator));
            generator.addProvider(new LootTableGenerator(generator));
        }
    }
}

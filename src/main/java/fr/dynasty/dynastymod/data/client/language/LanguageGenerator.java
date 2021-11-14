package fr.dynasty.dynastymod.data.client.language;

import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.init.ModItems;
import fr.dynasty.dynastymod.init.ModKeyBindings;
import fr.dynasty.dynastymod.utils.ModItemGroups;
import net.minecraft.data.DataGenerator;

public class LanguageGenerator {

    public static void generateLanguages(DataGenerator generator) {
        generator.addProvider(new EnUs(generator));
        //generator.addProvider(new FrFr(generator)); //accents is not respected
    }

    private static class EnUs extends AbstractLanguageGenerator {

        public EnUs(DataGenerator gen) {
            super(gen, "en_us");
        }

        @Override
        protected void addTranslations() {
            //Items
            add(ModItems.PURIFIED_GOLD_INGOT.get(), "Purified Gold Ingot");
            add(ModItems.AZURITE.get(), "Azurite");
            add(ModItems.SELENITE.get(), "Selenite");
            add(ModItems.SOLARITE.get(), "Solarite");
            add(ModItems.SELENITE_FRAGMENT.get(), "Selenite Fragment");
            add(ModItems.SOLARITE_FRAGMENT.get(), "Solarite Fragment");
            add(ModItems.PALM_SIGN.get(), "Palm Sign");
            add(ModItems.SOUL.get(), "Soul");
            add(ModItems.DATE.get(), "Date");
            add(ModItems.PAPYRUS_FIBER.get(), "Papyrus Fiber");
            add(ModItems.RASKA_FANG.get(), "Raska Fang");

            //Blocks
            add(ModBlocks.AZURITE_ORE.get(), "Azurite Ore");
            add(ModBlocks.SELENITE_ORE.get(), "Selenite Ore");
            add(ModBlocks.SOLARITE_ORE.get(), "Solarite Ore");
            add(ModBlocks.PURIFIED_GOLD_BLOCK.get(), "Purified Gold Block");
            add(ModBlocks.AZURITE_BLOCK.get(), "Azurite Block");
            add(ModBlocks.SELENITE_BLOCK.get(), "Selenite Block");
            add(ModBlocks.SOLARITE_BLOCK.get(), "Solarite Block");
            add(ModBlocks.PALM_LOG.get(), "Palm Log");
            add(ModBlocks.PALM_WOOD.get(), "Palm Wood");
            add(ModBlocks.PALM_PLANKS.get(), "Palm Planks");
            add(ModBlocks.STRIPPED_PALM_LOG.get(), "Stripped Palm Log");
            add(ModBlocks.STRIPPED_PALM_WOOD.get(), "Stripped Palm Wood");
            add(ModBlocks.PALM_LEAVES.get(), "Palm Leaves");
            add(ModBlocks.PALM_SAPLING.get(), "Palm Sapling");
            //addSign("Palm", ModBlocks.PALM_SIGN.get(), ModBlocks.PALM_WALL_SIGN.get());
            add(ModBlocks.SOUL_STONE.get(), "Soul Stone");
            add(ModBlocks.PAPYRUS.get(), "Papyrus");

            addContainerBlock(ModBlocks.SOUL_INFUSER.get(), "Soul Infuser");

            //Others
            add(ModItemGroups.TAB_DYNASTYMOD, "DynastyMod");

            add(ModKeyBindings.KEY_ONE, "Test");
            addKeyCategory("Dynastymod");
        }
    }

    private static class FrFr extends AbstractLanguageGenerator {

        public FrFr(DataGenerator gen) {
            super(gen, "fr_fr");
        }

        @Override
        protected void addTranslations() {
            //Items
            add(ModItems.PURIFIED_GOLD_INGOT.get(), "Lingot d'Or Purifié");
            add(ModItems.AZURITE.get(), "Azurite");
            add(ModItems.SELENITE.get(), "Sélénite");
            add(ModItems.SOLARITE.get(), "Solarite");
            add(ModItems.SELENITE_FRAGMENT.get(), "Fragment de Sélénite");
            add(ModItems.SOLARITE_FRAGMENT.get(), "Fragment de Solarite");
            add(ModItems.PALM_SIGN.get(), "Palm Sign");
            add(ModItems.SOUL.get(), "Âme");
            add(ModItems.DATE.get(), "Datte");
            add(ModItems.PAPYRUS_FIBER.get(), "Fibre de Papyrus");
            add(ModItems.RASKA_FANG.get(), "Croc de Raska");

            //Blocks
            add(ModBlocks.AZURITE_ORE.get(), "Minerai d'Azurite");
            add(ModBlocks.SELENITE_ORE.get(), "Minerai de Sélénite");
            add(ModBlocks.SOLARITE_ORE.get(), "Minerai de Solarite");
            add(ModBlocks.PURIFIED_GOLD_BLOCK.get(), "Bloc d'Or Purifié");
            add(ModBlocks.AZURITE_BLOCK.get(), "Bloc d'Azurite");
            add(ModBlocks.SELENITE_BLOCK.get(), "Bloc de Sélénite");
            add(ModBlocks.SOLARITE_BLOCK.get(), "Bloc de Solarite");
            add(ModBlocks.PALM_LOG.get(), "Bûches de Palmier");
            add(ModBlocks.PALM_WOOD.get(), "Bois de Palmier");
            add(ModBlocks.PALM_PLANKS.get(), "Planche de Palmier");
            add(ModBlocks.STRIPPED_PALM_LOG.get(), "Bûche de Palmier écorcé");
            add(ModBlocks.STRIPPED_PALM_WOOD.get(), "Bois de Palmier écorcé");
            add(ModBlocks.PALM_LEAVES.get(), "Feuilles de Palmier");
            add(ModBlocks.PALM_SAPLING.get(), "Pousse de Palmier");
            addSign("Palm", ModBlocks.PALM_SIGN.get(), ModBlocks.PALM_WALL_SIGN.get());
            add(ModBlocks.PALM_WALL_SIGN.get(), "Palm Wall Sign");
            add(ModBlocks.SOUL_STONE.get(), "Pierre des Âmes");
            add(ModBlocks.PAPYRUS.get(), "Papyrus");

            addContainerBlock(ModBlocks.SOUL_INFUSER.get(), "Infuseur d'Âme");

            //Others
            add(ModItemGroups.TAB_DYNASTYMOD, "DynastyMod");

            add(ModKeyBindings.KEY_ONE, "Test");
            addKeyCategory("Dynastymod");
        }
    }

}

package fr.dynasty.dynastymod.data.recipe;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.init.ModItems;
import fr.dynasty.dynastymod.utils.ModTags;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.PlacedBlockTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {

    public RecipeGenerator(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer){

        //modded crafts
        ShapelessRecipeBuilder.shapeless(ModItems.PURIFIED_GOLD_INGOT.get(), 9)
                .requires(ModBlocks.PURIFIED_GOLD_BLOCK.get())
                .unlockedBy("unlock", has(ModBlocks.PURIFIED_GOLD_BLOCK.get()))
                .save(consumer, DynastyMod.rl("purified_gold_ingot_from_purified_gold_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.PURIFIED_GOLD_BLOCK.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.PURIFIED_GOLD_INGOT.get())
                .unlockedBy("unlock", has(ModItems.PURIFIED_GOLD_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SELENITE.get())
                .pattern("XX")
                .pattern("XX")
                .define('X', ModItems.SELENITE_FRAGMENT.get())
                .unlockedBy("unlock", has(ModItems.SELENITE_FRAGMENT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SOLARITE.get())
                .pattern("XX")
                .pattern("XX")
                .define('X', ModItems.SOLARITE_FRAGMENT.get())
                .unlockedBy("unlock", has(ModItems.SOLARITE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.AZURITE.get(), 9)
                .requires(ModBlocks.AZURITE_BLOCK.get())
                .unlockedBy("unlock", has(ModBlocks.AZURITE_BLOCK.get()))
                .save(consumer, DynastyMod.rl("azurite_from_azurite_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.AZURITE_BLOCK.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.AZURITE.get())
                .unlockedBy("unlock", has(ModItems.AZURITE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.SELENITE.get(), 9)
                .requires(ModBlocks.SELENITE_BLOCK.get())
                .unlockedBy("unlock", has(ModBlocks.SELENITE_BLOCK.get()))
                .save(consumer, DynastyMod.rl("selenite_from_selenite_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.SELENITE_BLOCK.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.SELENITE.get())
                .unlockedBy("unlock", has(ModItems.SELENITE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.SOLARITE.get(), 9)
                .requires(ModBlocks.SOLARITE_BLOCK.get())
                .unlockedBy("unlock", has(ModBlocks.SOLARITE_BLOCK.get()))
                .save(consumer, DynastyMod.rl("solarite_from_solarite_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.SOLARITE_BLOCK.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.SOLARITE.get())
                .unlockedBy("unlock", has(ModItems.SOLARITE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.SOUL_STONE.get())
                .pattern("XX")
                .pattern("XX")
                .define('X', Blocks.SOUL_SAND)
                .unlockedBy("unlock", has(Blocks.SOUL_SAND))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.SOUL_INFUSER.get())
                .pattern("X#X")
                .pattern("X X")
                .pattern("XBX")
                .define('X', ModBlocks.SOUL_STONE.get()).define('#', Blocks.SOUL_SOIL)
                .define('B', Items.FLINT_AND_STEEL)
                .unlockedBy("unlock", has(ModItems.SOUL.get()))
                .save(consumer);

        planksFromLogs(consumer, ModBlocks.PALM_PLANKS.get(), ModTags.Items.PALM_LOGS);

        ShapedRecipeBuilder.shaped(ModItems.PALM_SIGN.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern(" # ")
                .define('X', ModBlocks.PALM_PLANKS.get()).define('#', Items.STICK)
                .unlockedBy("unlock", has(ModBlocks.PALM_PLANKS.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.PAPYRUS_FIBER.get(), 2)
                .requires(ModBlocks.PAPYRUS.get())
                .unlockedBy("unlock", has(ModBlocks.PAPYRUS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Items.PAPER)
                .pattern("XXX")
                .define('X', ModItems.PAPYRUS_FIBER.get())
                .unlockedBy("unlock", has(ModItems.PAPYRUS_FIBER.get()))
                .save(consumer, DynastyMod.rl("paper_from_papyrus_fiber"));

        //modded cooks
        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.AZURITE_ORE.get().asItem()), ModItems.AZURITE.get(), 0.1f, 200)
                .unlockedBy("unlock", has(ModBlocks.AZURITE_ORE.get()))
                .save(consumer, DynastyMod.rl("azurite_from_smelting"));

        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.SELENITE_ORE.get().asItem()), ModItems.SELENITE.get(), 0.2f, 200)
                .unlockedBy("unlock", has(ModBlocks.SELENITE_ORE.get()))
                .save(consumer, DynastyMod.rl("selenite_from_smelting"));

        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.SOLARITE_ORE.get().asItem()), ModItems.SOLARITE.get(), 0.2f, 200)
                .unlockedBy("unlock", has(ModBlocks.SOLARITE_ORE.get()))
                .save(consumer, DynastyMod.rl("solarite_from_smelting"));

        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.AZURITE_ORE.get().asItem()), ModItems.AZURITE.get(), 0.1f, 100)
                .unlockedBy("unlock", has(ModBlocks.AZURITE_ORE.get()))
                .save(consumer, DynastyMod.rl("azurite_from_blasting"));

        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.SELENITE_ORE.get().asItem()), ModItems.SELENITE.get(), 0.2f, 100)
                .unlockedBy("unlock", has(ModBlocks.SELENITE_ORE.get()))
                .save(consumer, DynastyMod.rl("selenite_from_blasting"));

        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.SOLARITE_ORE.get().asItem()), ModItems.SOLARITE.get(), 0.2f, 100)
                .unlockedBy("unlock", has(ModBlocks.SOLARITE_ORE.get()))
                .save(consumer, DynastyMod.rl("solarite_from_blasting"));

        //infusing
        InfusingRecipeBuilder.infusing(Ingredient.of(Items.GOLD_INGOT), Ingredient.of(ModItems.SOUL.get()), ModItems.PURIFIED_GOLD_INGOT.get())
                .unlocks("unlock", PlacedBlockTrigger.Instance.placedBlock(ModBlocks.SOUL_INFUSER.get()))
                .save(consumer, DynastyMod.rl("purified_gold_ingot"));

        InfusingRecipeBuilder.infusing(Ingredient.of(ItemTags.SAND), Ingredient.of(ModItems.SOUL.get()), Blocks.SOUL_SAND, 100)
                .unlocks("unlock", has(ModItems.SOUL.get()))
                .save(consumer, DynastyMod.rl("soul_sand_from_infuser"));

        InfusingRecipeBuilder.infusing(Ingredient.of(Blocks.SANDSTONE), Ingredient.of(ModItems.SOUL.get()), ModBlocks.SOUL_STONE.get(), 140)
                .unlocks("unlock", has(ModItems.SOUL.get()))
                .save(consumer, DynastyMod.rl("soul_stone_from_infuser"));


        //vanilla crafts
        ShapedRecipeBuilder.shaped(Blocks.GRASS_BLOCK)
                .pattern("X")
                .pattern("#")
                .define('X', Blocks.GRASS).define('#', Blocks.DIRT)
                .unlockedBy("unlock", InventoryChangeTrigger.Instance.hasItems(Blocks.GRASS, Blocks.DIRT))
                .save(consumer);

        CookingRecipeBuilder.blasting(Ingredient.of(Blocks.COBBLESTONE), Blocks.STONE.asItem(), 0.1f, 100)
                .unlockedBy("unlock", has(Blocks.COBBLESTONE))
                .save(consumer, "stone_from_blasting");

    }

}

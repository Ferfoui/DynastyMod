package fr.dynasty.dynastymod.init;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.blocks.ModOreBlock;
import fr.dynasty.dynastymod.blocks.soulinfuser.SoulInfuserBlock;
import fr.dynasty.dynastymod.blocks.trees.PalmTree;
import fr.dynasty.dynastymod.utils.ModItemGroups;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DynastyMod.MODID);

    public static final RegistryObject<Block> AZURITE_ORE = createBlock("azurite_ore", () -> new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.5f, 2.5f).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SELENITE_ORE = createBlock("selenite_ore", () -> new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(4f, 4f).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SOLARITE_ORE = createBlock("solarite_ore", () -> new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(4f, 4f).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PURIFIED_GOLD_BLOCK = createBlock("purified_gold_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.GOLD).sound(SoundType.METAL).strength(3f, 6f).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AZURITE_BLOCK = createBlock("azurite_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3f, 6f).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SELENITE_BLOCK = createBlock("selenite_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).sound(SoundType.METAL).strength(4f, 8f).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SOLARITE_BLOCK = createBlock("solarite_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).sound(SoundType.METAL).strength(4f, 8f).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SOUL_STONE = createBlock("soul_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).sound(SoundType.SOUL_SOIL).strength(1.5f, 5f).speedFactor(0.1F).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SOUL_INFUSER = createBlock("soul_infuser", () -> new SoulInfuserBlock(litBlockEmission(13)));

    public static final RegistryObject<Block> PALM_LOG = createBlock("palm_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> PALM_WOOD = createBlock("palm_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_PALM_LOG = createBlock("stripped_palm_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_PALM_WOOD = createBlock("stripped_palm_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> PALM_PLANKS = createBlock("palm_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PALM_LEAVES = createBlock("palm_leaves", () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2f).sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> PALM_SAPLING = createBlock("palm_sapling", () -> new SaplingBlock(new PalmTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> PAPYRUS = createBlock("papyrus", () -> new FlowerBlock(Effects.DAMAGE_RESISTANCE, 5, AbstractBlock.Properties.copy(Blocks.DANDELION)));

    private static RegistryObject<Block> createBlock(String name, Supplier<? extends Block> supplier){
        RegistryObject<Block> block = BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroups.TAB_DYNASTYMOD)));
        return block;
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightLevel) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightLevel : 0;
    }

}

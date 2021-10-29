package fr.dynasty.dynastymod.data.loottable;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LootTableGenerator extends LootTableProvider {

    public LootTableGenerator(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((resourceLocation, lootTable) -> LootTableManager.validate(validationtracker, resourceLocation, lootTable));
    }

    public static class ModBlockLootTables extends BlockLootTables {
        @Override
        protected void addTables() {
            dropSelf(ModBlocks.PURIFIED_GOLD_BLOCK.get());

            dropSelf(ModBlocks.AZURITE_BLOCK.get());
            dropSelf(ModBlocks.SELENITE_BLOCK.get());
            dropSelf(ModBlocks.SOLARITE_BLOCK.get());

            add(ModBlocks.AZURITE_ORE.get(), (block) -> createOreDrop(block, ModItems.AZURITE.get()));
            add(ModBlocks.SELENITE_ORE.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(ModItems.SELENITE.get())
                    .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                    .apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
            add(ModBlocks.SOLARITE_ORE.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(ModItems.SOLARITE.get())
                    .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                    .apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));

            dropSelf(ModBlocks.SOUL_STONE.get());

            add(ModBlocks.SOUL_INFUSER.get(), BlockLootTables::createNameableBlockEntityTable);

            dropSelf(ModBlocks.PAPYRUS.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}

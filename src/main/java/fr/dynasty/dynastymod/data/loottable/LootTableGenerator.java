package fr.dynasty.dynastymod.data.loottable;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import fr.dynasty.dynastymod.entity.ModEntityTypes;
import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.KilledByPlayer;
import net.minecraft.loot.conditions.RandomChanceWithLooting;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.LootingEnchantBonus;
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
                Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK),
                Pair.of(ModEntityLootTables::new, LootParameterSets.ENTITY)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((resourceLocation, lootTable) -> LootTableManager.validate(validationtracker, resourceLocation, lootTable));
    }

    public static class ModBlockLootTables extends BlockLootTables {

        protected static LootTable.Builder createPalmLeavesDrops(Block block, Block drop, float... chances) {
            return createLeavesDrops(block, drop, chances)
                    .withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                            .when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                            .add(applyExplosionCondition(block, ItemLootEntry.lootTableItem(ModItems.DATE.get()))
                                    .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
        }

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

            dropSelf(ModBlocks.PALM_LOG.get());
            dropSelf(ModBlocks.PALM_WOOD.get());
            dropSelf(ModBlocks.STRIPPED_PALM_LOG.get());
            dropSelf(ModBlocks.STRIPPED_PALM_WOOD.get());
            add(ModBlocks.PALM_LEAVES.get(), (block) -> createPalmLeavesDrops(block, ModBlocks.PALM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
            dropSelf(ModBlocks.PALM_SAPLING.get());
            dropSelf(ModBlocks.PALM_PLANKS.get());
            dropSelf(ModBlocks.PALM_STAIRS.get());
            dropSelf(ModBlocks.PALM_SLAB.get());
            dropSelf(ModBlocks.PALM_FENCE.get());
            dropSelf(ModBlocks.PALM_FENCE_GATE.get());
            dropSelf(ModBlocks.PALM_SIGN.get());
            dropOther(ModBlocks.PALM_WALL_SIGN.get(), ModBlocks.PALM_SIGN.get());

            dropSelf(ModBlocks.PAPYRUS.get());

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }

    public static class ModEntityLootTables extends EntityLootTables {

        @Override
        protected void addTables() {
            add(ModEntityTypes.MEGA_MUMMY.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(Items.ROTTEN_FLESH)
                                    .apply(SetCount.setCount(RandomValueRange.between(0.0F, 3.0F)))
                                    .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))))
                    .withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(ModItems.SOUL.get()))
                            .add(ItemLootEntry.lootTableItem(Items.PAPER))
                            .when(KilledByPlayer.killedByPlayer())
                            .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.025F, 0.01F))));
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return ModEntityTypes.ENTITY_TYPES.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}

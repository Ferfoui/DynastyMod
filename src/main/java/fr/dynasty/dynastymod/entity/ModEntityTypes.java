package fr.dynasty.dynastymod.entity;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.entity.custom.MegaMummyEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, DynastyMod.MODID);

    public static final RegistryObject<EntityType<MegaMummyEntity>> MEGA_MUMMY = ENTITY_TYPES.register("mega_mummy", () -> EntityType.Builder.of(MegaMummyEntity::new, EntityClassification.MONSTER)
            .sized(1f, 5f).build(DynastyMod.rl("mega_mummy").toString()));
}

package fr.dynasty.dynastymod.init;

import fr.dynasty.dynastymod.DynastyMod;
//import fr.dynasty.dynastymod.soulinfuser.TileEntitySoulInfuser;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, DynastyMod.MODID);
    /*
    public static final RegistryObject<TileEntityType<?>> SOUL_INFUSER_TILE_ENTITY = TILE_ENTITIES.register("soul_infuser_tile_entity", () -> TileEntityType.Builder.of(TileEntitySoulInfuser::new, ModBlocks.SOUL_INFUSER.get()).build(null));
    */
}

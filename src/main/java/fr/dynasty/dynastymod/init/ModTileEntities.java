package fr.dynasty.dynastymod.init;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.blocks.signs.ModSignTileEntity;
import fr.dynasty.dynastymod.blocks.soulinfuser.SoulInfuserTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, DynastyMod.MODID);

    public static final RegistryObject<TileEntityType<SoulInfuserTileEntity>> SOUL_INFUSER_TILE_ENTITY = TILE_ENTITIES.register("soul_infuser_tile_entity",
            () -> TileEntityType.Builder.of(SoulInfuserTileEntity::new, ModBlocks.SOUL_INFUSER.get()).build(null));

    public static final RegistryObject<TileEntityType<ModSignTileEntity>> SIGN_TILE_ENTITIES = TILE_ENTITIES.register("sign",
            () -> TileEntityType.Builder.of(ModSignTileEntity::new, ModBlocks.PALM_SIGN.get(), ModBlocks.PALM_WALL_SIGN.get()).build(null));

}

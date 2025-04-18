package fr.dynasty.dynastymod.blocks.signs;

import fr.dynasty.dynastymod.init.ModTileEntities;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ModSignTileEntity extends SignTileEntity {

    public ModSignTileEntity() {
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return ModTileEntities.SIGN_TILE_ENTITIES.get();
    }
}

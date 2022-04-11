package fr.dynasty.dynastymod.entity.render;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.entity.custom.MegaMummyEntity;
import fr.dynasty.dynastymod.entity.model.MegaMummyModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MegaMummyRenderer extends MobRenderer<MegaMummyEntity, MegaMummyModel<MegaMummyEntity>> {

    protected static final ResourceLocation TEXTURE = DynastyMod.rl("textures/entity/mega_mummy.png");

    public MegaMummyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MegaMummyModel<>(), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(MegaMummyEntity entity) {
        return TEXTURE;
    }
}

package fr.dynasty.dynastymod.blocks.soulinfuser;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SoulInfuserScreen extends ContainerScreen<SoulInfuserContainer> {
    public static final ResourceLocation TEXTURE = DynastyMod.rl("textures/gui/soul_infuser.png");
    public SoulInfuserScreen(SoulInfuserContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, x, y, partialTicks);
        this.renderTooltip(matrixStack, x, y);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (minecraft == null) {
            return;
        }

        //noinspection deprecation
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        minecraft.getTextureManager().bind(TEXTURE);

        int posX = (this.width - this.imageWidth) / 2;
        int posY = (this.height - this.imageHeight) / 2;

        blit(matrixStack, posX, posY, 0,0, this.imageWidth, this.imageHeight);

        if (menu.isLit()) {
            //burning
            int k = menu.getSoulBurning();
            blit(matrixStack, posX + 56, posY + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        //progress arrow
        int l = menu.getProgressArrowScale();
        blit(matrixStack, posX + 79, posY + 34, 176, 14, l + 1, 16);
    }
}

package fr.dynasty.dynastymod.integration.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;
import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.utils.crafting.recipe.InfusingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class SoulInfuserRecipeCategory implements IRecipeCategory<InfusingRecipe> {
    public static final ResourceLocation UID = DynastyMod.rl("infusing");
    public static final ResourceLocation TEXTURE = DynastyMod.rl("textures/gui/soul_infuser.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic staticFlame;
    private final LoadingCache<Integer, IDrawableAnimated> progressArrow;

    private final int infusingTime = 200;

    public SoulInfuserRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 14, 4, 148, 74);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.SOUL_INFUSER.get()));
        this.staticFlame = helper.createDrawable(TEXTURE, 176, 0, 14, 14);
        this.progressArrow = CacheBuilder.newBuilder()
                .maximumSize(25)
                .build(new CacheLoader<Integer, IDrawableAnimated>() {
                    @Override
                    public IDrawableAnimated load(Integer workTime) {
                        return helper.drawableBuilder(TEXTURE, 176, 14, 24, 17)
                                .buildAnimated(workTime, IDrawableAnimated.StartDirection.LEFT, false);
                    }
                });
    }

    protected IDrawableAnimated getArrow(InfusingRecipe recipe) {
        return this.progressArrow.getUnchecked(infusingTime);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends InfusingRecipe> getRecipeClass() {
        return InfusingRecipe.class;
    }

    @Override
    public String getTitle() {
        return ModBlocks.SOUL_INFUSER.get().getName().getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(InfusingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void draw(InfusingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        staticFlame.draw(matrixStack, 42, 32);

        IDrawableAnimated arrow = getArrow(recipe);
        arrow.draw(matrixStack, 65, 30);

        drawInfusingTime(recipe, matrixStack, 50);
    }

    private void drawInfusingTime(InfusingRecipe recipe, MatrixStack matrixStack, int y) {
        //int infusingTime = recipe.getInfusingTime();
        if (infusingTime > 0) {
            int infusingTimeSeconds = infusingTime / 20;
            TranslationTextComponent timeString = new TranslationTextComponent("gui.jei.category.smelting.time.seconds", infusingTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            fontRenderer.draw(matrixStack, timeString, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, InfusingRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 41, 12);
        recipeLayout.getItemStacks().init(1, true, 41, 48);

        recipeLayout.getItemStacks().init(2, false, 101, 30);

        recipeLayout.getItemStacks().set(ingredients);
    }
}

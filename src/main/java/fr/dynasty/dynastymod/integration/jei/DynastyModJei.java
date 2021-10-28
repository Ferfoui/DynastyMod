package fr.dynasty.dynastymod.integration.jei;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.init.ModRecipes;
import fr.dynasty.dynastymod.utils.crafting.recipe.InfusingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class DynastyModJei implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return DynastyMod.rl("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new SoulInfuserRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registration.addRecipes(rm.getAllRecipesFor(ModRecipes.Types.INFUSING).stream().filter(r -> r instanceof InfusingRecipe).collect(Collectors.toList()),
                SoulInfuserRecipeCategory.UID);
    }

}

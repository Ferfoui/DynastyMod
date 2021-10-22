package fr.dynasty.dynastymod.utils.crafting.recipe;

import com.google.gson.JsonObject;
import fr.dynasty.dynastymod.init.ModRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class InfusingRecipe extends SingleItemRecipe {

    public InfusingRecipe(ResourceLocation recipeId, Ingredient ingredient, ItemStack result) {
        super(ModRecipes.Types.INFUSING, ModRecipes.Serializers.INFUSING.get(), recipeId, "", ingredient, result);
    }

    @Override
    public boolean matches(IInventory inventory, World world) {
        return this.ingredient.test(inventory.getItem(0));
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<InfusingRecipe> {

        @Override
        public InfusingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
            ResourceLocation itemId = new ResourceLocation(JSONUtils.getAsString(json, "result"));
            int count = JSONUtils.getAsInt(json, "count", 1);

            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), count);

            return new InfusingRecipe(recipeId, ingredient, result);
        }

        @Nullable
        @Override
        public InfusingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();

            return new InfusingRecipe(recipeId, ingredient, result);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, InfusingRecipe recipe) {
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }
    }

}

package fr.dynasty.dynastymod.utils.crafting.recipe;

import com.google.gson.JsonObject;
import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.init.ModRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class InfusingRecipe implements IRecipe<IInventory> {
    private final Ingredient ingredient;
    private final Ingredient infuser;
    private final ItemStack result;
    private final ResourceLocation recipeId;
    private final int infusingTime;

    public static ResourceLocation TYPE_ID = DynastyMod.rl("infusing");

    public InfusingRecipe(ResourceLocation recipeId, Ingredient ingredient, Ingredient infuser, ItemStack result, int infusingTime) {
        this.recipeId = recipeId;
        this.ingredient = ingredient;
        this.infuser = infuser;
        this.result = result;
        this.infusingTime = infusingTime;
    }

    @Override
    public boolean matches(IInventory inventory, World world) {
        return this.ingredient.test(inventory.getItem(0)) && this.infuser.test(inventory.getItem(1));
    }

    @Override
    public ItemStack assemble(IInventory inventory) {
        ItemStack itemstack = this.result.copy();
        CompoundNBT compoundnbt = inventory.getItem(0).getTag();
        if (compoundnbt != null) {
            itemstack.setTag(compoundnbt.copy());
        }

        return itemstack;
    }

    @Override
    public boolean canCraftInDimensions(int gridWidth, int gridHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.withSize(2, Ingredient.EMPTY);
        ingredients.set(0, ingredient);
        ingredients.set(1, infuser);

        return ingredients;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.SOUL_INFUSER.get());
    }

    public int getInfusingTime() {
        return infusingTime;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.Serializers.INFUSING.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    public static class RecipeType implements IRecipeType<InfusingRecipe> {

        @Override
        public String toString() {
            return InfusingRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<InfusingRecipe> {

        @Override
        public InfusingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "ingredient"));
            Ingredient infuser = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "infuser"));
            ResourceLocation itemId = new ResourceLocation(JSONUtils.getAsString(json, "result"));

            int count = JSONUtils.getAsInt(json, "count", 1);
            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), count);

            int infusingTime = JSONUtils.getAsInt(json, "time", 200);


            return new InfusingRecipe(recipeId, ingredient, infuser, result, infusingTime);
        }

        @Nullable
        @Override
        public InfusingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            Ingredient infuser = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            int infusingTime = buffer.readVarInt();

            return new InfusingRecipe(recipeId, ingredient, infuser, result, infusingTime);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, InfusingRecipe recipe) {
            recipe.ingredient.toNetwork(buffer);
            recipe.infuser.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeVarInt(recipe.infusingTime);
        }
    }

}

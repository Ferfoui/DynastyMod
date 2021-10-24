package fr.dynasty.dynastymod.utils.crafting.recipe;

import com.google.gson.JsonObject;
import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.init.ModRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class InfusingRecipe implements IRecipe<IInventory> {
    private final Ingredient ingredient;
    private final Ingredient infuser;
    private final ItemStack result;
    private final ResourceLocation recipeId;

    public InfusingRecipe(ResourceLocation recipeId, Ingredient ingredient, Ingredient infuser, ItemStack result) {
        this.recipeId = recipeId;
        this.ingredient = ingredient;
        this.infuser = infuser;
        this.result = result;
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
        return gridWidth * gridHeight >= 2;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.SOUL_INFUSER.get());
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
        return ModRecipes.Types.INFUSING;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<InfusingRecipe> {

        @Override
        public InfusingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "ingredient"));
            Ingredient infuser = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "infuser"));
            ResourceLocation itemId = new ResourceLocation(JSONUtils.getAsString(json, "result"));
            int count = JSONUtils.getAsInt(json, "count", 1);

            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), count);

            return new InfusingRecipe(recipeId, ingredient, infuser, result);
        }

        @Nullable
        @Override
        public InfusingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            Ingredient infuser = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();

            return new InfusingRecipe(recipeId, ingredient, infuser, result);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, InfusingRecipe recipe) {
            recipe.ingredient.toNetwork(buffer);
            recipe.infuser.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }
    }

}

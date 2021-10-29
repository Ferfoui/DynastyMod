package fr.dynasty.dynastymod.data.recipe;

import com.google.gson.JsonObject;
import fr.dynasty.dynastymod.init.ModRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class InfusingRecipeBuilder {
    private final Ingredient ingredient;
    private final Ingredient infuser;
    private final Item result;
    private final int count;
    private final int infusingTime;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private final IRecipeSerializer<?> type;

    public InfusingRecipeBuilder(IRecipeSerializer<?> serializer, Ingredient ingredient, Ingredient infuser, IItemProvider itemProvider, int count, int infusingTime) {
        this.type = serializer;
        this.ingredient = ingredient;
        this.infuser = infuser;
        this.result = itemProvider.asItem();
        this.count = count;
        this.infusingTime = infusingTime;
    }

    public static InfusingRecipeBuilder infusing(Ingredient ingredient, Ingredient infuser, IItemProvider itemProvider) {
        return infusing(ingredient, infuser, itemProvider, 200);
    }

    public static InfusingRecipeBuilder infusing(Ingredient ingredient, Ingredient infuser, IItemProvider itemProvider, int infusingTime) {
        return infusing(ingredient, infuser, itemProvider, infusingTime, 1);
    }

    public static InfusingRecipeBuilder infusing(Ingredient ingredient, Ingredient infuser, IItemProvider itemProvider, int infusingTime, int count) {
        return new InfusingRecipeBuilder(ModRecipes.Serializers.INFUSING.get(), ingredient, infuser, itemProvider, count, infusingTime);
    }

    public InfusingRecipeBuilder unlocks(String name, ICriterionInstance unlockEvent) {
        this.advancement.addCriterion(name, unlockEvent);
        return this;
    }

    public void save(Consumer<IFinishedRecipe> consumer, String name) {
        this.save(consumer, new ResourceLocation(name));
    }

    public void save(Consumer<IFinishedRecipe> consumer, ResourceLocation resourceLocation) {
        this.ensureValid(resourceLocation);
        this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation)).rewards(AdvancementRewards.Builder.recipe(resourceLocation)).requirements(IRequirementsStrategy.OR);
        assert this.result.getItemCategory() != null;
        consumer.accept(new InfusingRecipeBuilder.Result(resourceLocation, this.type, this.ingredient, this.infuser, this.result, this.count, this.infusingTime, this.advancement, new ResourceLocation(resourceLocation.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + resourceLocation.getPath())));
    }

    private void ensureValid(ResourceLocation resourceLocation) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + resourceLocation);
        }
    }

    public static class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final Ingredient infuser;
        private final Item result;
        private final int count;
        private final int infusingTime;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final IRecipeSerializer<?> type;

        public Result(ResourceLocation recipeId, IRecipeSerializer<?> type, Ingredient ingredient, Ingredient infuser, Item result, int count, int infusingTime, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = recipeId;
            this.type = type;
            this.ingredient = ingredient;
            this.infuser = infuser;
            this.result = result;
            this.count = count;
            this.infusingTime = infusingTime;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        public void serializeRecipeData(JsonObject json) {
            json.add("ingredient", this.ingredient.toJson());
            json.add("infuser", this.infuser.toJson());
            json.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
            json.addProperty("count", this.count);
            json.addProperty("time", this.infusingTime);
        }

        public ResourceLocation getId() {
            return this.id;
        }

        public IRecipeSerializer<?> getType() {
            return this.type;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}

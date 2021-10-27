package fr.dynasty.dynastymod.init;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.utils.crafting.recipe.InfusingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {

    public static class Types {
        public static final IRecipeType<InfusingRecipe> INFUSING = new InfusingRecipe.RecipeType();
    }

    public static class Serializers {
        public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, DynastyMod.MODID);

        public static final RegistryObject<IRecipeSerializer<InfusingRecipe>> INFUSING = RECIPE_SERIALIZERS.register("infusing", InfusingRecipe.Serializer::new);
    }

    public static void register(IEventBus bus) {
        Serializers.RECIPE_SERIALIZERS.register(bus);

        Registry.register(Registry.RECIPE_TYPE, InfusingRecipe.TYPE_ID, Types.INFUSING);
    }

}

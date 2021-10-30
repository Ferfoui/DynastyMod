package fr.dynasty.dynastymod.init;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.blocks.soulinfuser.SoulInfuserContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, DynastyMod.MODID);


    public static final RegistryObject<ContainerType<SoulInfuserContainer>> SOUL_INFUSER_CONTAINER = CONTAINERS.register("soul_infuser_container", () -> IForgeContainerType.create(SoulInfuserContainer::new));

}

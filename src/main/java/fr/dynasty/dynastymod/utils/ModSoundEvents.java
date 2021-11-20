package fr.dynasty.dynastymod.utils;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DynastyMod.MODID);

    public static final RegistryObject<SoundEvent> NEVER_GONNA_GIVE_YOU_UP = register("never_gonna_give_you_up");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(DynastyMod.rl(name)));
    }
}

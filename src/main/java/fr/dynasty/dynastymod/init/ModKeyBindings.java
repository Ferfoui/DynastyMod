package fr.dynasty.dynastymod.init;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = DynastyMod.MODID)
public class ModKeyBindings {

    public static final KeyBinding KEY_ONE = new KeyBinding("key."+ DynastyMod.MODID +".key_one", GLFW.GLFW_KEY_V, "key.categories."+ DynastyMod.MODID);

    public static void register() {
        ClientRegistry.registerKeyBinding(KEY_ONE);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent e) {
        if(KEY_ONE.isDown()){
            System.out.println("TEST");
        }
    }
}

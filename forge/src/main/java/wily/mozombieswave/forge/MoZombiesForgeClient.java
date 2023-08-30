package wily.mozombieswave.forge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.client.ClientEvents;

import java.util.function.Supplier;

@Mod.EventBusSubscriber( modid = MoZombiesWave.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MoZombiesForgeClient {

    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event){
        ClientEvents.entityRenderEvent(new MoZombiesPlatform.EntityRendererRegistry() {
            @Override
            public <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, MoZombiesPlatform.EntityRendererProvider<T> provider) {
                RenderingRegistry.registerEntityRenderingHandler(type.get(), provider::create);
            }
        });
    }

}

package wily.mozombieswave.forge;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.init.ClientEvents;

import java.util.function.Supplier;

@Mod.EventBusSubscriber( modid = MoZombiesWave.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MoZombiesForgeClient {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event){
        ClientEvents.entityRenderEvent(new MoZombiesPlatform.FactocraftyEntityRendererRegistry() {
            @Override
            public <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
                event.registerEntityRenderer(type.get(),provider);
            }
        });
    }
    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ClientEvents.registerLayerDefinition(event::registerLayerDefinition);
    }


}

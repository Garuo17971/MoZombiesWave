package wily.mozombieswave.forge;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.init.ClientEvents;
import wily.mozombieswave.init.Registration;

import java.util.function.Supplier;

import static wily.mozombieswave.init.Registration.getModId;

@Mod.EventBusSubscriber( modid = MoZombiesWave.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MoZombiesForgeClient {
    @SubscribeEvent
    public static void registerCreativeTab(final CreativeModeTabEvent.Register event){
        event.registerCreativeModeTab(getModId("tab"), Registration::getPlatformCreativeModeTab);
    }
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

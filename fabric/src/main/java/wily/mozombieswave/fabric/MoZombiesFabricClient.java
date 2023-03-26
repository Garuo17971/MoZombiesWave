package wily.mozombieswave.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.init.ClientEvents;
import wily.mozombieswave.init.Registration;

import java.util.function.Supplier;

public class MoZombiesFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientEvents.entityRenderEvent(new MoZombiesPlatform.FactocraftyEntityRendererRegistry() {
            @Override
            public <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
                EntityRendererRegistry.register(type.get(),provider);
            }
        });
        ClientEvents.registerLayerDefinition((l,d)-> EntityModelLayerRegistry.registerModelLayer(l,d::get));
    }
}

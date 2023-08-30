package wily.mozombieswave.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.client.ClientEvents;

import java.util.function.Supplier;

public class MoZombiesFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientEvents.entityRenderEvent(new MoZombiesPlatform.EntityRendererRegistry() {
            @Override
            public <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, MoZombiesPlatform.EntityRendererProvider<T> provider) {
                net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.INSTANCE.register(type.get(),(m, c)->provider.create(m));
            }
        });

    }
}

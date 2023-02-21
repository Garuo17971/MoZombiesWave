package wily.mozombieswave.fabric;

import jdk.management.jfr.RecordingInfo;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.networking.v1.EntityTrackingEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.init.Registration;


public class MoZombiesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MoZombiesWave.init();
        MoZombiesWave.common();
        ServerEntityEvents.ENTITY_LOAD.register((Registration::onEntityJoinWorld));
        Registration.registerEntityAttributes( (e,a)->FabricDefaultAttributeRegistry.register(e,a.get()));
        Registration.addEntitiesSpawn((c,s,e,w,min,max)-> BiomeModifications.addSpawn((bc)-> c.test(new MoZombiesPlatform.BiomeContext(bc.getBiome(),bc::hasTag)),s,e,w,min,max));
    }
}

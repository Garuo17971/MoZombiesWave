package wily.mozombieswave.fabric;

import jdk.management.jfr.RecordingInfo;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.init.Registration;

import java.util.function.Predicate;

import static wily.mozombieswave.init.Registration.getModId;


public class MoZombiesFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        MoZombiesWave.init();
        Registration.registerEntitiesSpawnPlacement(SpawnPlacements::register);
        ServerEntityEvents.ENTITY_LOAD.register((Registration::onEntityJoinWorld));
        Registration.registerEntityAttributes( (e,a)->FabricDefaultAttributeRegistry.register(e,a.get()));
        BiomeModifications.addSpawn( (c)-> c.hasTag(BiomeTags.IS_TAIGA) || c.hasTag(BiomeTags.HAS_DESERT_PYRAMID), MobCategory.MONSTER, EntityType.GIANT, 3, 1, 2);
        BiomeModifications.addSpawn( (c)-> c.hasTag(BiomeTags.IS_NETHER) ,MobCategory.MONSTER, Registration.NETHER_ZOMBIE.get(), 3, 1, 4);
        Predicate<BiomeSelectionContext> predicate =(c)-> !c.hasTag(BiomeTags.IS_NETHER) && !c.hasTag(BiomeTags.IS_END);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER, Registration.DISCO_ZOMBIE.get(), 6, 1, 4);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER, Registration.ZOMBIE_CREEPER.get(), 3, 1, 3);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER, Registration.ZOMBIE_DWARF.get(), 10, 1, 5);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER, Registration.ZOMBIE_CHEF.get(), 8, 1, 4);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER, Registration.ZOMBIE_CYBORG.get(), 5, 1, 4);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER, Registration.ZOMBIE_HEROBRINE.get(), 1, 1, 1);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER, Registration.ZOMBIE_NOTCH.get(), 2, 1, 1);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER, Registration.ZOMBIE_KING.get(), 4, 1, 2);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER, Registration.ZOMBIE_KNIGHT.get(), 3, 1, 4);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER,Registration.ZOMBIE_MINER.get(), 4, 2, 7);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER, Registration.ZOMBIE_PA.get(), 5, 1, 2);
        BiomeModifications.addSpawn(predicate,MobCategory.MONSTER, Registration.ZOMBIE_PIRATE.get(), 7, 1, 3);
        BiomeModifications.addSpawn( (c)-> !c.hasTag(BiomeTags.IS_OCEAN),MobCategory.CREATURE, Registration.SURVIVOR.get(), 3, 1, 4);
    }
}

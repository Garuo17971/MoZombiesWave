/*
 *    MCreator note:
 *
 *    If you lock base mod element files, you can edit this file and the proxy files
 *    and they won't get overwritten. If you change your mod package or modid, you
 *    need to apply these changes to this file MANUALLY.
 *
 *    Settings in @Mod annotation WON'T be changed in case of the base mod element
 *    files lock too, so you need to set them manually here in such case.
 *
 *    Keep the ElementsMozombiesWaveMod object in this class and all calls to this object
 *    INTACT in order to preserve functionality of mod elements generated by MCreator.
 *
 *    If you do not lock base mod element files in Workspace settings, this file
 *    will be REGENERATED on each build.
 *
 */
package wily.mozombieswave;

import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wily.mozombieswave.entity.AbstractMoZombie;
import wily.mozombieswave.entity.Survivor;
import wily.mozombieswave.entity.ZombieCreeper;
import wily.mozombieswave.init.Registration;


@Mod(MozombiesWaveMod.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MozombiesWaveMod {
	public static final String MODID = "mozombies_wave";
	public static final String VERSION = "0.1.0";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static IEventBus MOD_EVENT_BUS;


	public MozombiesWaveMod(){

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

		MOD_EVENT_BUS.register(Registration.class);
		Registration.init();
		MOD_EVENT_BUS.register(this);

		Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-client.toml"));

	}


    @SubscribeEvent
	public static void common(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			LOGGER.info("Success started spawn placements register");
					SpawnPlacements.register(Registration.DISCO_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.NETHER_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.ZOMBIE_DWARF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.ZOMBIE_CHEF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.ZOMBIE_CHEF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.ZOMBIE_CYBORG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.ZOMBIE_HEROBRINE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(EntityType.GIANT, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Monster::checkAnyLightMonsterSpawnRules);
					SpawnPlacements.register(Registration.ZOMBIE_KING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.ZOMBIE_KNIGHT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.ZOMBIE_MINER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.ZOMBIE_PA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.ZOMBIE_PIRATE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractMoZombie::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.ZOMBIE_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ZombieCreeper::checkMonsterSpawnRules);
					SpawnPlacements.register(Registration.SURVIVOR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Survivor::checkMobSpawnRules);
		}
		);

	}


}

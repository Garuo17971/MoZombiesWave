package wily.mozombieswave.init;


import com.google.common.collect.ImmutableMap;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import wily.mozombieswave.Config;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.entity.*;


public class ClientEvents {



	public static void entityRenderEvent(MoZombiesPlatform.FactocraftyEntityRendererRegistry event) {

		EntityRendererProvider<AbstractMoZombie> dwarfRenderFactory = ZombieDwarf.RendererZombieDwarf::new;
		if (Config.oldDwarfZombieModel)  dwarfRenderFactory = manager -> new AbstractMoZombie.MoZombieRenderer(manager, ZombieDwarf.name);
		event.register(Registration.DISCO_ZOMBIE, manager -> new AbstractMoZombie.MoZombieRenderer(manager, DiscoZombie.name));
		event.register(Registration.NETHER_ZOMBIE, manager -> new AbstractMoZombie.MoZombieRenderer(manager, NetherZombie.name));
		event.register(Registration.ZOMBIE_CHEF, manager -> new AbstractMoZombie.MoZombieRenderer(manager, ZombieChef.name));
		event.register(Registration.ZOMBIE_CYBORG, manager -> new AbstractMoZombie.MoZombieRenderer(manager, ZombieCyborg.name));
		event.register(Registration.ZOMBIE_HEROBRINE, manager -> new AbstractMoZombie.MoZombieRenderer(manager, ZombieHerobrine.name));
		event.register(Registration.ZOMBIE_KING, manager -> new AbstractMoZombie.MoZombieRenderer(manager, ZombieKing.name));
		event.register(Registration.ZOMBIE_KNIGHT, manager -> new AbstractMoZombie.MoZombieRenderer(manager, ZombieKnight.name));
		event.register(Registration.ZOMBIE_MINER, manager -> new AbstractMoZombie.MoZombieRenderer(manager, ZombieMiner.name));
		event.register(Registration.ZOMBIE_NOTCH, manager -> new AbstractMoZombie.MoZombieRenderer(manager, ZombieNotch.name));
		event.register(Registration.ZOMBIE_PA, manager -> new AbstractMoZombie.MoZombieRenderer(manager, ZombiePa.name));
		event.register(Registration.ZOMBIE_PIRATE, manager -> new AbstractMoZombie.MoZombieRenderer(manager, ZombiePirate.name));
		event.register(Registration.SURVIVOR, Survivor.SurvivorRenderer::new);
		event.register(Registration.ZOMBIE_CREEPER, ZombieCreeper.CreeperRenderer::new);
		event.register(Registration.ZOMBIE_DWARF, dwarfRenderFactory);
	}


	public static void registerLayerDefinition(MoZombiesPlatform.FactocraftyModelLayerRegistry event) {

		ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> builder = ImmutableMap.builder();
		event.register(ZombieDwarf.ZombieDwarfModel.DWARF, () -> ZombieDwarf.ZombieDwarfModel.createBodyLayer(new CubeDeformation(0), 64));
		event.register(ZombieDwarf.ZombieDwarfModel.DWARF_INNER, () -> ZombieDwarf.ZombieDwarfModel.createBodyLayer(new CubeDeformation(0.5F), 32));
		event.register(ZombieDwarf.ZombieDwarfModel.DWARF_OUTER, () -> ZombieDwarf.ZombieDwarfModel.createBodyLayer(new CubeDeformation(1F), 32));
	}
}

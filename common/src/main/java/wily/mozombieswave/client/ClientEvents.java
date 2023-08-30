package wily.mozombieswave.client;


import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Creeper;
import wily.mozombieswave.Config;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.client.entity.MoZombieRenderer;
import wily.mozombieswave.client.entity.SurvivorRenderer;
import wily.mozombieswave.client.entity.ZombieDwarfRenderer;
import wily.mozombieswave.entity.*;
import wily.mozombieswave.init.Registration;


public class ClientEvents {



	public static void entityRenderEvent(MoZombiesPlatform.EntityRendererRegistry event) {

		MoZombiesPlatform.EntityRendererProvider<ZombieDwarf> dwarfRenderFactory = ZombieDwarfRenderer::new;
		if (Config.oldDwarfZombieModel)  dwarfRenderFactory = manager -> new MoZombieRenderer(manager, ZombieDwarf.name);
		event.register(Registration.DISCO_ZOMBIE, manager -> new MoZombieRenderer(manager, DiscoZombie.name));
		event.register(Registration.NETHER_ZOMBIE, manager -> new MoZombieRenderer(manager, NetherZombie.name));
		event.register(Registration.ZOMBIE_CHEF, manager -> new MoZombieRenderer(manager, ZombieChef.name));
		event.register(Registration.ZOMBIE_CYBORG, manager -> new MoZombieRenderer(manager, ZombieCyborg.name));
		event.register(Registration.ZOMBIE_HEROBRINE, manager -> new MoZombieRenderer(manager, ZombieHerobrine.name));
		event.register(Registration.ZOMBIE_KING, manager -> new MoZombieRenderer(manager, ZombieKing.name));
		event.register(Registration.ZOMBIE_KNIGHT, manager -> new MoZombieRenderer(manager, ZombieKnight.name));
		event.register(Registration.ZOMBIE_MINER, manager -> new MoZombieRenderer(manager, ZombieMiner.name));
		event.register(Registration.ZOMBIE_NOTCH, manager -> new MoZombieRenderer(manager, ZombieNotch.name));
		event.register(Registration.ZOMBIE_PA, manager -> new MoZombieRenderer(manager, ZombiePa.name));
		event.register(Registration.ZOMBIE_PIRATE, manager -> new MoZombieRenderer(manager, ZombiePirate.name));
		event.register(Registration.SURVIVOR, SurvivorRenderer::new);
		event.register(Registration.ZOMBIE_CREEPER, d-> new CreeperRenderer(d){
			@Override
			public ResourceLocation getTextureLocation(Creeper creeper) {
				return new ResourceLocation(MoZombiesWave.MODID, "textures/entity/" + ZombieCreeper.name +".png");
			}
		});
		event.register(Registration.ZOMBIE_DWARF, dwarfRenderFactory);
	}


}

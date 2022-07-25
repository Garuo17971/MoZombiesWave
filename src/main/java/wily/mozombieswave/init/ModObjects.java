package wily.mozombieswave.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import wily.mozombieswave.MozombiesWaveMod;
import wily.mozombieswave.entity.*;

import javax.annotation.Nonnull;

@EventBusSubscriber(modid = MozombiesWaveMod.MODID)
public class ModObjects{


	public static Class<? extends EntityMoZombieBase>[] commonZombies = new Class[]{EntityDiscoZombie.class, EntityZombieDwarf.class, EntityZombieChef.class,EntityZombieCyborg.class,EntityZombieCreeper.class,EntityZombieHerobrine.class,EntityZombieKing.class,EntityZombieKnight.class,EntityZombieMiner.class,EntityZombieNotch.class,EntityZombiePa.class,EntityZombiePirate.class};

	@SubscribeEvent
	public static void entities(@Nonnull final Register<EntityEntry> e) {
		e.getRegistry().registerAll(
				EntityEntryBuilder.create().entity(EntitySurvivor.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntitySurvivor.name), EntitySurvivor.ID)
				.name(EntitySurvivor.name).tracker(64, 3, true).egg(-9611202, -12053227).build(),
				EntityEntryBuilder.create().entity(EntityZombieDwarf.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityZombieDwarf.name), EntityZombieDwarf.ID)
				.name(EntityZombieDwarf.name).tracker(64, 3, true).egg(-3355444, -16738048).build(),
				EntityEntryBuilder.create().entity(EntityDiscoZombie.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityDiscoZombie.name), EntityDiscoZombie.ID)
				.name(EntityDiscoZombie.name).tracker(64, 3, true).egg(-3342388, -16777216).build(),
				EntityEntryBuilder.create().entity(EntityNetherZombie.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityNetherZombie.name), EntityNetherZombie.ID)
				.name(EntityNetherZombie.name).tracker(64, 3, true).egg(-10092544, -13434880).build(),
				EntityEntryBuilder.create().entity(EntityZombieChef.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityZombieChef.name), EntityZombieChef.ID)
						.name(EntityZombieChef.name).tracker(64, 3, true).egg(-3342388, -6750208).build(),
				EntityEntryBuilder.create().entity(EntityZombieMiner.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityZombieMiner.name), EntityZombieMiner.ID)
						.name(EntityZombieMiner.name).tracker(64, 3, true).egg(-6750208, -13434676).build(),
				EntityEntryBuilder.create().entity(EntityZombieCreeper.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityZombieCreeper.name), EntityZombieCreeper.ID)
						.name(EntityZombieCreeper.name).tracker(64, 3, true).egg(-16724992, -16724788).build(),
				EntityEntryBuilder.create().entity(EntityZombieHerobrine.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityZombieHerobrine.name), EntityZombieHerobrine.ID)
						.name(EntityZombieHerobrine.name).tracker(64, 3, true).egg(-16751104, -16751002).build(),
				EntityEntryBuilder.create().entity(EntityZombieCyborg.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityZombieCyborg.name), EntityZombieCyborg.ID)
				.name(EntityZombieCyborg.name).tracker(64, 3, true).egg(-10066330, -6750208).build(),
				EntityEntryBuilder.create().entity(EntityZombieKing.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityZombieKing.name), EntityZombieKing.ID)
						.name(EntityZombieKing.name).tracker(64, 3, true).egg(-16751104, -3355648).build(),
				EntityEntryBuilder.create().entity(EntityZombiePa.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityZombiePa.name), EntityZombiePa.ID)
						.name(EntityZombiePa.name).tracker(64, 3, true).egg(-10066330, -16737997).build(),
				EntityEntryBuilder.create().entity(EntityZombieKnight.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityZombieKnight.name), EntityZombieKnight.ID)
						.name(EntityZombieKnight.name).tracker(64, 3, true).egg(-16738048, -10066330).build(),
				EntityEntryBuilder.create().entity(EntityZombiePirate.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityZombiePirate.name), EntityZombiePirate.ID)
						.name(EntityZombiePirate.name).tracker(64, 3, true).egg(-16738048, -10092544).build(),
				EntityEntryBuilder.create().entity(EntityZombieNotch.class).id(new ResourceLocation(MozombiesWaveMod.MODID, EntityZombieNotch.name), EntityZombieNotch.ID)
						.name(EntityZombieNotch.name).tracker(64, 3, true).egg(-11851502, -16738048).build());
	}

	public static final SoundEvent HURT_SURVIVOR;
	public static final SoundEvent HURT_SURVIVOR_FEMALE;

	static {
		HURT_SURVIVOR = addSoundsToRegistry("hurt_survivor");
		HURT_SURVIVOR_FEMALE = addSoundsToRegistry("hurt_survivor_female");
	}

	private static SoundEvent addSoundsToRegistry(String soundId) {
		ResourceLocation shotSoundLocation = new ResourceLocation(MozombiesWaveMod.MODID, soundId);
		SoundEvent soundEvent = new SoundEvent(shotSoundLocation);
		soundEvent.setRegistryName(shotSoundLocation);
		return soundEvent;
	}

}

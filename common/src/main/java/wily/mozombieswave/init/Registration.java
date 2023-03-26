package wily.mozombieswave.init;

import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.MobSpawnSettings;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.entity.*;
import wily.mozombieswave.item.DiscoGlassesMaterial;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static wily.mozombieswave.MoZombiesPlatform.*;


public class Registration {



	public static CreativeModeTab getPlatformCreativeModeTab(CreativeModeTab.Builder builder){
		return builder.title(Component.translatable("itemGroup.mozombies_wave.tab")).icon(()-> Registration.DISCO_GLASSES.get().getDefaultInstance()).displayItems((f, b)->{
			b.accept(Registration.SURVIVOR_SPAWN_EGG.get());
			b.accept(Registration.DISCO_SPAWN_EGG.get());
			b.accept(Registration.CHEF_SPAWN_EGG.get());
			b.accept(Registration.CYBORG_SPAWN_EGG.get());
			b.accept(Registration.HEROBRINE_SPAWN_EGG.get());
			b.accept(Registration.KING_SPAWN_EGG.get());
			b.accept(Registration.KNIGHT_SPAWN_EGG.get());
			b.accept(Registration.MINER_SPAWN_EGG.get());
			b.accept(Registration.NOTCH_SPAWN_EGG.get());
			b.accept(Registration.PA_SPAWN_EGG.get());
			b.accept(Registration.PIRATE_SPAWN_EGG.get());
			b.accept(Registration.DWARF_SPAWN_EGG.get());
			b.accept(Registration.NETHER_ZOMBIE_SPAWN_EGG.get());
			b.accept(Registration.DISCO_GLASSES.get());
		}).build();
	}

	public static final Supplier<Item> DISCO_GLASSES = registerItem("disco_glasses", () -> new ArmorItem(DiscoGlassesMaterial.GLASSES, ArmorItem.Type.HELMET, new Item.Properties()));

	public static final Supplier<SoundEvent> HURT_SURVIVOR = registerSoundEvent("hurt_survivor", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MoZombiesWave.MODID, "hurt_survivor")));
	public static final Supplier<SoundEvent> HURT_SURVIVOR_FEMALE = registerSoundEvent("hurt_survivor_female", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MoZombiesWave.MODID, "hurt_survivor_female")));

	public static final Supplier<Item> GIANT_SPAWN_EGG = registerItem( "giant_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(()->EntityType.GIANT, 44975, 7969893, new Item.Properties()));

	public static final Supplier<EntityType<Survivor>> SURVIVOR = registerEntityType(Survivor.name, () -> EntityType.Builder.of(Survivor::new, MobCategory.CREATURE).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, Survivor.name).toString()));

	public static final Supplier<Item> SURVIVOR_SPAWN_EGG = registerItem(Survivor.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(SURVIVOR, -9611202, -12053227, new Item.Properties()));

	public static final Supplier<EntityType<DiscoZombie>> DISCO_ZOMBIE = registerEntityType(DiscoZombie.name, () -> EntityType.Builder.of(DiscoZombie::new, MobCategory.MONSTER).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, DiscoZombie.name).toString()));
	public static final Supplier<Item> DISCO_SPAWN_EGG = registerItem(DiscoZombie.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(DISCO_ZOMBIE, -3342388, -16777216, new Item.Properties()));

	public static final Supplier<EntityType<ZombieChef>> ZOMBIE_CHEF = registerEntityType(ZombieChef.name, () -> EntityType.Builder.of(ZombieChef::new, MobCategory.MONSTER).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, ZombieChef.name).toString()));
	public static final Supplier<Item> CHEF_SPAWN_EGG = registerItem(ZombieChef.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(ZOMBIE_CHEF, -3342388, -6750208, new Item.Properties()));

	public static final Supplier<EntityType<ZombieCyborg>> ZOMBIE_CYBORG = registerEntityType(ZombieCyborg.name, () -> EntityType.Builder.of(ZombieCyborg::new, MobCategory.MONSTER).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, ZombieCyborg.name).toString()));
	public static final Supplier<Item> CYBORG_SPAWN_EGG = registerItem(ZombieCyborg.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(ZOMBIE_CYBORG, -10066330, -6750208, new Item.Properties()));

	public static final Supplier<EntityType<ZombieHerobrine>> ZOMBIE_HEROBRINE = registerEntityType(ZombieHerobrine.name, () -> EntityType.Builder.of(ZombieHerobrine::new, MobCategory.MONSTER).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, ZombieHerobrine.name).toString()));
	public static final Supplier<Item> HEROBRINE_SPAWN_EGG = registerItem(ZombieHerobrine.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(ZOMBIE_HEROBRINE, -16751104, -16751002, new Item.Properties()));

	public static final Supplier<EntityType<ZombieKing>> ZOMBIE_KING = registerEntityType(ZombieKing.name, () -> EntityType.Builder.of(ZombieKing::new, MobCategory.MONSTER).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, ZombieKing.name).toString()));
	public static final Supplier<Item> KING_SPAWN_EGG = registerItem(ZombieKing.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(ZOMBIE_KING, -16751104, -3355648, new Item.Properties()));

	public static final Supplier<EntityType<ZombieKnight>> ZOMBIE_KNIGHT = registerEntityType(ZombieKnight.name, () -> EntityType.Builder.of(ZombieKnight::new, MobCategory.MONSTER).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, ZombieKnight.name).toString()));
	public static final Supplier<Item> KNIGHT_SPAWN_EGG = registerItem(ZombieKnight.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(ZOMBIE_KNIGHT, -16738048, -10066330, new Item.Properties()));

	public static final Supplier<EntityType<ZombieMiner>> ZOMBIE_MINER = registerEntityType(ZombieMiner.name, () -> EntityType.Builder.of(ZombieMiner::new, MobCategory.MONSTER).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, ZombieMiner.name).toString()));
	public static final Supplier<Item> MINER_SPAWN_EGG = registerItem(ZombieMiner.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(ZOMBIE_MINER, -6750208, -13434676, new Item.Properties()));

	public static final Supplier<EntityType<ZombieNotch>> ZOMBIE_NOTCH = registerEntityType(ZombieNotch.name, () -> EntityType.Builder.of(ZombieNotch::new, MobCategory.MONSTER).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, ZombieNotch.name).toString()));
	public static final Supplier<Item> NOTCH_SPAWN_EGG = registerItem(ZombieNotch.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(ZOMBIE_NOTCH, -11851502, -16738048, new Item.Properties()));

	public static final Supplier<EntityType<ZombiePa>> ZOMBIE_PA = registerEntityType(ZombiePa.name, () -> EntityType.Builder.of(ZombiePa::new, MobCategory.MONSTER).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, ZombiePa.name).toString()));
	public static final Supplier<Item> PA_SPAWN_EGG = registerItem(ZombiePa.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(ZOMBIE_PA, -10066330, -16737997, new Item.Properties()));

	public static final Supplier<EntityType<ZombiePirate>> ZOMBIE_PIRATE = registerEntityType(ZombiePirate.name, () -> EntityType.Builder.of(ZombiePirate::new, MobCategory.MONSTER).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, ZombiePirate.name).toString()));
	public static final Supplier<Item> PIRATE_SPAWN_EGG = registerItem(ZombiePirate.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(ZOMBIE_PIRATE, -16738048, -10092544, new Item.Properties()));

	public static final Supplier<EntityType<ZombieDwarf>> ZOMBIE_DWARF = registerEntityType(ZombieDwarf.name, () -> EntityType.Builder.of(ZombieDwarf::new, MobCategory.MONSTER).sized(0.6f, 1.7f).clientTrackingRange(50).build(new ResourceLocation(MoZombiesWave.MODID, ZombieDwarf.name).toString()));
	public static final Supplier<Item> DWARF_SPAWN_EGG = registerItem(ZombieDwarf.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(ZOMBIE_DWARF, -3355444, -16738048, new Item.Properties()));

	public static final Supplier<EntityType<NetherZombie>> NETHER_ZOMBIE = registerEntityType(NetherZombie.name, () -> EntityType.Builder.of(NetherZombie::new, MobCategory.MONSTER).sized(0.6f, 1.95F).clientTrackingRange(25).build(new ResourceLocation(MoZombiesWave.MODID, NetherZombie.name).toString()));
	public static final Supplier<Item> NETHER_ZOMBIE_SPAWN_EGG = registerItem(NetherZombie.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(NETHER_ZOMBIE, -10092544, -13434880, new Item.Properties()));

	public static final Supplier<EntityType<ZombieCreeper>> ZOMBIE_CREEPER = registerEntityType(ZombieCreeper.name, () -> EntityType.Builder.of(ZombieCreeper::new, MobCategory.MONSTER).sized(0.6f, 1.51f).clientTrackingRange(50).build(new ResourceLocation(MoZombiesWave.MODID, ZombieCreeper.name).toString()));
	public static final Supplier<Item> ZOMBIE_CREEPER_SPAWN_EGG = registerItem(ZombieCreeper.name + "_spawn_egg", () -> MoZombiesPlatform.getPlatformSpawnEgg(ZOMBIE_CREEPER, -16724992, -16724788, new Item.Properties()));


	public static ResourceLocation getModId(String name){
		return new ResourceLocation(MoZombiesWave.MODID, name);
	}

	public static void registerObjects(){

	}
	public static void registerEntityAttributes(MoZombiesPlatform.AttributeRegister attribute){
		attribute.register(DISCO_ZOMBIE.get(), DiscoZombie::createAttributes);
		attribute.register(ZOMBIE_DWARF.get(), ZombieDwarf::createAttributes);
		attribute.register(ZOMBIE_CHEF.get(), ZombieChef::createAttributes);
		attribute.register(ZOMBIE_CYBORG.get(), ZombieCyborg::createAttributes);
		attribute.register(ZOMBIE_HEROBRINE.get(), ZombieHerobrine::createAttributes);
		attribute.register(ZOMBIE_KING.get(), ZombieKing::createAttributes);
		attribute.register(ZOMBIE_KNIGHT.get(), ZombieKnight::createAttributes);
		attribute.register(ZOMBIE_MINER.get(), ZombieMiner::createAttributes);
		attribute.register(ZOMBIE_NOTCH.get(), ZombieNotch::createAttributes);
		attribute.register(ZOMBIE_PA.get(), ZombiePa::createAttributes);
		attribute.register(ZOMBIE_PIRATE.get(), ZombiePirate::createAttributes);
		attribute.register(NETHER_ZOMBIE.get(), DiscoZombie::createAttributes);
		attribute.register(SURVIVOR.get(), Survivor::createAttributes);
		attribute.register(ZOMBIE_CREEPER.get(), ZombieCreeper::createAttributes);
	}

	public static void onEntityJoinWorld(Entity entity, Level level) {
		if(entity instanceof Giant giant) {
			giant.goalSelector.addGoal(1, new LookAtPlayerGoal(giant, Player.class, 8.0F));
			giant.goalSelector.addGoal(1, new RandomLookAroundGoal(giant));
			giant.goalSelector.addGoal(2, new MeleeAttackGoal(giant, 1.0D, false));
			giant.goalSelector.addGoal(6, new MoveThroughVillageGoal(giant, 1.0D, true, 4, () -> true));
			giant.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(giant, 1.0D));
			giant.goalSelector.addGoal(1, (new HurtByTargetGoal(giant)).setAlertOthers(ZombifiedPiglin.class));
			giant.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(giant, ZombieNotch.class, true));
			giant.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(giant, Player.class, true));
			giant.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(giant, Villager.class, true));
			giant.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(giant, IronGolem.class, true));
			giant.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(giant, Turtle.class, true));
		}
	}

}

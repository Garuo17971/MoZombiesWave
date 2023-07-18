package wily.mozombieswave.forge;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.init.Registration;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MoZombiesPlatformImpl {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoZombiesWave.MODID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoZombiesWave.MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MoZombiesWave.MODID);

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MoZombiesWave.MODID);

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZER = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MoZombiesWave.MODID);
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }


    public static RegistryObject<Codec<SimpleBiomeModifier>> GHOST_CODEC_MODIFIER =  BIOME_MODIFIER_SERIALIZER.register("spawn_all_biomes", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(SimpleBiomeModifier::biomes),
                    new ExtraCodecs.EitherCodec<>(MobSpawnSettings.SpawnerData.CODEC.listOf(), MobSpawnSettings.SpawnerData.CODEC).xmap(
                            either -> either.map(Function.identity(), List::of), // convert list/singleton to list when decoding
                            list -> list.size() == 1 ? Either.right(list.get(0)) : Either.left(list) // convert list to singleton/list when encoding
                    ).fieldOf("spawners").forGetter(SimpleBiomeModifier::spawners)
            ).apply(builder, SimpleBiomeModifier::new)));

    public static Supplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(name,item);
    }

    public static <T extends LivingEntity> Supplier<EntityType<T>> registerEntityType(String name, Supplier<EntityType<T>> entity) {
        return ENTITIES.register(name, entity);
    }

    public static Supplier<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> sound) {
        return SOUNDS.register(name,sound);
    }
    public static Supplier<CreativeModeTab> registerCreativeTab(String name, Supplier<CreativeModeTab> sound) {
        return CREATIVE_TABS.register(name,sound);
    }
    @SubscribeEvent
    public static void onEntityJoinWorld(final EntityJoinLevelEvent event){
        Registration.onEntityJoinWorld(event.getEntity(),event.getLevel());
    }

    public static void init(IEventBus bus){
        MoZombiesPlatformImpl.ITEMS.register(bus);
        MoZombiesPlatformImpl.SOUNDS.register(bus);
        MoZombiesPlatformImpl.ENTITIES.register(bus);
        MoZombiesPlatformImpl.BIOME_MODIFIER_SERIALIZER.register(bus);
    }

    public static SpawnEggItem getPlatformSpawnEgg(Supplier<? extends EntityType<? extends Mob>> entityType, int i, int j, Item.Properties properties) {
    return new ForgeSpawnEggItem(entityType,i,j,properties);
    }

    public record SimpleBiomeModifier(HolderSet<Biome> biomes, List<MobSpawnSettings.SpawnerData> spawners) implements BiomeModifier {

        @Override
        public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
            if (phase == Phase.ADD)
            {
                MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
                for(MobSpawnSettings.SpawnerData spawn : spawners) {
                    if (!biomes.contains(biome) && !biome.is(BiomeTags.IS_END)) spawns.addSpawn(spawn.type.getCategory(), spawn);
                }
            }
        }
        @Override
        public Codec<? extends BiomeModifier> codec() {return GHOST_CODEC_MODIFIER.get();}
    };
}

package wily.mozombieswave.forge;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.init.Registration;

import java.nio.file.Path;
import java.util.function.Supplier;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MoZombiesPlatformImpl {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registry.ITEM_REGISTRY, MoZombiesWave.MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registry.SOUND_EVENT_REGISTRY, MoZombiesWave.MODID);

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registry.ENTITY_TYPE_REGISTRY, MoZombiesWave.MODID);

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZER = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MoZombiesWave.MODID);
    public static final DeferredRegister<BiomeModifier> BIOME_MODIFIER = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIERS, MoZombiesWave.MODID);

    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }


    public static RegistryObject<Codec<? extends BiomeModifier>> GHOST_CODEC_MODIFIER =  BIOME_MODIFIER_SERIALIZER.register("ghost_biome_modifier_codec", ()->Codec.unit(SimpleBiomeModifier.INSTANCE));

    public static RegistryObject<SimpleBiomeModifier> SIMPLE_BIOME_MODIFIER =  BIOME_MODIFIER.register("simple_biome_modifier", ()-> SimpleBiomeModifier.INSTANCE);
    public static Supplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(name,item);
    }

    public static <T extends LivingEntity> Supplier<EntityType<T>> registerEntityType(String name, Supplier<EntityType<T>> entity) {
        return ENTITIES.register(name, entity);
    }

    public static Supplier<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> sound) {
        return SOUNDS.register(name,sound);
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
        MoZombiesPlatformImpl.BIOME_MODIFIER.register(bus);
    }

    public static CreativeModeTab getPlatformCreativeModeTab(ResourceLocation id, Supplier<ItemStack> itemStackSupplier) {
        return new CreativeModeTab(id.toString().replace(":",".")) {
            @Override
            public ItemStack makeIcon() {
                return itemStackSupplier.get();
            }
        };
    }

    public static class SimpleBiomeModifier implements BiomeModifier {

        public static final SimpleBiomeModifier INSTANCE = new SimpleBiomeModifier();

        @Override
        public void modify(Holder<Biome> arg, BiomeModifier.Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
            if (phase == Phase.ADD) Registration.addEntitiesSpawn((c, s, e, w, min, max)-> {if (c.test(new MoZombiesPlatform.BiomeContext(arg.get(), arg::containsTag))) builder.getMobSpawnSettings().addSpawn(s,new MobSpawnSettings.SpawnerData(e,w,min,max));});
        }

        @Override
        public Codec<? extends BiomeModifier> codec() {return GHOST_CODEC_MODIFIER.orElse(Codec.unit(INSTANCE));}
    };
}

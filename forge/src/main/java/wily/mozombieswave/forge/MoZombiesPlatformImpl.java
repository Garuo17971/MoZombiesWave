package wily.mozombieswave.forge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.init.Registration;

import java.nio.file.Path;
import java.util.function.Supplier;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MoZombiesPlatformImpl {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoZombiesWave.MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MoZombiesWave.MODID);

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MoZombiesWave.MODID);


    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }



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
    public static void onEntityJoinWorld(final EntityJoinWorldEvent event){
        Registration.onEntityJoinWorld(event.getEntity(),event.getWorld());
    }
    public static void init(IEventBus bus){
        MoZombiesPlatformImpl.ITEMS.register(bus);
        MoZombiesPlatformImpl.SOUNDS.register(bus);
        MoZombiesPlatformImpl.ENTITIES.register(bus);
    }

    public static CreativeModeTab getPlatformCreativeModeTab(ResourceLocation id, Supplier<ItemStack> itemStackSupplier) {
        return new CreativeModeTab(id.toString().replace(":",".")) {
            @Override
            public ItemStack makeIcon() {
                return itemStackSupplier.get();
            }
        };
    }
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        Registration.addEntitiesSpawn((context, spawnGroup, entityType, weight, minGroupSize, maxGroupSize) -> {
            if (context.test(new MoZombiesPlatform.BiomeContext(event.getCategory(),event.getName())))
                event.getSpawns().addSpawn(spawnGroup, new MobSpawnSettings.SpawnerData(entityType, weight, minGroupSize, maxGroupSize));
        });
    }

    public static SpawnEggItem getPlatformSpawnEggInstance(Supplier<? extends EntityType<?>> entityType, int i, int j, Item.Properties properties) {
        return new ForgeSpawnEggItem(entityType,i,j,properties);
    }

}

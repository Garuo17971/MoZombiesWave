package wily.mozombieswave.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;

import java.nio.file.Path;
import java.util.function.Supplier;

import static wily.mozombieswave.init.Registration.getModId;

public class MoZombiesPlatformImpl {

    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static Supplier<Item> registerItem(String name, Supplier<Item> item) {
        Registry.register(BuiltInRegistries.ITEM,getModId(name),item.get());
        return () -> BuiltInRegistries.ITEM.get(getModId(name));
    }
    public static <T extends LivingEntity> Supplier<EntityType<T>> registerEntityType(String name, Supplier<EntityType<T>> entity) {
        Registry.register(BuiltInRegistries.ENTITY_TYPE,getModId(name),entity.get());
        return () -> (EntityType<T>) BuiltInRegistries.ENTITY_TYPE.get(getModId(name));
    }

    public static Supplier<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> sound) {
        Registry.register(BuiltInRegistries.SOUND_EVENT,getModId(name),sound.get());
        return () -> BuiltInRegistries.SOUND_EVENT.get(getModId(name));
    }

    public static SpawnEggItem getPlatformSpawnEgg(Supplier<? extends EntityType<? extends Mob>> entityType, int i, int j, Item.Properties properties) {
        return new SpawnEggItem(entityType.get(),i,j,properties);
    }


}
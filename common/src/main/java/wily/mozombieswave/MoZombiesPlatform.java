package wily.mozombieswave;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.biome.Biome;
import wily.mozombieswave.init.Registration;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static wily.mozombieswave.init.Registration.getModId;

public class MoZombiesPlatform {
    @ExpectPlatform
    public static Path getConfigDirectory() {
    throw new AssertionError();
    }

    @ExpectPlatform
    public static SpawnEggItem getPlatformSpawnEgg(Supplier<? extends EntityType<? extends Mob>> entityType, int i, int j, Item.Properties properties){
        throw new AssertionError();
    }

    public interface AttributeRegister{
        void register(EntityType<? extends LivingEntity> type, Supplier<AttributeSupplier.Builder> supplier);
    }

    public interface FactocraftyEntityRendererRegistry {
        <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider);
    }

    public interface FactocraftyModelLayerRegistry {
        void register(ModelLayerLocation location, Supplier<LayerDefinition> definition);
    }


    @ExpectPlatform
    public static Supplier<Item> registerItem(String name, Supplier<Item> item){
        throw new AssertionError();
    }
    @ExpectPlatform
    public static <T extends LivingEntity> Supplier<EntityType<T>> registerEntityType(String name, Supplier<EntityType<T>> entity){
        throw new AssertionError();
    }
    @ExpectPlatform
    public static Supplier<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> sound){
        throw new AssertionError();
    }
}

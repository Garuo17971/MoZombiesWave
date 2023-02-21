package wily.mozombieswave;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MoZombiesPlatform {
    @ExpectPlatform
    public static Path getConfigDirectory() {
    throw new AssertionError();
    }

    @ExpectPlatform
    public static CreativeModeTab getPlatformCreativeModeTab(ResourceLocation id, Supplier<ItemStack> itemStackSupplier){
        throw new AssertionError();
    }
    public interface AttributeRegister{
        void register(EntityType<? extends LivingEntity> type, Supplier<AttributeSupplier.Builder> supplier);
    }
    public record BiomeContext(Biome biome, Predicate<TagKey<Biome>> predicate){
        public boolean hasTag(TagKey<Biome> tag){
            return predicate.test(tag);
        }
    }
    public interface FactocraftyEntityRendererRegistry {
        <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider);
    }

    public interface FactocraftyModelLayerRegistry {
        void register(ModelLayerLocation location, Supplier<LayerDefinition> definition);
    }

    public interface EntitySpawnAddition {
        void addSpawn(Predicate<BiomeContext> context, MobCategory spawnGroup, EntityType<?> entityType,
                      int weight, int minGroupSize, int maxGroupSize);
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

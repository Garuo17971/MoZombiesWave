package wily.mozombieswave;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
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
    public static class BiomeContext{
        public final Biome.BiomeCategory category;
        public final ResourceLocation resourceLocation;
        public BiomeContext(Biome.BiomeCategory category, ResourceLocation resourceLocation){
            this.category = category;
            this.resourceLocation = resourceLocation;
        }
    }
    public interface EntityRendererRegistry {
        <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider);
    }
    public interface EntityRendererProvider<T extends Entity> {
        EntityRenderer<? super T> create(EntityRenderDispatcher manager);
    }


    public interface EntitySpawnAddition {
        void addSpawn(Predicate<BiomeContext> context, MobCategory spawnGroup, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize);
    }

    @ExpectPlatform
    public static Supplier<Item> registerItem(String name, Supplier<Item> item){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static SpawnEggItem getPlatformSpawnEggInstance(Supplier<? extends EntityType<?>> entityType, int i, int j, Item.Properties properties){
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

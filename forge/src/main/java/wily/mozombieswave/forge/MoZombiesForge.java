package wily.mozombieswave.forge;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import wily.mozombieswave.MoZombiesPlatform;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.init.Registration;

import static wily.mozombieswave.MoZombiesWave.MODID;

@Mod(MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoZombiesForge {

    public static IEventBus MOD_EVENT_BUS;
    public MoZombiesForge(){
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MOD_EVENT_BUS.register(this);
        MOD_EVENT_BUS.register(Registration.class);
        MoZombiesWave.init();
        MoZombiesPlatformImpl.init(MOD_EVENT_BUS);
    }
    @SubscribeEvent
    public static void setupCommon(FMLCommonSetupEvent event){
        event.enqueueWork(MoZombiesWave::common);
    }
    @SubscribeEvent
    public static void setup(final EntityAttributeCreationEvent event) {
        Registration.registerEntityAttributes(((type, supplier) -> event.put(type,supplier.get().build())));
    }


}

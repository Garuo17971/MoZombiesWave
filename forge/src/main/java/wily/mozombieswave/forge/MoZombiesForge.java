package wily.mozombieswave.forge;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
    public static void registerEntitiesSpawnPlacement(SpawnPlacementRegisterEvent event){
        Registration.registerEntitiesSpawnPlacement(new MoZombiesPlatform.SpawnPlacementsRegister() {
            public <T extends Mob> void register(EntityType<T> entityType, SpawnPlacements.Type type, Heightmap.Types types, SpawnPlacements.SpawnPredicate<T> spawnPredicate) {
                event.register(entityType, type, types, spawnPredicate, SpawnPlacementRegisterEvent.Operation.AND);
            }
        });
    }
    @SubscribeEvent
    public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
        Registration.registerEntityAttributes(((type, supplier) -> event.put(type,supplier.get().build())));
    }


}

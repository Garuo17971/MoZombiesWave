package wily.mozombieswave.fabric;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.minecraftforge.fml.config.ModConfig;
import wily.mozombieswave.ForgeConfigCompat;
import wily.mozombieswave.MoZombiesWave;

public class ForgeConfigCompatImpl {

    public static void registerConfigs(){
        ForgeConfigRegistry.INSTANCE.register(MoZombiesWave.MODID, ModConfig.Type.CLIENT, ForgeConfigCompat.CLIENT_CONFIG);
        ForgeConfigRegistry.INSTANCE.register(MoZombiesWave.MODID,ModConfig.Type.COMMON, ForgeConfigCompat.COMMON_CONFIG);
    }

}

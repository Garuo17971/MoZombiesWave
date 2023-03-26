package wily.mozombieswave.forge;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import wily.mozombieswave.Config;
import wily.mozombieswave.ForgeConfigCompat;
import wily.mozombieswave.MoZombiesWave;

public class ConfigImpl {
    public static void setupPlatformConfig(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ForgeConfigCompat.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ForgeConfigCompat.COMMON_CONFIG);
        ForgeConfigCompat.setupConfig();
    }
}

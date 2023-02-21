package wily.mozombieswave.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import wily.mozombieswave.Config;
import wily.mozombieswave.ForgeConfigCompat;
import wily.mozombieswave.MoZombiesWave;

public class ConfigImpl {
    public static void setupPlatformConfig() {
        if (FabricLoader.getInstance().isModLoaded("forgeconfigapiport")) {
            ModLoadingContext.registerConfig(MoZombiesWave.MODID,ModConfig.Type.CLIENT, ForgeConfigCompat.CLIENT_CONFIG);
            ModLoadingContext.registerConfig(MoZombiesWave.MODID,ModConfig.Type.COMMON, ForgeConfigCompat.COMMON_CONFIG);
            ForgeConfigCompat.setupConfig();
        } else MoZombiesWave.LOGGER.warn("Currently ForgeConfigApiPort isn't installed, to config  BetterFurnaces options, please consider install it!");
    }
}

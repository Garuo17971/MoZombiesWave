package wily.mozombieswave.fabric;

import net.fabricmc.loader.api.FabricLoader;
import wily.mozombieswave.ForgeConfigCompat;
import wily.mozombieswave.MoZombiesWave;

public class ConfigImpl {
    public static void setupPlatformConfig() {
        if (FabricLoader.getInstance().isModLoaded("forgeconfigapiport")) {
            ForgeConfigCompatImpl.registerConfigs();
            ForgeConfigCompat.setupConfig();
        } else MoZombiesWave.LOGGER.warn("Currently ForgeConfigApiPort isn't installed, to config  BetterFurnaces options, please consider install it!");
    }
}

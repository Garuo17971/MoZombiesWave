package wily.mozombieswave.fabric;

import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import wily.mozombieswave.ForgeConfigCompat;
import wily.mozombieswave.MoZombiesWave;

public class ForgeConfigCompatImpl {

    public static void registerConfigs(){
        ModLoadingContext.registerConfig(MoZombiesWave.MODID, ModConfig.Type.CLIENT, ForgeConfigCompat.CLIENT_CONFIG);
        ModLoadingContext.registerConfig(MoZombiesWave.MODID,ModConfig.Type.COMMON, ForgeConfigCompat.COMMON_CONFIG);
    }

}

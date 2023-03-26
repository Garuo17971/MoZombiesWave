package wily.mozombieswave;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ForgeConfigCompat {




    public static ForgeConfigSpec CLIENT_CONFIG;
    public static final ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue checkUpdates;

    public static ForgeConfigSpec.BooleanValue oldDwarfZombieModel;


    private static final String CATEGORY_UPDATES = "updates";

    private static final String CATEGORY_GENERAL = "general";


    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("Settings").push(CATEGORY_GENERAL);

        oldDwarfZombieModel = CLIENT_BUILDER
                .comment("Changes the Zombie Dwarf model to a standard zombie model.\n Default: false\n Note: This don't change his collision box")
                .define("oldZombieDwarfModel", false);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("Update Checker Settings").push(CATEGORY_UPDATES);
        checkUpdates = COMMON_BUILDER
                .comment(" true = check for updates, false = don't check for updates.\n Default: true.")
                .define("check_updates.updates", true);

        CLIENT_BUILDER.pop();

        CLIENT_CONFIG = CLIENT_BUILDER.build();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }


    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        MoZombiesWave.LOGGER.debug("Loading config file {}", path);

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        MoZombiesWave.LOGGER.debug("Built TOML config for {}", path.toString());
        configData.load();
        MoZombiesWave.LOGGER.debug("Loaded TOML config file {}", path.toString());
        spec.setConfig(configData);
    }



    public static void loadAllSyncConfigs() {
        loadConfig(CLIENT_CONFIG, MoZombiesPlatform.getConfigDirectory().resolve(MoZombiesWave.MODID + "-client.toml"));
        loadConfig(COMMON_CONFIG, MoZombiesPlatform.getConfigDirectory().resolve(MoZombiesWave.MODID + "-common.toml"));
    }

    public static void setupConfig(){
        loadAllSyncConfigs();
        Config.checkUpdates = checkUpdates.get();
        Config.oldDwarfZombieModel = oldDwarfZombieModel.get();
    }

}

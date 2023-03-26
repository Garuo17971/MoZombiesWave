package wily.mozombieswave;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;


public class Config {

    public static boolean oldDwarfZombieModel = false;

    public static boolean checkUpdates = true;

    @ExpectPlatform
    public static void setupPlatformConfig() {
        throw new AssertionError();
    }

}

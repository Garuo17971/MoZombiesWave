package wily.mozombieswave.level;

import com.mojang.serialization.Codec;


import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import wily.mozombieswave.init.Registration;

import java.util.List;

public record OverworldSpawnBiomeModifier(HolderSet<Biome> biomes, List<MobSpawnSettings.SpawnerData> spawners) implements BiomeModifier
{

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD)
        {
            MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
            for(MobSpawnSettings.SpawnerData spawn : spawners) {
                if (!biomes.contains(biome) && !biome.is(BiomeTags.IS_END)) spawns.addSpawn(spawn.type.getCategory(), spawn);
            }
        }
    }

    public Codec<? extends BiomeModifier> codec()
    {
        return Registration.EXAMPLE_CODEC.get();
    }
}
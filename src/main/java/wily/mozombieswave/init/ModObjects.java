package wily.mozombieswave.init;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import wily.mozombieswave.MozombiesWaveMod;
import wily.mozombieswave.entity.ZombieNotch;

import static wily.mozombieswave.MozombiesWaveMod.MODID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModObjects {

    @SubscribeEvent
    public static void onEntityJoinWorld(final EntityJoinLevelEvent event) {
        if(event.getEntity() instanceof Giant giant) {
            giant.goalSelector.addGoal(1, new LookAtPlayerGoal(giant, Player.class, 8.0F));
            giant.goalSelector.addGoal(1, new RandomLookAroundGoal(giant));
            giant.goalSelector.addGoal(2, new MeleeAttackGoal(giant, 1.0D, false));
            giant.goalSelector.addGoal(6, new MoveThroughVillageGoal(giant, 1.0D, true, 4, () -> true));
            giant.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(giant, 1.0D));
            giant.goalSelector.addGoal(1, (new HurtByTargetGoal(giant)).setAlertOthers(ZombifiedPiglin.class));
            giant.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(giant, ZombieNotch.class, true));
            giant.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(giant, Player.class, true));
            giant.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(giant, Villager.class, true));
            giant.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(giant, IronGolem.class, true));
            giant.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(giant, Turtle.class, true));
        }
    }
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(MODID) {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.DISCO_GLASSES.get());
        }
    };
}
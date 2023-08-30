package wily.mozombieswave.client.entity;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.entity.AbstractMoZombie;

public class MoZombieRenderer extends AbstractZombieRenderer<AbstractMoZombie, ZombieModel<AbstractMoZombie>> {
    private final String location;

    public MoZombieRenderer(EntityRenderDispatcher dispatcher, String name) {
        super(dispatcher, new ZombieModel<>(0.0F, false), new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true));
        location = name;
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        return new ResourceLocation(MoZombiesWave.MODID, "textures/entity/" + location + ".png");
    }
}

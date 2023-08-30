package wily.mozombieswave.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.entity.Survivor;

public class SurvivorRenderer extends HumanoidMobRenderer<Survivor, PlayerModel<Survivor>> {
    PlayerModel<Survivor> slimModel;
    private final PlayerModel<Survivor> originalModel;

    public SurvivorRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PlayerModel<>(0,false), 0.5F);
        originalModel = model;
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(0.5F), new HumanoidModel<>(1.0F)));
    }

    @Override
    public void render(Survivor survivor, float p_225623_2_, float p_225623_3_, PoseStack p_225623_4_, MultiBufferSource p_225623_5_, int p_225623_6_) {
        if (slimModel == null) slimModel =new PlayerModel<>(0,true);
        model = survivor.getSurvivorType() > 0 ? slimModel : originalModel;
        super.render(survivor, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
    }

    @Override
    public ResourceLocation getTextureLocation(Survivor entity) {
        return new ResourceLocation(MoZombiesWave.MODID, "textures/entity/" + Survivor.name + "_" + entity.getSurvivorType() + ".png");
    }
}


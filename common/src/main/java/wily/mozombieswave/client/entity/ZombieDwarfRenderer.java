package wily.mozombieswave.client.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import wily.mozombieswave.MoZombiesWave;
import wily.mozombieswave.entity.AbstractMoZombie;
import wily.mozombieswave.entity.ZombieDwarf;

public class ZombieDwarfRenderer extends HumanoidMobRenderer<ZombieDwarf, ZombieDwarfModel>
{
    public final ResourceLocation TEXTURE = new ResourceLocation(MoZombiesWave.MODID, "textures/entity/" + ZombieDwarf.name +".png");

    @Override
    public ResourceLocation getTextureLocation(ZombieDwarf mob) {
        return TEXTURE;
    }
    public ZombieDwarfRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new ZombieDwarfModel(0.0F, false), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new ZombieDwarfModel(0.5F, true), new ZombieDwarfModel(1.0F, true)));
    }

    protected boolean isShaking(AbstractMoZombie p_230495_1_) {
        return p_230495_1_.isUnderWaterConverting();
    }

}

package wily.mozombieswave.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import wily.mozombieswave.entity.ZombieDwarf;

public class ZombieDwarfModel extends HumanoidModel<ZombieDwarf> {

    public boolean isAggressive(ZombieDwarf p_212850_1_) {
        return p_212850_1_.isAggressive();
    }

    public ZombieDwarfModel(float p_i1168_1_, boolean p_i1168_2_) {
        super(p_i1168_1_, 0.0F, 64, p_i1168_2_ ? 32 : 64);
        head.setPos(0.0F, -4.0F + p_i1168_1_, 0.0F);
        head.texOffs(24, 0).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 5.0F, 1.0F, 0.0F, false);
        head.texOffs(24, 0).addBox(-1.0F, 5.0F, -4.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        head.texOffs(24, 0).addBox(2.0F, 0.0F, -4.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        hat = new ModelPart(this);
        hat.setPos(0.0F, -4.0F + p_i1168_1_, 0.0F);
        hat.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.25F, false);

        body = new ModelPart(this, 16,16);
        body.addBox(-4.0F, -5.0F, -2.0F, 8.0F, 10.0F, 4.0F, p_i1168_1_);
        body.setPos(0.0F, -4.0F + p_i1168_1_, 0.0F);

        leftArm = new ModelPart(this);
        leftArm.setPos(5.0F, 2.0F + p_i1168_1_, 0.0F);
        leftArm.texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, true);

        rightArm = new ModelPart(this);
        rightArm.setPos(-5.0F, 2.0F + p_i1168_1_, 0.0F);
        rightArm.texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);

        leftLeg = new ModelPart(this);
        leftLeg.setPos(-1.9F, 8.0F + p_i1168_1_, 0.0F);
        leftLeg.texOffs(0, 16).addBox(1.9F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, true);

        rightLeg = new ModelPart(this);
        rightLeg.setPos(1.9F, 8.0F + p_i1168_1_, 0.0F);
        rightLeg.texOffs(0, 16).addBox(-5.9F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);
    }


    @Override
    public void setupAnim(ZombieDwarf p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        super.setupAnim(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
        if (this.crouching) {
            this.body.xRot = 0.5F;
            this.rightArm.xRot += 0.4F;
            this.leftArm.xRot += 0.4F;
            this.rightLeg.z = 4.0F;
            this.leftLeg.z = 4.0F;
            this.rightLeg.y = 12.2F;
            this.leftLeg.y = 12.2F;
            this.head.y = 8.2F;
            this.body.y = 3.2F;
            this.leftArm.y = 5.2F;
            this.rightArm.y = 5.2F;
        } else {
            this.body.xRot = 0.0F;
            this.rightLeg.z = 0.1F;
            this.leftLeg.z = 0.1F;
            this.rightLeg.y = 14.0F;
            this.leftLeg.y = 14.0F;
            this.head.y = 4F;
            this.body.y = 9.0F;
            this.leftArm.y = 6.0F;
            this.rightArm.y = 6.0F;
        }
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, p_225597_1_.isAggressive(), this.attackTime, p_225597_4_);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        if (!young) {
            head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            hat.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        }
    }


}
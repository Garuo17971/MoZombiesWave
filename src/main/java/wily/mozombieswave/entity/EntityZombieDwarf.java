
package wily.mozombieswave.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

	public class EntityZombieDwarf extends EntityMoZombieBase {
		public EntityZombieDwarf(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 2;
			
			hasCustomLoot = true;
		}
		public static int ID = 15;
		public static String name = "zombie_dwarf";



		@Override
		protected Item getDropItem() {
			return new ItemStack(Items.COAL, (int) (1), 0).getItem();
		}






		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
		}


		// Made with Blockbench 3.7.5
		// Exported for Minecraft version 1.12
		// Paste this class into your mod and generate all required imports
		public static class Modeldwarfzombie extends ModelBase {
			private final ModelRenderer head;
			private final ModelRenderer headwear;
			private final ModelRenderer body;
			private final ModelRenderer left_arm;
			private final ModelRenderer right_arm;
			private final ModelRenderer left_leg;
			private final ModelRenderer right_leg;

			public Modeldwarfzombie() {
				textureWidth = 64;
				textureHeight = 64;
				head = new ModelRenderer(this);
				head.setRotationPoint(0.0F, 0.0F, 0.0F);
				head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F, false));
				head.cubeList.add(new ModelBox(head, 24, 0, -2.0F, 4.0F, -4.0F, 4, 5, 1, 0.0F, false));
				head.cubeList.add(new ModelBox(head, 24, 0, -1.0F, 9.0F, -4.0F, 2, 3, 1, 0.0F, false));
				head.cubeList.add(new ModelBox(head, 24, 0, 2.0F, 4.0F, -4.0F, 1, 3, 1, 0.0F, false));
				headwear = new ModelRenderer(this);
				headwear.setRotationPoint(0.0F, 0.0F, 0.0F);
				headwear.cubeList.add(new ModelBox(headwear, 32, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.25F, false));
				body = new ModelRenderer(this);
				body.setRotationPoint(0.0F, 0.0F, 0.0F);
				body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, 4.0F, -2.0F, 8, 10, 4, 0.0F, false));
				left_arm = new ModelRenderer(this);
				left_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
				left_arm.cubeList.add(new ModelBox(left_arm, 40, 16, 9.0F, 2.0F, -2.0F, 4, 11, 4, 0.0F, true));
				right_arm = new ModelRenderer(this);
				right_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
				right_arm.cubeList.add(new ModelBox(right_arm, 40, 16, -13.0F, 2.0F, -2.0F, 4, 11, 4, 0.0F, false));
				left_leg = new ModelRenderer(this);
				left_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);
				left_leg.cubeList.add(new ModelBox(left_leg, 0, 16, 1.9F, 2.0F, -2.0F, 4, 10, 4, 0.0F, true));
				right_leg = new ModelRenderer(this);
				right_leg.setRotationPoint(1.9F, 12.0F, 0.0F);
				right_leg.cubeList.add(new ModelBox(right_leg, 0, 16, -5.9F, 2.0F, -2.0F, 4, 10, 4, 0.0F, false));
			}

			@Override
			public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
				head.render(f5);
				headwear.render(f5);
				body.render(f5);
				left_arm.render(f5);
				right_arm.render(f5);
				left_leg.render(f5);
				right_leg.render(f5);
			}

			public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
				modelRenderer.rotateAngleX = x;
				modelRenderer.rotateAngleY = y;
				modelRenderer.rotateAngleZ = z;
			}

			public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
				super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
				this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
				this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
				this.headwear.rotateAngleY = f3 / (180F / (float) Math.PI);
				this.headwear.rotateAngleX = f4 / (180F / (float) Math.PI);
				this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
				this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
				this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
				this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			}
		}
	}



package wily.mozombieswave.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.Random;

	public class EntityDiscoZombie extends EntityMoZombieBase {
		public EntityDiscoZombie(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 2;
			
			hasCustomLoot = true;
		}
		public static int ID = 23;
		public static String name = "disco_zombie";

		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityZombieNotch.class, true, true));
		}



		@Override
		protected Item getDropItem() {
			return new ItemStack(Blocks.NOTEBLOCK, (int) (1)).getItem();
		}
		




		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
		}

		public void onLivingUpdate() {
			super.onLivingUpdate();
			int i = (int) this.posX;
			int j = (int) this.posY;
			int k = (int) this.posZ;
			Random random = this.rand;
			if (true)
				for (int l = 0; l < 1; ++l) {
					double d0 = (i + random.nextFloat());
					double d1 = (j + random.nextFloat());
					double d2 = (k + random.nextFloat());
					int i1 = random.nextInt(2) * 2 - 1;
					double d3 = (random.nextFloat() - 0.5D) * 0.25D;
					double d4 = (random.nextFloat() - 0.5D) * 0.25D;
					double d5 = (random.nextFloat() - 0.5D) * 0.25D;
					world.spawnParticle(EnumParticleTypes.NOTE, d0, d1, d2, d3, d4, d5);
				}
		}
	}


package wily.mozombieswave.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


	public class EntityZombieCreeper extends EntityCreeper {
		public EntityZombieCreeper(World world) {
			super(world);
			setSize(0.6f, 1.7f);
			experienceValue = 2;
			
		}
		public static final int ID = 5;
		public static final String name = "zombie_creeper";
		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityZombieNotch.class, true, true));
		}



		@Override
		protected Item getDropItem() {
			return new ItemStack(Blocks.TNT, (int) (1)).getItem();
		}






		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
		}
	}


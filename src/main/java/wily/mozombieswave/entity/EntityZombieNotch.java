
package wily.mozombieswave.entity;


import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


	public  class EntityZombieNotch extends EntityMoZombieBase {
		public EntityZombieNotch(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 19;
			hasCustomLoot = true;
		}
		public static int ID = 1;
		public static String name = "zombie_notch";
		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombieHerobrine.class, true, true));
		}



		@Override
		protected Item getDropItem() {
			return new ItemStack(Items.APPLE, (int) ((Math.random() * 3))).getItem();
		}








		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200D);
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(100D);
		}
	}

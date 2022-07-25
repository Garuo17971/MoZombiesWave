
package wily.mozombieswave.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;


	public class EntityZombieChef extends EntityMoZombieBase {
		public EntityZombieChef(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 2;
			hasCustomLoot = true;
		}
		public static int ID = 25;
		public static String name = "zombie_chef";
		protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
		{
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
		}
		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityPig.class, true, true));
		}



		@Override
		protected Item getDropItem() {
			return new ItemStack(Items.COOKED_PORKCHOP, (int) (1)).getItem();
		}






		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
		}
	}


package wily.mozombieswave.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

	public class EntityZombiePirate extends EntityMoZombieBase {
		public EntityZombiePirate(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 2;
		}
		public static int ID = 7;
		public static String name = "zombie_pirate";
		protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
		{
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
		}


		@Override
		protected Item getDropItem() {
			return new ItemStack(Items.GOLD_NUGGET, (int) (1)).getItem();
		}






		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4D);
		}
	}


package wily.mozombieswave.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

	public class EntityZombieKing extends EntityMoZombieBase {
		public EntityZombieKing(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 2;
		}
		public static int ID = 19;
		public static String name = "zombie_king";



		@Override
		protected Item getDropItem() {
			return new ItemStack(Items.GOLD_NUGGET, (int) (3)).getItem();
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

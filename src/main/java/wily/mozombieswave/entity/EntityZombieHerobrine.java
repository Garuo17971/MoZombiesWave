
package wily.mozombieswave.entity;


import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;


	public class EntityZombieHerobrine extends EntityMoZombieBase {
		public EntityZombieHerobrine(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 19;
			this.isImmuneToFire = true;
			hasCustomLoot = true;
		}
		public static final int ID = 17;
		public static final String name = "zombie_herobrine";
		



		@Override
		protected Item getDropItem() {
			return new ItemStack(Items.SKULL, (int) (1), 3).getItem();
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
		}


		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300D);
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(45D);
		}
	}

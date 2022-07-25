
package wily.mozombieswave.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;


	public class EntityNetherZombie extends EntityMoZombieBase {
		public EntityNetherZombie(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 2;
			this.isImmuneToFire = true;
		}
		public static int ID = 9;
		public static String name = "nether_zombie";



		protected ResourceLocation getLootTable()
		{
			return LootTableList.ENTITIES_ZOMBIE_PIGMAN;
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.zombie.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.zombie_pig.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.ghast.death"));
		}


		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5D);
		}
	}

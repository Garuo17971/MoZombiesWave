
package wily.mozombieswave.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import wily.mozombieswave.MozombiesWaveMod;

import javax.annotation.Nullable;

	public abstract class EntityMoZombieBase extends EntityZombie {
		public EntityMoZombieBase(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 2;
			setNoAI(false);
			hasCustomLoot = false;
		}
		protected boolean hasCustomLoot;
		public static int ID;
		public static String name;
		@Nullable
		protected ResourceLocation getLootTable()
		{
			if (hasCustomLoot) return new ResourceLocation(MozombiesWaveMod.MODID, "entities/" + EntityRegistry.getEntry(this.getClass()).getName());
			return super.getLootTable();
		}

		public String getNameID(){
			return name;
		}
		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombieNotch.class, true, true));
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

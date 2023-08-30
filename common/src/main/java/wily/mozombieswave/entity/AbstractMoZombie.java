
package wily.mozombieswave.entity;


import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public abstract class AbstractMoZombie extends Zombie {

		public AbstractMoZombie(EntityType<? extends Zombie> entityType, Level world) {
			super(entityType,world);

		}
		public static int ID;
		public static String name;

		public String getNameID(){
			return this.name;
		}

		@Override
		public SoundEvent getHurtSound(DamageSource ds) {
			return SoundEvents.ZOMBIE_VILLAGER_HURT;
		}

		@Override
		public SoundEvent getDeathSound() {
			return SoundEvents.HUSK_DEATH;
		}

	@Override
		protected float getSoundVolume() {
			return 1.0F;
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 45.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.ATTACK_DAMAGE, 3.0D).add(Attributes.ARMOR, 2.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE).add(Attributes.MAX_HEALTH,25D);
		}

		protected void addBehaviourGoals() {
			super.addBehaviourGoals();
			specialGoals();

		}
		protected void specialGoals(){
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ZombieNotch.class, true));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Survivor.class, true));

		}

	}

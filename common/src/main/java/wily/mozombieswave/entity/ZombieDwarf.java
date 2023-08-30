
package wily.mozombieswave.entity;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class ZombieDwarf extends AbstractMoZombie {
	public ZombieDwarf(EntityType<? extends AbstractMoZombie> entityType, Level world) {
		super(entityType,world);
	}

	public static int ID = 15;
	public static String name = "zombie_dwarf";

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 60.0D).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 1D).add(Attributes.ARMOR, 2.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE).add(Attributes.MAX_HEALTH,15D);
	}

	@Override
	protected float getStandingEyeHeight(Pose p_34313_, EntityDimensions p_34314_) {
		return this.isBaby() ? 0.80F : 1.51F;
	}


}


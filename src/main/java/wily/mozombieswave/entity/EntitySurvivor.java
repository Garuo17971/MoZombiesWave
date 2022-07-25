
package wily.mozombieswave.entity;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import wily.mozombieswave.MozombiesWaveMod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;


	public class EntitySurvivor extends EntityCreature implements IMob {
		private static final UUID ATTACK_SPEED_BOOST_MODIFIER_UUID = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
		private static final UUID BABY_SPEED_BOOST_ID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
		private static final AttributeModifier BABY_SPEED_BOOST = new AttributeModifier(BABY_SPEED_BOOST_ID, "Baby speed boost", 0.5D, 1);
		private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.<Boolean>createKey(EntitySurvivor.class, DataSerializers.BOOLEAN);
		private static final DataParameter<Integer> GET_TYPE = EntityDataManager.createKey(EntitySurvivor.class, DataSerializers.VARINT);
		private float survivorWidth = -1.0F;
		/** The height of the the entity. */
		private float survivorHeight;
		private int angerLevel;
		private UUID angerTargetUUID;

		public static String name = "survivor";
		public static final int ID = 27;

		public EntitySurvivor(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 2;
			

		}
		@Override
		protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
		{
			super.setEquipmentBasedOnDifficulty(difficulty);
			if (this.rand.nextFloat() < (this.world.getDifficulty() == EnumDifficulty.HARD ? 0.05F : 0.01F))
			{
				int i = this.rand.nextInt(3);

				if (i == 0)
				{
					if (this.rand.nextFloat() < 0.15F)
					this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
					if (this.rand.nextFloat() < 0.3F)
					this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_HELMET));
					this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
				}
				else
				{
					this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
					if (this.rand.nextFloat() < 0.3F)
					this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
					this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
				}
			}
		}
		@Override
		public boolean getCanSpawnHere()
		{
			return true;
		}
		protected void entityInit()
		{
			super.entityInit();
			this.getDataManager().register(IS_CHILD, Boolean.FALSE);
			this.getDataManager().register(GET_TYPE, 0);
		}
		public boolean isChild()
		{
			return this.getDataManager().get(IS_CHILD);
		}
		public int getType()
		{
			return (this.getDataManager().get(GET_TYPE));
		}
		protected int getExperiencePoints(EntityPlayer player)
		{
			if (this.isChild())
			{
				this.experienceValue = (int)((float)this.experienceValue * 2.5F);
			}

			return super.getExperiencePoints(player);
		}
		public void setChild(boolean childSurvivor)
		{
			this.getDataManager().set(IS_CHILD, childSurvivor);

			if (this.world != null && !this.world.isRemote)
			{
				IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
				iattributeinstance.removeModifier(BABY_SPEED_BOOST);

				if (childSurvivor)
				{
					iattributeinstance.applyModifier(BABY_SPEED_BOOST);
				}
			}

			this.setChildSize(childSurvivor);
		}
		public void setType(int Type) {
			this.getDataManager().set(GET_TYPE, Type);
		}
		public void notifyDataManagerChange(DataParameter<?> key)
		{
			if (IS_CHILD.equals(key))
			{
				this.setChildSize(this.isChild());
			}
			if (GET_TYPE.equals(key))
			{
				this.setType(getType());
			}

			super.notifyDataManagerChange(key);
		}
		public void writeEntityToNBT(NBTTagCompound compound)
		{
			super.writeEntityToNBT(compound);

			if (this.isChild())
			{
				compound.setBoolean("IsBaby", true);
			}
			compound.setInteger("Type", getType());

		}

		/**
		 * (abstract) Protected helper method to read subclass entity data from NBT.
		 */
		public void readEntityFromNBT(NBTTagCompound compound)
		{
			super.readEntityFromNBT(compound);

			if (compound.getBoolean("IsBaby"))
			{
				this.setChild(true);
			}
			this.setType(compound.getInteger("Type"));
		}

		protected ItemStack getSkullDrop()
		{
			return new ItemStack(Items.SKULL, 1, 3);
		}

		public void onDeath(DamageSource cause)
		{
			super.onDeath(cause);

			if (cause.getTrueSource() instanceof EntityCreeper)
			{
				EntityCreeper entitycreeper = (EntityCreeper)cause.getTrueSource();

				if (entitycreeper.getPowered() && entitycreeper.ableToCauseSkullDrop())
				{
					entitycreeper.incrementDroppedSkulls();
					ItemStack itemstack = this.getSkullDrop();

					if (!itemstack.isEmpty())
					{
						this.entityDropItem(itemstack, 0.0F);
					}
				}
			}
		}

		public void setChildSize(boolean isChild)
		{
			this.multiplySize(isChild ? 0.5F : 1.0F);
		}
		protected final void setSize(float width, float height)
		{
			boolean flag = this.survivorWidth > 0.0F && this.survivorHeight > 0.0F;
			this.survivorWidth = width;
			this.survivorHeight = height;

			if (!flag)
			{
				this.multiplySize(1.0F);
			}
		}

		/**
		 * Multiplies the height and width by the provided float.
		 */
		protected final void multiplySize(float size)
		{
			super.setSize(this.survivorWidth * size, this.survivorHeight * size);
		}
		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.tasks.addTask(1, new EntityAIWander(this, 1));
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombie.class, true, true));
			this.targetTasks.addTask(18, new EntityAINearestAttackableTarget(this, EntityCow.class, true, true));
			this.tasks.addTask(19, new EntityAIAttackMelee(this, 1.2, true));
			this.targetTasks.addTask(20, new EntityAIHurtByTarget(this, true));
			this.tasks.addTask(3, new EntityAILookIdle(this));
			this.tasks.addTask(3, new EntityAISwimming(this));
			this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
			this.tasks.addTask(5, new EntityAIMoveIndoors(this));
			applyEntityAI();
		}

		@Override
		public EnumCreatureAttribute getCreatureAttribute() {
			return EnumCreatureAttribute.UNDEFINED;
		}

		@Nullable
		protected ResourceLocation getLootTable()
		{
			return new ResourceLocation(MozombiesWaveMod.MODID,"entities/survivor");
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (getType() >= 1 ? SoundEvent.REGISTRY.getObject(new ResourceLocation("mozombies_wave:hurt_survivor_female")) : SoundEvent.REGISTRY.getObject(new ResourceLocation("mozombies_wave:hurt_survivor")));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.death"));
		}

		@Nullable
		@Override
		public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
			super.onInitialSpawn(difficulty, livingdata);
			if (livingdata == null) {
				livingdata = new EntitySurvivor.GroupData(this.world.rand.nextFloat() < net.minecraftforge.common.ForgeModContainer.zombieBabyChance, world.rand.nextFloat() < 0.40D ? 1 : 0);
			}

			if (livingdata instanceof EntitySurvivor.GroupData) {
				EntitySurvivor.GroupData entityzombie$groupdata = (EntitySurvivor.GroupData) livingdata;

				setType((entityzombie$groupdata.getType));
				if (entityzombie$groupdata.isChild) {
					this.setChild(true);

					if ((double) this.world.rand.nextFloat() < 0.05D) {
						List<EntityChicken> list = this.world.<EntityChicken>getEntitiesWithinAABB(EntityChicken.class, this.getEntityBoundingBox().grow(5.0D, 3.0D, 5.0D), EntitySelectors.IS_STANDALONE);

						if (!list.isEmpty()) {
							EntityChicken entitychicken = list.get(0);
							entitychicken.setChickenJockey(true);
							this.startRiding(entitychicken);
						}
					} else if ((double) this.world.rand.nextFloat() < 0.05D) {
						EntityChicken entitychicken1 = new EntityChicken(this.world);
						entitychicken1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
						entitychicken1.onInitialSpawn(difficulty, (IEntityLivingData) null);
						entitychicken1.setChickenJockey(true);
						this.world.spawnEntity(entitychicken1);
						this.startRiding(entitychicken1);
					}
				}

			}
			this.setEquipmentBasedOnDifficulty(difficulty);
			return livingdata;
		}

		protected void updateAITasks()
		{
				
			if (this.angerLevel > 0 && this.angerTargetUUID != null && this.getRevengeTarget() == null)
			{
				EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(this.angerTargetUUID);
				this.setRevengeTarget(entityplayer);
				this.attackingPlayer = entityplayer;
				this.recentlyHit = this.getRevengeTimer();
			}

			super.updateAITasks();
		}

		public void setRevengeTarget(@Nullable EntityLivingBase livingBase)
		{
			super.setRevengeTarget(livingBase);

			if (livingBase != null)
			{
				this.angerTargetUUID = livingBase.getUniqueID();
			}
		}

		static class AIHurtByAggressor extends EntityAIHurtByTarget
		{
			public AIHurtByAggressor(EntitySurvivor p_i45828_1_)
			{
				super(p_i45828_1_, true);
			}

			protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn)
			{
				super.setEntityAttackTarget(creatureIn, entityLivingBaseIn);

				if (creatureIn instanceof EntitySurvivor)
				{
					((EntitySurvivor)creatureIn).becomeAngryAt(entityLivingBaseIn);
				}
			}
		}
		private void becomeAngryAt(Entity p_70835_1_)
		{
			this.angerLevel = 400 + this.rand.nextInt(400);

			if (p_70835_1_ instanceof EntityLivingBase)
			{
				this.setRevengeTarget((EntityLivingBase)p_70835_1_);
			}
		}
		public boolean attackEntityAsMob(Entity entityIn)
		{
			float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
			int i = 0;

			if (entityIn instanceof EntityLivingBase)
			{
				f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
				i += EnchantmentHelper.getKnockbackModifier(this);
			}

			boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

			if (flag)
			{
				if (i > 0 && entityIn instanceof EntityLivingBase)
				{
					((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double) MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
					this.motionX *= 0.6D;
					this.motionZ *= 0.6D;
				}

				int j = EnchantmentHelper.getFireAspectModifier(this);

				if (j > 0)
				{
					entityIn.setFire(j * 4);
				}

				if (entityIn instanceof EntityPlayer)
				{
					EntityPlayer entityplayer = (EntityPlayer)entityIn;
					ItemStack itemstack = this.getHeldItemMainhand();
					ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

					if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
					{
						float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

						if (this.rand.nextFloat() < f1)
						{
							entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
							this.world.setEntityState(entityplayer, (byte)30);
						}
					}
				}

				this.applyEnchantments(this, entityIn);
			}

			return flag;
		}
		public boolean isAngry()
		{
			return this.angerLevel > 0;
		}
		static class AITargetAggressor extends EntityAINearestAttackableTarget<EntityPlayer>
		{
			public AITargetAggressor(EntitySurvivor p_i45829_1_)
			{
				super(p_i45829_1_, EntityPlayer.class, true);
			}

			/**
			 * Returns whether the EntityAIBase should begin execution.
			 */
			public boolean shouldExecute()
			{
				return ((EntitySurvivor)this.taskOwner).isAngry() && super.shouldExecute();
			}
		}
		protected void applyEntityAI()
		{
			this.targetTasks.addTask(1, new EntitySurvivor.AIHurtByAggressor(this));
			this.targetTasks.addTask(2, new EntitySurvivor.AITargetAggressor(this));
		}
		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
			this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3D);
		}
		class GroupData implements IEntityLivingData
		{
			public boolean isChild;
			public int getType;

			private GroupData(boolean p_i47328_2_, int p_i47328_3)
			{
				this.isChild = p_i47328_2_;
				this.getType = p_i47328_3;
			}
		}
		public static  class RenderSurvivor extends RenderBiped<EntitySurvivor> {
			ModelPlayer normal = (ModelPlayer) getMainModel();
			private final ModelPlayer slim = new ModelPlayer(0.0F,true);
			public RenderSurvivor(RenderManager renderManagerIn) {
				super(renderManagerIn, new ModelPlayer(0.0F,false), 0.5F);
				this.addLayer(new LayerBipedArmor(this));
				this.addLayer(new LayerHeldItem(this));
				this.addLayer(new LayerArrow(this));
			}
			protected ResourceLocation getEntityTexture(EntitySurvivor entity) {
				return new ResourceLocation(MozombiesWaveMod.MODID, "textures/" + name + "_" + entity.getType() + ".png");
			}

			@Override
			public void doRender(EntitySurvivor entity, double x, double y, double z, float entityYaw, float partialTicks) {
				if (entity.getType() >=1)
					this.mainModel = slim;
				else this.mainModel = normal;
				super.doRender(entity, x, y, z, entityYaw, partialTicks);
			}
		}
	}


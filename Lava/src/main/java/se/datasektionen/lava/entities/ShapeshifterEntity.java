package se.datasektionen.lava.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RunAroundLikeCrazyGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShapeshifterEntity extends Animal {

	private EatBlockGoal eatBlockGoal;
	private int eatTimer;
	private int copyMob = 1;

	public ShapeshifterEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
		super(p_27557_, p_27558_);
		copyMob = (int) Math.random()*2;
		// TODO Auto-generated constructor stub
	}

	protected void registerGoals() {
		this.eatBlockGoal = new EatBlockGoal(this);
		this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 8.0f));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(2, new FloatGoal(this));
		this.goalSelector.addGoal(3, new PanicGoal(this, 1.4D));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Animal.class, 150, true, false, null));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
	}

	@Override
	public boolean save(CompoundTag tag) {
		return super.save(tag);
	}
	

	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getExperienceReward(Player p_34322_) {
		return 1 + this.random.nextInt(5);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.TURTLE_EGG_CRACK;

	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.GENERIC_DEATH;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_34327_) {
		return SoundEvents.PLAYER_HURT;
	}

	@Override
	protected void customServerAiStep() {
		this.eatTimer = this.eatBlockGoal.getEatAnimationTick();
		super.customServerAiStep();
	}

	@Override
	public void aiStep() {
		if (this.level.isClientSide) {
			this.eatTimer = Math.max(0, this.eatTimer - 1);
		}

		super.aiStep();
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte p_29814_) {
		if (p_29814_ == 10) {
			this.eatTimer = 40;
		} else {
			super.handleEntityEvent(p_29814_);
		}

	}
	

	public int getCopyMob() {
		return copyMob;
	}

	public void setCopyMob(int copyMob) {
		this.copyMob = copyMob;
	}

	public static AttributeSupplier.Builder prepareAttributes() {
		return LivingEntity.createLivingAttributes().add(Attributes.ATTACK_DAMAGE, 3.0).add(Attributes.MAX_HEALTH, 7.0)
				.add(Attributes.FOLLOW_RANGE, 40.0).add(Attributes.ATTACK_KNOCKBACK, 0.1)
				.add(Attributes.MOVEMENT_SPEED, 0.5);
	}

}

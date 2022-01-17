package baguchan.frostrealm.entity;

import baguchan.frostrealm.api.animation.Animation;
import baguchan.frostrealm.api.animation.IAnimatable;
import baguchan.frostrealm.registry.FrostBlocks;
import baguchan.frostrealm.registry.FrostEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;
import java.util.UUID;

public class FrostWolf extends Wolf implements IAnimatable {
	private static final EntityDataAccessor<Integer> ANIMATION_ID = SynchedEntityData.defineId(FrostWolf.class, EntityDataSerializers.INT);

	public static final Animation HOWL_ANIMATION = Animation.create(60);

	private int animationTick;

	public FrostWolf(EntityType<? extends Wolf> p_30369_, Level p_30370_) {
		super(p_30369_, p_30370_);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ANIMATION_ID, -1);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	public static boolean checkFrostWolfSpawnRules(EntityType<? extends Animal> p_27578_, LevelAccessor p_27579_, MobSpawnType p_27580_, BlockPos p_27581_, Random p_27582_) {
		return p_27579_.getBlockState(p_27581_.below()).is(FrostBlocks.FROZEN_GRASS_BLOCK) && p_27579_.getRawBrightness(p_27581_, 0) > 8;
	}

	@Override
	public FrostWolf getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
		FrostWolf wolf = FrostEntities.FROST_WOLF.create(p_149088_);
		UUID uuid = this.getOwnerUUID();
		if (uuid != null) {
			wolf.setOwnerUUID(uuid);
			wolf.setTame(true);
		}

		return wolf;
	}

	public float getTailAngle() {
		if (this.isAngry()) {
			return 1.5393804F;
		} else {
			return this.isTame() ? (0.55F - (this.getMaxHealth() - this.getHealth()) * 0.015F) * (float) Math.PI : ((float) Math.PI / 5F);
		}
	}

	public void setTame(boolean p_30443_) {
		super.setTame(p_30443_);
		if (p_30443_) {
			this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(24.0D);
			this.setHealth(this.getMaxHealth());
		} else {
			this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0D);
		}

		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(5.0D);
	}

	@Override
	public void tick() {
		super.tick();
		updateAnimations(this);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.level.isNight() && this.level.canSeeSky(this.blockPosition()) && this.getAnimation() != HOWL_ANIMATION && this.random.nextInt(250) == 0) {
			this.setAnimation(HOWL_ANIMATION);
			this.playSound(SoundEvents.WOLF_HOWL, 3.0F, this.getVoicePitch());
		}
	}

	@Override
	public int getAnimationTick() {
		return animationTick;
	}

	@Override
	public void setAnimationTick(int tick) {
		this.animationTick = tick;
	}

	@Override
	public Animation getAnimation() {
		int index = this.entityData.get(ANIMATION_ID);
		if (index < 0) {
			return NO_ANIMATION;
		} else {
			return this.getAnimations()[index];
		}
	}

	@Override
	public Animation[] getAnimations() {
		return new Animation[]{
				HOWL_ANIMATION
		};
	}

	@Override
	public void setAnimation(Animation animation) {
		this.entityData.set(ANIMATION_ID, ArrayUtils.indexOf(this.getAnimations(), animation));
	}
}

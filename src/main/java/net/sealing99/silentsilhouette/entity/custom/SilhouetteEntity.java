package net.sealing99.silentsilhouette.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sealing99.silentsilhouette.TheSilentSilhouette;
import net.sealing99.silentsilhouette.item.ModItems;

public class SilhouetteEntity extends PathAwareEntity {
    private static final TrackedData<Boolean> CRUCIFIED = DataTracker.registerData(SilhouetteEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> CRUCIFICATION_TIMER = DataTracker.registerData(SilhouetteEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private final MinecraftServer server;

    public SilhouetteEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);

        this.server = world.getServer();

        this.addStatusEffect(new StatusEffectInstance(
                StatusEffects.GLOWING,
                StatusEffectInstance.INFINITE,
                255,
                false,
                true
        ));

        TheSilentSilhouette.LOGGER.info("created entity");
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(CRUCIFIED, false);
        builder.add(CRUCIFICATION_TIMER, 0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 16.0f));
        this.goalSelector.add(2, new SilhouetteAttackGoal(this, 1.0d, false));
        //this.goalSelector.add(3, new FollowMobGoal(this, 1.0f, 10.0f, 20.0f));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0d));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));;
    }

    public boolean isCrucified() {
        return this.dataTracker.get(CRUCIFIED);
    }

    public void setCrucified(boolean crucified) {
        this.dataTracker.set(CRUCIFIED, crucified);
    }

    public int getCrucificationTimeout() {
        return this.dataTracker.get(CRUCIFICATION_TIMER);
    }

    public void setCrucificationTimeout(int timeout) {
        this.dataTracker.set(CRUCIFICATION_TIMER, timeout);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 14)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient() && player.getMainHandStack().isOf(ModItems.PINK_GARNET_CRUCIFIX)) {
            if (this.getCrucificationTimeout() == 0) {
                this.setCrucified(!this.isCrucified());
                TheSilentSilhouette.LOGGER.info("Silhouette crucified state toggled: " + this.isCrucified());
                this.setCrucificationTimeout(10);
                TheSilentSilhouette.LOGGER.info("Silhouette crucification timeout set to: " + this.getCrucificationTimeout());

                this.server.getPlayerManager().broadcast(
                        Text.literal("<The Silent Silhouette> greetings :) , i am now " + this.isCrucified()),
                        false // set to true to show in the action bar instead of chat
                );
            }

            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }


    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }

        if (this.age % 100 == 0 && this.getHealth() < this.getMaxHealth() && !this.isCrucified()) {
            this.setHealth(100.0f);
            TheSilentSilhouette.LOGGER.info("Silhouette healed to full health!");
            TheSilentSilhouette.LOGGER.info("creaadsf: " + this.isCrucified() + " and crucification timeout: " + this.getCrucificationTimeout());

        }

        if (this.getCrucificationTimeout() > 0) {
            this.setCrucificationTimeout(this.getCrucificationTimeout() - 1);
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putBoolean("Crucified", this.isCrucified());
        nbt.putInt("CrucificationTimeout", this.getCrucificationTimeout());
        return nbt;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.setCrucified(nbt.getBoolean("Crucified"));
        this.setCrucificationTimeout(nbt.getInt("CrucificationTimeout"));
    }
}

class SilhouetteAttackGoal extends MeleeAttackGoal {
    private final SilhouetteEntity entity;
    private int ticks;

    public SilhouetteAttackGoal(SilhouetteEntity silhouette, double speed, boolean pauseWhenIdle) {
        super(silhouette, speed, pauseWhenIdle);
        this.entity = silhouette;
    }

    @Override
    public void start() {
        super.start();
        this.ticks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.entity.setAttacking(false);
    }

    @Override
    public void tick() {
        super.tick();
        this.ticks++;
        if (this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
            this.entity.setAttacking(true);
        } else {
            this.entity.setAttacking(false);
        }
    }

    @Override
    protected void attack(LivingEntity target) {
        if (this.canAttack(target) && this.entity.isAttacking()) {
            // Check custom attack timer here if needed
            this.entity.tryAttack(target);
            // Play attack sound
            this.entity.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1.0F, 1.0F);
        }
    }
}

package net.sealing99.silentsilhouette.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sealing99.silentsilhouette.TheSilentSilhouette;
import org.jetbrains.annotations.Nullable;

public class SilhouetteEntity extends PathAwareEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private boolean isCrucified = false;
    private int crucificationTimeout = 0;

    public SilhouetteEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 16.0f));
        this.goalSelector.add(2, new SilhouetteAttackGoal(this, 1.0d, false));
        //this.goalSelector.add(3, new FollowMobGoal(this, 1.0f, 10.0f, 20.0f));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0d));
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
        if (!this.getWorld().isClient()) {
            if (this.crucificationTimeout == 0) {
                this.isCrucified = !this.isCrucified;
                TheSilentSilhouette.LOGGER.info("Silhouette crucified state toggled: " + this.isCrucified);
                this.crucificationTimeout = 10;
                TheSilentSilhouette.LOGGER.info("Silhouette crucification timeout set to: " + this.crucificationTimeout);
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

            if (this.age % 2 == 0 && this.getHealth() < this.getMaxHealth() && !this.isCrucified) {
                this.setHealth(100.0f);
                TheSilentSilhouette.LOGGER.info("Silhouette healed to full health!");
            }

            if (this.crucificationTimeout > 0) {
                this.crucificationTimeout = this.crucificationTimeout - 1;
            }
            TheSilentSilhouette.LOGGER.info("Silhouette crucification timeout: " + this.crucificationTimeout);
        }
    }
}

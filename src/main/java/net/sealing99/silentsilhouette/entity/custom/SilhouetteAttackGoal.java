package net.sealing99.silentsilhouette.entity.custom;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class SilhouetteAttackGoal extends MeleeAttackGoal {
    private final SilhouetteEntity entity;
    private int ticks;

    public SilhouetteAttackGoal(SilhouetteEntity silhouette, double speed, boolean pauseWhenMobIdle) {
        super(silhouette, speed, pauseWhenMobIdle);
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
}
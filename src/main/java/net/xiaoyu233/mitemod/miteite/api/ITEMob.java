package net.xiaoyu233.mitemod.miteite.api;

public interface ITEMob {

    float getChanceOfCausingFire();

    default boolean ite$hasBurningAttack() {
        throw new IllegalStateException("Should be implemented in Mixin");
    }

    default void ite$setBurningAttack(boolean burningAttack) {
        throw new IllegalStateException("Should be implemented in Mixin");
    }
}

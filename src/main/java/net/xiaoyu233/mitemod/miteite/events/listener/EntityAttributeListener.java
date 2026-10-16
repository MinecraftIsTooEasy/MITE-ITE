package net.xiaoyu233.mitemod.miteite.events.listener;

import moddedmite.rustedironcore.api.event.listener.IEntityAttributeListener;
import net.minecraft.AttributeInstance;
import net.minecraft.EntityAncientBoneLord;
import net.minecraft.EntityArachnid;
import net.minecraft.EntityBlaze;
import net.minecraft.EntityBoneLord;
import net.minecraft.EntityCaveSpider;
import net.minecraft.EntityCreeper;
import net.minecraft.EntityDemonSpider;
import net.minecraft.EntityDragon;
import net.minecraft.EntityEarthElemental;
import net.minecraft.EntityEnderman;
import net.minecraft.EntityFireElemental;
import net.minecraft.EntityGhast;
import net.minecraft.EntityGhoul;
import net.minecraft.EntityGiantZombie;
import net.minecraft.EntityInvisibleStalker;
import net.minecraft.EntityIronGolem;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityLongdead;
import net.minecraft.EntityMagmaCube;
import net.minecraft.EntityPigZombie;
import net.minecraft.EntityRevenant;
import net.minecraft.EntityShadow;
import net.minecraft.EntitySilverfish;
import net.minecraft.EntitySkeleton;
import net.minecraft.EntitySpider;
import net.minecraft.EntityWight;
import net.minecraft.EntityWitch;
import net.minecraft.EntityWither;
import net.minecraft.EntityWoodSpider;
import net.minecraft.EntityZombie;
import net.minecraft.SharedMonsterAttributes;
import net.xiaoyu233.fml.reload.transform.util.WorldAccessor;
import net.xiaoyu233.mitemod.miteite.util.Configs;

public class EntityAttributeListener implements IEntityAttributeListener {
    @Override
    public void onEntityAttributeRegister(EntityLivingBase entity) {
        int day = Math.min(Configs.Entities.ENHANCE_LIMIT.get(), entity.getWorld() != null ? entity.getWorld().getDayOfOverworld() : 0);
        boolean tweak = Configs.Entities.BONE_LORD_TWEAK.get();

        if (entity instanceof EntityAncientBoneLord) {
            followRange(entity, 48.0);
            movementSpeed(entity, 0.3);
            attackDamage(entity, tweak ? 13.0 + day / 20.0 : 8.0);
            maxHealth(entity, tweak ? 40.0 + day / 12.0 : 25.0);
        } else if (entity instanceof EntityBoneLord) {
            followRange(entity, 44.0);
            movementSpeed(entity, 0.27);
            attackDamage(entity, tweak ? 10.0 + day / 20.0 : 3.5);
            maxHealth(entity, tweak ? 35.0 + day / 16.0 : 22.0);
        } else if (entity instanceof EntityLongdead) {
            boolean guardian = ((EntityLongdead) entity).isGuardian();
            followRange(entity, 40.0);
            movementSpeed(entity, 0.29);
            attackDamage(entity, (guardian ? 10.0 : 8.0) + day / 20.0);
            maxHealth(entity, (guardian ? 26.0 : 15.0) + day / 16.0);
        } else if (entity instanceof EntitySkeleton) {
            followRange(entity, 64.0);
            if (((EntitySkeleton) entity).getSkeletonType() == 1) {
                maxHealth(entity, 45.0 + day / 16.0);
                movementSpeed(entity, 0.25);
                attackDamage(entity, 13.0 + day / 24.0);
            } else {
                maxHealth(entity, 15.0 + day / 14.0);
                movementSpeed(entity, 0.3);
                attackDamage(entity, 8.0 + day / 24.0);
            }
        } else if (entity instanceof EntityPigZombie) {
            maxHealth(entity, 40.0 + day / 16.0);
            attackDamage(entity, 12.0 + day / 20.0);
            movementSpeed(entity, 0.25);
            followRange(entity, 64.0);
            spawnReinforcements(entity);
        } else if (entity instanceof EntityRevenant) {
            maxHealth(entity, 35.0 + day / 16.0);
            attackDamage(entity, 12.0 + day / 24.0);
            movementSpeed(entity, 0.28);
            followRange(entity, 64.0);
            spawnReinforcements(entity);
        } else if (entity instanceof EntityZombie) {
            maxHealth(entity, 30.0 + day / 8.0);
            attackDamage(entity, 5.0);
            movementSpeed(entity, 0.23);
            followRange(entity, 40.0);
            spawnReinforcements(entity);
        } else if (entity instanceof EntityGhoul) {
            maxHealth(entity, 40.0);
            attackDamage(entity, 5.0);
            movementSpeed(entity, 0.28);
            followRange(entity, 40.0);
        } else if (entity instanceof EntityEarthElemental) {
            followRange(entity, 20.0);
            movementSpeed(entity, 0.2);
            attackDamage(entity, 12.0);
            maxHealth(entity, entity.getWorld().isTheNether() ? 60.0 : 30.0);
        } else if (entity instanceof EntitySpider) {
            maxHealth(entity, 15.0 + day / 24.0);
            attackDamage(entity, 6.0 + day / 24.0);
            movementSpeed(entity, 1.0);
            followRange(entity, 64.0);
        } else if (entity instanceof EntityCaveSpider) {
            maxHealth(entity, 16.0);
            attackDamage(entity, 6.0);
            movementSpeed(entity, 1.0);
            followRange(entity, 28.0);
        } else if (entity instanceof EntityDemonSpider) {
            maxHealth(entity, 18.0);
            attackDamage(entity, 5.0);
            movementSpeed(entity, 1.0);
            followRange(entity, 28.0);
        } else if (entity instanceof EntityWoodSpider) {
            maxHealth(entity, 6.0);
            attackDamage(entity, 1.0);
            movementSpeed(entity, 0.8);
            followRange(entity, 28.0);
        } else if (entity instanceof EntityArachnid) {
            maxHealth(entity, 10.0);
            attackDamage(entity, 6.0);
            movementSpeed(entity, 1.0);
            followRange(entity, 28.0);
        } else if (entity instanceof EntityBlaze) {
            maxHealth(entity, 40.0);
            attackDamage(entity, 6.0);
            movementSpeed(entity, 0.7);
            followRange(entity, 32.0);
        } else if (entity instanceof EntityCreeper) {
            maxHealth(entity, 20.0 + day / 8.0);
            attackDamage(entity, 4.0);
            movementSpeed(entity, 0.25);
            followRange(entity, 64.0);
        } else if (entity instanceof EntityEnderman) {
            maxHealth(entity, 60.0 + day / 20.0);
            attackDamage(entity, 15.0 + day / 20.0);
            movementSpeed(entity, 0.3);
            followRange(entity, 32.0);
        } else if (entity instanceof EntityFireElemental) {
            followRange(entity, 40.0);
            maxHealth(entity, ((WorldAccessor) entity.getWorld()).getProvider().isHellWorld ? 70.0 : 40.0);
            movementSpeed(entity, 0.25);
            attackDamage(entity, 5.0);
        } else if (entity instanceof EntityGiantZombie) {
            maxHealth(entity, 100.0 + day / 16.0);
            attackDamage(entity, 25.0 + day / 16.0);
            movementSpeed(entity, 0.3);
            followRange(entity, 32.0);
        } else if (entity instanceof EntityInvisibleStalker) {
            maxHealth(entity, 20.0 + day / 16.0);
            attackDamage(entity, 6.0 + day / 16.0);
            movementSpeed(entity, 0.23);
            followRange(entity, 40.0);
        } else if (entity instanceof EntityShadow) {
            maxHealth(entity, 40.0);
            attackDamage(entity, 5.0);
            movementSpeed(entity, 0.23);
            followRange(entity, 40.0);
        } else if (entity instanceof EntitySilverfish) {
            maxHealth(entity, 8.0);
            attackDamage(entity, 3.0);
            movementSpeed(entity, 0.6);
            followRange(entity, 32.0);
        } else if (entity instanceof EntityWight) {
            maxHealth(entity, 40.0);
            attackDamage(entity, 5.0);
            movementSpeed(entity, 0.25);
            followRange(entity, 40.0);
        } else if (entity instanceof EntityWitch) {
            maxHealth(entity, 26.0);
            attackDamage(entity, 4.0);
            movementSpeed(entity, 0.25);
            followRange(entity, 32.0);
        } else if (entity instanceof EntityWither) {
            maxHealth(entity, 300.0);
            attackDamage(entity, 4.0);
            movementSpeed(entity, 0.6);
            followRange(entity, 40.0);
        } else if (entity instanceof EntityGhast) {
            maxHealth(entity, 15.0);
        } else if (entity instanceof EntityDragon) {
            maxHealth(entity, 1250.0);
        } else if (entity instanceof EntityIronGolem) {
            maxHealth(entity, 200.0 + day / 26.0);
            movementSpeed(entity, 0.25);
            attackDamage(entity, 0.0);
        } else if (entity instanceof EntityMagmaCube) {
            movementSpeed(entity, 0.2);
            maxHealth(entity, entity.getMaxHealth() + 30.0);
        }
    }

    private static void maxHealth(EntityLivingBase entity, double value) {
        entity.setEntityAttribute(SharedMonsterAttributes.maxHealth, value);
    }

    private static void attackDamage(EntityLivingBase entity, double value) {
        entity.setEntityAttribute(SharedMonsterAttributes.attackDamage, value);
    }

    private static void movementSpeed(EntityLivingBase entity, double value) {
        entity.setEntityAttribute(SharedMonsterAttributes.movementSpeed, value);
    }

    private static void followRange(EntityLivingBase entity, double value) {
        entity.setEntityAttribute(SharedMonsterAttributes.followRange, value);
    }

    private static void spawnReinforcements(EntityLivingBase entity) {
        AttributeInstance instance = entity.getAttributeMap().getAttributeInstanceByName("zombie.spawnReinforcements");
        if (instance != null) {
            instance.setAttribute(entity.getRNG().nextDouble() * 0.1);
        }
    }
}

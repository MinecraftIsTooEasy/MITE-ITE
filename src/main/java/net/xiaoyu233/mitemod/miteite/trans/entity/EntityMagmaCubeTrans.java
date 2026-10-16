package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityMagmaCube.class)
public abstract class EntityMagmaCubeTrans extends EntityCubic {
    public EntityMagmaCubeTrans(World par1World) {
        super(par1World);
    }

    @Override
    public EntityDamageResult attackEntityAsMob(Entity target) {
        if (target != null && target.isEntityAlive() && rand.nextFloat() < Configs.Entities.NETHER_MOD_ATTACK_FIRE_CHANCE.get()){
            target.setFire(100);
        }
        return super.attackEntityAsMob(target);
    }
}

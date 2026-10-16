package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityEnderman.class)
public class EntityEndermanTrans extends EntityMob {
   public EntityEndermanTrans(World par1World) {
      super(par1World);
   }

   public EntityDamageResult attackEntityAsMob(Entity target) {
      this.worldObj.setEntityState(this, EnumEntityState.golem_throw);
      EntityDamageResult result = target.attackEntityFrom(new Damage(DamageSource.causeMobDamage(this), (float)this.getEntityAttributeValue(SharedMonsterAttributes.attackDamage)));
      if (result != null && result.entityWasKnockedBack()) {
         target.motionY += 0.6;
      }

      return result;
   }
}
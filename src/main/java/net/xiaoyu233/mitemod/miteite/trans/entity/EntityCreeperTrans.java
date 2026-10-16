package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.EntityCreeper;
import net.minecraft.EntityMob;
import net.minecraft.World;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityCreeper.class)
public class EntityCreeperTrans extends EntityMob {
   @Shadow protected float explosionRadius;
   @Shadow private int fuseTime;

   public EntityCreeperTrans(World par1World) {
      super(par1World);
   }

   @Inject(method = "<init>",at = @At("RETURN"))
   private void injectInit(CallbackInfo callbackInfo){
      int day = Math.min(Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
      this.explosionRadius = 1.25f;
      this.setExplosionTime(Math.max(this.getExplosionTime() * 3 - (int)((double)day * 0.6D), 40));
   }

   @Unique
   public int getExplosionTime() {
      return this.fuseTime;
   }

   @Unique
   public void setExplosionTime(int br) {
      this.fuseTime = br;
   }
}

package net.xiaoyu233.mitemod.miteite.trans.block.tileentity;

import net.minecraft.*;
import net.xiaoyu233.fml.util.Utils;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.xiaoyu233.mitemod.miteite.util.MobSpawnerGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin({MobSpawnerBaseLogic.class})
public abstract class MobSpawnerTrans {
   @Shadow public double field_98284_d;
   @Shadow public double field_98287_c;
   @Shadow public int spawnDelay;
   @Shadow private int maxNearbyEntities;
   @Shadow private int spawnCount;
   @Shadow private int spawnRange;
   @Shadow public abstract boolean canRun();
   @Shadow public abstract Entity func_98265_a(Entity var1);
   @Shadow private void func_98273_j() {}
   @Shadow public abstract String getEntityNameToSpawn();
   @Shadow public abstract World getSpawnerWorld();
   @Shadow public abstract int getSpawnerX();
   @Shadow public abstract int getSpawnerY();
   @Shadow public abstract int getSpawnerZ();

//   @Unique private static final Class<?> MOB_SPAWNER = Utils.safeMake(new MobSpawnerGetter(), MobSpawnerBaseLogic.class);

//   /**
//    * @author
//    * @reason
//    */
//   @Overwrite
//   public void updateSpawner() {
//      if (this.canRun()) {
//         boolean playerBoost = (Configs.Block.MOB_SPAWNER_SPEED_UP_WITH_PLAYERS.get()) && !this.getSpawnerWorld().getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(this.getSpawnerX(), this.getSpawnerY(), this.getSpawnerZ(), this.getSpawnerX() + 1, this.getSpawnerY() + 1, this.getSpawnerZ() + 1).expand(this.spawnRange * 2, this.spawnRange, this.spawnRange * 2)).isEmpty();
//         if (this.getSpawnerWorld().isRemote) {
//            double var1 = (float)this.getSpawnerX() + this.getSpawnerWorld().rand.nextFloat();
//            double var3 = (float)this.getSpawnerY() + this.getSpawnerWorld().rand.nextFloat();
//            double var5 = (float)this.getSpawnerZ() + this.getSpawnerWorld().rand.nextFloat();
//            this.getSpawnerWorld().spawnParticle(EnumParticle.smoke, var1, var3, var5, 0.0, 0.0, 0.0);
//            this.getSpawnerWorld().spawnParticle(EnumParticle.flame, var1, var3, var5, 0.0, 0.0, 0.0);
//            if (this.spawnDelay > 0) {
//               if (playerBoost) {
//                  this.spawnDelay -= 2;
//               }
//
//               --this.spawnDelay;
//            }
//
//            this.field_98284_d = this.field_98287_c;
//            this.field_98287_c = (this.field_98287_c + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0;
//         } else {
//            if (this.spawnDelay <= -1) {
//               this.func_98273_j();
//            }
//
//            if (this.spawnDelay > 0) {
//               if (playerBoost) {
//                  this.spawnDelay -= 2;
//               }
//
//               --this.spawnDelay;
//               return;
//            }
//
//            boolean var12 = false;
//
//            for(int var2 = 0; var2 < this.spawnCount; ++var2) {
//               Entity var13 = EntityList.createEntityByName(this.getEntityNameToSpawn(), this.getSpawnerWorld());
//               if (var13 == null) {
//                  return;
//               }
//
//               int var4 = this.getSpawnerWorld().getEntitiesWithinAABB(var13.getClass(), AxisAlignedBB.getAABBPool().getAABB(this.getSpawnerX(), this.getSpawnerY(), this.getSpawnerZ(), this.getSpawnerX() + 1, this.getSpawnerY() + 1, this.getSpawnerZ() + 1).expand(this.spawnRange * 2, 4.0, this.spawnRange * 2)).size();
//               if (var4 >= this.maxNearbyEntities) {
//                  this.func_98273_j();
//                  return;
//               }
//
//               double var5 = (double)this.getSpawnerX() + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double)this.spawnRange;
//               double var7 = this.getSpawnerY() + this.getSpawnerWorld().rand.nextInt(3) - 1;
//               double var9 = (double)this.getSpawnerZ() + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double)this.spawnRange;
//               EntityLiving var11 = var13 instanceof EntityLiving ? (EntityLiving)var13 : null;
//               var13.setLocationAndAngles(var5, var7, var9, this.getSpawnerWorld().rand.nextFloat() * 360.0F, 0.0F);
//               if (var11 != null && MOB_SPAWNER.isAssignableFrom(this.getClass())) {
//                  var11.setSpawnBlock(this.getSpawnerX(), this.getSpawnerY(), this.getSpawnerZ());
//               }
//
//               if (var11 == null || var11.getCanSpawnHere(true)) {
//                  this.func_98265_a(var13);
//                  this.getSpawnerWorld().playAuxSFX(2004, this.getSpawnerX(), this.getSpawnerY(), this.getSpawnerZ(), 0);
//                  if (var11 != null) {
//                     var11.spawnExplosionParticle();
//                  }
//
//                  var12 = true;
//               }
//            }
//
//            if (var12) {
//               this.func_98273_j();
//            } else {
//               this.spawnDelay += 10;
//            }
//         }
//      }
//
//   }

   @Inject(method = "updateSpawner", at = @At(value = "FIELD", target = "Lnet/minecraft/MobSpawnerBaseLogic;spawnDelay:I", ordinal = 1))
   private void playerBoost(CallbackInfo ci) {
      boolean playerBoost = (Configs.Block.MOB_SPAWNER_SPEED_UP_WITH_PLAYERS.get()) && !this.getSpawnerWorld().getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(this.getSpawnerX(), this.getSpawnerY(), this.getSpawnerZ(), this.getSpawnerX() + 1, this.getSpawnerY() + 1, this.getSpawnerZ() + 1).expand(this.spawnRange * 2, this.spawnRange, this.spawnRange * 2)).isEmpty();
      if (playerBoost) {
         this.spawnDelay -= 2;
      }
   }

   @Inject(method = "updateSpawner", at = @At(value = "FIELD", target = "Lnet/minecraft/MobSpawnerBaseLogic;spawnDelay:I", ordinal = 6))
   private void playerBoost_1(CallbackInfo ci) {
      boolean playerBoost = (Configs.Block.MOB_SPAWNER_SPEED_UP_WITH_PLAYERS.get()) && !this.getSpawnerWorld().getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(this.getSpawnerX(), this.getSpawnerY(), this.getSpawnerZ(), this.getSpawnerX() + 1, this.getSpawnerY() + 1, this.getSpawnerZ() + 1).expand(this.spawnRange * 2, this.spawnRange, this.spawnRange * 2)).isEmpty();
      if (playerBoost) {
         this.spawnDelay -= 2;
      }
   }

}

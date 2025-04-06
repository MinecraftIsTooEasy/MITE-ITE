package net.xiaoyu233.mitemod.miteite.trans.world;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldServer.class)
public abstract class WorldServerTrans extends World {
   @Unique private boolean pushTimeNextTick;

   public WorldServerTrans(ISaveHandler par1ISaveHandler, String par2Str, WorldProvider par3WorldProvider, WorldSettings par4WorldSettings, Profiler par5Profiler, ILogAgent par6ILogAgent, long world_creation_time, long total_world_time) {
      super(par1ISaveHandler, par2Str, par3WorldProvider, par4WorldSettings, par5Profiler, par6ILogAgent, world_creation_time, total_world_time);
   }

   @WrapWithCondition(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/WorldServer;advanceTotalWorldTime(J)V"))
   private boolean wrapAdvanceWorldTime(WorldServer instance, long l){
      if (Configs.GameMechanics.FIRST_DAY_LONGER_DAY_TIME.get() && this.getDayOfOverworld() == 1 && this.getTotalWorldTime() < 12000L) {
         if (this.pushTimeNextTick) {
            this.pushTimeNextTick = false;
            return true;
         } else {
            this.pushTimeNextTick = true;
            return false;
         }
      }
      return true;
   }

}

package net.xiaoyu233.mitemod.miteite.trans.world;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.entity.EntityAnnihilationSkeleton;
import net.xiaoyu233.mitemod.miteite.entity.EntityWanderingWitch;
import net.xiaoyu233.mitemod.miteite.entity.EntityZombieLord;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeGenUnderworld.class)
public class BiomeGenUnderworldTrans extends BiomeGenBase {
   protected BiomeGenUnderworldTrans(int par1) {
      super(par1);
   }

   @Inject(method = "<init>", at = @At("RETURN"))
   public void injectCtor(CallbackInfo callbackInfo) {
      this.spawnableMonsterList.add(new SpawnListEntry(EntityZombieLord.class, Configs.GameMechanics.MobSpawning.ZOMBIE_LORD_SPWAN_FREQUENCY.get(), 1, 1));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityAnnihilationSkeleton.class, Configs.GameMechanics.MobSpawning.ANNIHILATION_SKELETON_SPWAN_FREQUENCY.get(), 1, 1));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityWanderingWitch.class, Configs.GameMechanics.MobSpawning.WANDERING_WITCH_SPAWN_FREQUENCY.get(), 1, 1));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityHellhound.class, 15, 1, 2));
   }
}

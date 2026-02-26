package net.xiaoyu233.mitemod.miteite.trans.block;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEPortal;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BlockPortal.class)
public abstract class BlockPortalTrans extends Block implements ITEPortal {

   @Shadow protected abstract int getFrameMinY(World world, int x, int y, int z);

   @Shadow protected abstract int getFrameMinX(World world, int x, int y, int z);

   @Shadow protected abstract int getFrameMaxX(World world, int x, int y, int z);

   @Shadow protected abstract int getFrameMinZ(World world, int x, int y, int z);

   @Shadow protected abstract int getFrameMaxZ(World world, int x, int y, int z);
   
   @Shadow public abstract BlockRunestone getRunegateType(World world, int x, int y, int z);
   
   @Shadow protected abstract int getRunegateSeed(World world, int x, int y, int z);
   
   @Unique private BlockRunestone mITE_ITEPlugin$runestoneOverride;
   @Unique private int mITE_ITEPlugin$portalSeedOverride;

   protected BlockPortalTrans(int par1, Material par2Material, BlockConstants constants) {
      super(par1, par2Material, constants);
   }
   
   @Redirect(method = "getRunegateDestinationCoords", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockPortal;getRunegateType(Lnet/minecraft/World;III)Lnet/minecraft/BlockRunestone;"))
   private BlockRunestone redirectOverrideRuneType(BlockPortal instance, World world, int x, int y, int z){
      if (this.mITE_ITEPlugin$runestoneOverride != null){
         BlockRunestone cached = this.mITE_ITEPlugin$runestoneOverride;
         this.mITE_ITEPlugin$runestoneOverride = null;
         return cached;
      }else {
         return getRunegateType(world,x,y,z);
      }
   }
   
   @Redirect(method = "getRunegateDestinationCoords", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockPortal;getRunegateSeed(Lnet/minecraft/World;III)I"))
   private int redirectOverrideRuneSeed(BlockPortal instance, World world, int x, int y, int z){
      if (this.mITE_ITEPlugin$portalSeedOverride != 0){
         int cached = this.mITE_ITEPlugin$portalSeedOverride;
         this.mITE_ITEPlugin$portalSeedOverride = 0;
         return cached;
      }else {
         return getRunegateSeed(world,x,y,z);
      }
   }

   @ModifyReturnValue(method = "getRunegateDestinationCoords", at = @At("TAIL"))
   private int[] modifyUnderworldRunegateDestinationMinY(int[] original, @Local(argsOnly = true) WorldServer world, @Local(name = "x") int x, @Local(name = "z") int z) {
      if (world.isUnderworld()) {
         int minY = Configs.WorldGen.Underworld.UNDERWORLD_MANTLE_BLOCK_OFFSET.get() + 5;
         if (!world.isAirOrPassableBlock(x, 120, z, true)) {
            world.setBlockToAir(x, minY, z);
         }

         if (!world.isAirOrPassableBlock(x, 121, z, true)) {
            world.setBlockToAir(x, Configs.WorldGen.Underworld.UNDERWORLD_MANTLE_BLOCK_OFFSET.get() + 6, z);
         }
         return new int[]{x, minY, z};
      }
      return original;
   }

   @Inject(
           locals = LocalCapture.CAPTURE_FAILHARD,
           method = "onEntityCollidedWithBlock",
           at = @At(
                   value = "INVOKE",
                   shift = At.Shift.BEFORE,
                   target = "Lnet/minecraft/BlockPortal;initiateRunegateTeleport(Lnet/minecraft/WorldServer;IIILnet/minecraft/ServerPlayer;Z)V"
           )
   )
   private void injectTeleportTips(World par1World, int par2, int par3, int par4, Entity par5Entity, CallbackInfo ci, EntityPlayer player, int metadata, boolean is_runegate, boolean is_portal_to_world_spawn){
      if ((Configs.Misc.LOG_PLAYERS_INTERACT_WITH_PORTAL.get())) {
         ((WorldServer) par1World).getMinecraftServer().getConfigurationManager().sendChatMsg(ChatMessageComponent.createFromTranslationWithSubstitutions("gameplay.portal_door.interact", player.getEntityName()).setColor(EnumChatFormatting.DARK_RED));
      }
   }

   @Inject(method = "isTouchingBottomBedrock", at = @At("HEAD"), cancellable = true)
   public void isTouchingBottomBedrock(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
      int frame_min_y = this.getFrameMinY(world, x, y, z);
      if (frame_min_y <= 8 || (world.isUnderworld() && frame_min_y <= Configs.WorldGen.Underworld.UNDERWORLD_MANTLE_BLOCK_OFFSET.get() + 8)) {
         int frame_min_x = this.getFrameMinX(world, x, y, z);
         int frame_max_x = this.getFrameMaxX(world, x, y, z);
         int frame_min_z = this.getFrameMinZ(world, x, y, z);
         int frame_max_z = this.getFrameMaxZ(world, x, y, z);

         for (int frame_x = frame_min_x; frame_x <= frame_max_x; ++frame_x) {
            for (int frame_z = frame_min_z; frame_z <= frame_max_z; ++frame_z) {
               if (world.isBottomBlock(frame_x, frame_min_y - 1, frame_z)) {
                  cir.setReturnValue(true);
               }
            }
         }
      }
   }
   
   @Override
   @Unique
   public void setRuneTypeOverride(BlockRunestone runeTypeOverride) {
      mITE_ITEPlugin$runestoneOverride = runeTypeOverride;
   }
   
   @Override
   @Unique
   public void setPortalSeedOverride(int newSeed) {
      mITE_ITEPlugin$portalSeedOverride = newSeed;
   }
}

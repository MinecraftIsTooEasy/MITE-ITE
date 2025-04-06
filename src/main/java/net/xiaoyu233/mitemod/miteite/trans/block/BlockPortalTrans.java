package net.xiaoyu233.mitemod.miteite.trans.block;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEPortal;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BlockPortal.class)
public abstract class BlockPortalTrans extends Block implements ITEPortal {

   protected BlockPortalTrans(int par1, Material par2Material, BlockConstants constants) {
      super(par1, par2Material, constants);
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
         ((WorldServer)par1World).getMinecraftServer().getConfigurationManager().sendChatMsg(ChatMessageComponent.createFromTranslationWithSubstitutions("gameplay.portal_door.interact", player.getEntityName()).setColor(EnumChatFormatting.DARK_RED));
      }
   }

}

package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.*;
import net.xiaoyu233.fml.util.ReflectHelper;
import net.xiaoyu233.mitemod.miteite.api.ITEPlayer;
import net.xiaoyu233.mitemod.miteite.inventory.container.ContainerForgingTable;
import net.xiaoyu233.mitemod.miteite.inventory.container.ForgingTableSlots;
import net.xiaoyu233.mitemod.miteite.network.BiPacketUpdateDefense;
//import net.xiaoyu233.mitemod.miteite.network.SPacketUpdateNutrition;
import org.spongepowered.asm.mixin.*;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerTrans extends EntityPlayer implements ICrafting, ITEPlayer {
   @Shadow public NetServerHandler playerNetServerHandler;
   @Shadow private int currentWindowId;
   @Shadow protected abstract void incrementWindowID();

   public ServerPlayerTrans(World par1World, String par2Str) {
      super(par1World, par2Str);
   }

   public void displayGUIForgingTable(int x, int y, int z, ForgingTableSlots slots) {
      this.incrementWindowID();
      TileEntity tile_entity = this.worldObj.getBlockTileEntity(x, y, z);
      this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, 14, tile_entity.getCustomInvName(), 9, tile_entity.hasCustomName())).setCoords(x, y, z));
      this.openContainer = new ContainerForgingTable(slots, this, x, y, z);
      this.openContainer.windowId = this.currentWindowId;
      ReflectHelper.dyCast(ServerPlayer.class, this).sendContainerAndContentsToPlayer(this.openContainer, ((ContainerForgingTable)this.openContainer).getInventory());
      this.openContainer.addCraftingToCrafters(this);
   }

   @Override
   public void setDefenseCooldown(int cooldown) {
      super.setDefenseCooldown(cooldown);
      if (playerNetServerHandler != null) {
         this.playerNetServerHandler.sendPacketToPlayer(new BiPacketUpdateDefense(cooldown));
      }
   }
}

package net.xiaoyu233.mitemod.miteite.trans.network;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITENetHandler;
import net.xiaoyu233.mitemod.miteite.inventory.container.ContainerForgingTable;
import net.xiaoyu233.mitemod.miteite.network.CPacketStartForging;
import net.xiaoyu233.mitemod.miteite.network.CPacketSyncItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;

import java.util.ArrayList;

@Mixin(NetServerHandler.class)
public abstract class ServerNetworkManagerTrans extends NetHandler implements ITENetHandler {
   @Shadow public ServerPlayer playerEntity;
   @Shadow public abstract INetworkManager getNetManager();

   @SoftOverride
   public void handleSyncItems(CPacketSyncItems packet) {
      ArrayList<ItemStack> itemList = new ArrayList<>();

      for (int index = 0; index < this.playerEntity.openContainer.inventorySlots.size(); ++index) {
         itemList.add(((Slot)this.playerEntity.openContainer.inventorySlots.get(index)).getStack());
      }

      this.playerEntity.sendContainerAndContentsToPlayer(this.playerEntity.openContainer, itemList);
   }

   @Shadow
   public boolean isServerHandler() {
      return false;
   }

   @SoftOverride
   public void processStartForgingPacket(CPacketStartForging packet) {
      if (this.playerEntity.openContainer instanceof ContainerForgingTable) {
         ((ContainerForgingTable)this.playerEntity.openContainer).startForging();
      }

   }
}

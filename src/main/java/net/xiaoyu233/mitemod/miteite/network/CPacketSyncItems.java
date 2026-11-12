package net.xiaoyu233.mitemod.miteite.network;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;
import net.minecraft.Slot;

import java.util.ArrayList;
import java.util.List;

public class CPacketSyncItems implements Packet {
    public CPacketSyncItems() {
    }

    public CPacketSyncItems(PacketByteBuf packetByteBuf) {
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        if (entityPlayer instanceof ServerPlayer serverPlayer) {
            List<ItemStack> itemList = new ArrayList<>();
            for (Object slot : serverPlayer.openContainer.inventorySlots) {
                itemList.add(((Slot) slot).getStack());
            }
            serverPlayer.sendContainerAndContentsToPlayer(serverPlayer.openContainer, itemList);
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return MITEITENetwork.SyncItems;
    }
}

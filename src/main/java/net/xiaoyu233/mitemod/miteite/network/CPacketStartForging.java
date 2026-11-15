package net.xiaoyu233.mitemod.miteite.network;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.mitemod.miteite.inventory.container.ContainerForgingTable;

public class CPacketStartForging implements Packet {
    public CPacketStartForging() {
    }

    public CPacketStartForging(PacketByteBuf packetByteBuf) {
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        if (entityPlayer.openContainer instanceof ContainerForgingTable containerForgingTable) {
            containerForgingTable.startForging();
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return MITEITENetwork.StartForging;
    }
}

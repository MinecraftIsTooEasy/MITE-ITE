package net.xiaoyu233.mitemod.miteite.network;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.mitemod.miteite.api.ITEPlayer;

public class BiPacketUpdateDefense implements Packet {
    private int time;

    public BiPacketUpdateDefense() {
    }

    public BiPacketUpdateDefense(int time) {
        this.time = time;
    }

    public BiPacketUpdateDefense(PacketByteBuf packetByteBuf) {
        this.time = packetByteBuf.readInt();
    }

    public int getTime() {
        return this.time;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(this.time);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        ((ITEPlayer) entityPlayer).setDefenseCooldown(this.time);
    }

    @Override
    public ResourceLocation getChannel() {
        return MITEITENetwork.UpdateDefense;
    }
}

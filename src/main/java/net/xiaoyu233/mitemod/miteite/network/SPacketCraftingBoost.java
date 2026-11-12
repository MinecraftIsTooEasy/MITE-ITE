package net.xiaoyu233.mitemod.miteite.network;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.mitemod.miteite.api.ITEPlayer;

public class SPacketCraftingBoost implements Packet {
    private float factor;

    public SPacketCraftingBoost() {
    }

    public SPacketCraftingBoost(float factor) {
        this.factor = factor;
    }

    public SPacketCraftingBoost(PacketByteBuf packetByteBuf) {
        this.factor = packetByteBuf.readFloat();
    }

    public float getFactor() {
        return this.factor;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeFloat(this.factor);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        ((ITEPlayer) entityPlayer).setCraftingBoostFactor(this.factor, null);
    }

    @Override
    public ResourceLocation getChannel() {
        return MITEITENetwork.CraftingBoost;
    }
}

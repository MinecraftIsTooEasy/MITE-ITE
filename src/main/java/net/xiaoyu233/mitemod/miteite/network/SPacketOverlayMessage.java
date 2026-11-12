package net.xiaoyu233.mitemod.miteite.network;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.Minecraft;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.mitemod.miteite.api.ITEGuiIngame;

public class SPacketOverlayMessage implements Packet {
    private int color;
    private String msg;
    private int time;

    public SPacketOverlayMessage() {
    }

    public SPacketOverlayMessage(String msg, int color, int time) {
        this.msg = msg;
        this.color = color;
        this.time = time;
    }

    public SPacketOverlayMessage(PacketByteBuf packetByteBuf) {
        this.color = packetByteBuf.readInt();
        this.msg = packetByteBuf.readString();
        this.time = packetByteBuf.readInt();
    }

    public int getColor() {
        return this.color;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getTime() {
        return this.time;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(this.color);
        packetByteBuf.writeString(this.msg);
        packetByteBuf.writeInt(this.time);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        ((ITEGuiIngame) Minecraft.getMinecraft().ingameGUI).setOverlayMsg(this.msg, this.time, this.color);
    }

    @Override
    public ResourceLocation getChannel() {
        return MITEITENetwork.OverlayMessage;
    }
}

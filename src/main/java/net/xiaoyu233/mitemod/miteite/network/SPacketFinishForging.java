package net.xiaoyu233.mitemod.miteite.network;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.GuiScreen;
import net.minecraft.Minecraft;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.mitemod.miteite.gui.GuiForgingTable;

public class SPacketFinishForging implements Packet {
    private Status finishStatus;

    public SPacketFinishForging() {
    }

    public SPacketFinishForging(Status status) {
        this.finishStatus = status;
    }

    public SPacketFinishForging(PacketByteBuf packetByteBuf) {
        this.finishStatus = Status.values()[packetByteBuf.readInt()];
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(this.finishStatus.ordinal());
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        GuiScreen openingGUI = Minecraft.getMinecraft().currentScreen;
        if (openingGUI instanceof GuiForgingTable guiForgingTable) {
            guiForgingTable.enableButton();
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return MITEITENetwork.FinishForging;
    }

    public enum Status {
        COMPLETED,
        FAILED,
        CANCELED
    }
}

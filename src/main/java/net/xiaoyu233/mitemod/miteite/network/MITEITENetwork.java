package net.xiaoyu233.mitemod.miteite.network;

import moddedmite.rustedironcore.network.Network;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketReader;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.mitemod.miteite.MITEITEMod;

public class MITEITENetwork {
    public static final ResourceLocation UpdateDefense = new ResourceLocation(MITEITEMod.ITEID, "UpdateDefense");
    public static final ResourceLocation StartForging = new ResourceLocation(MITEITEMod.ITEID, "StartForging");
    public static final ResourceLocation SyncItems = new ResourceLocation(MITEITEMod.ITEID, "SyncItems");
    public static final ResourceLocation CraftingBoost = new ResourceLocation(MITEITEMod.ITEID, "CraftingBoost");
    public static final ResourceLocation FinishForging = new ResourceLocation(MITEITEMod.ITEID, "FinishForging");
    public static final ResourceLocation ForgingTableInfo = new ResourceLocation(MITEITEMod.ITEID, "ForgingTableInfo");
    public static final ResourceLocation OverlayMessage = new ResourceLocation(MITEITEMod.ITEID, "OverlayMessage");

    public static void sendToClient(ServerPlayer player, Packet packet) {
        Network.sendToClient(player, packet);
    }

    public static void sendToServer(Packet packet) {
        Network.sendToServer(packet);
    }

    public static void init() {
        if (!FishModLoader.isServer()) {
            initClient();
        }
        initServer();
    }

    private static void initClient() {
        PacketReader.registerClientPacketReader(UpdateDefense, BiPacketUpdateDefense::new);
        PacketReader.registerClientPacketReader(CraftingBoost, SPacketCraftingBoost::new);
        PacketReader.registerClientPacketReader(FinishForging, SPacketFinishForging::new);
        PacketReader.registerClientPacketReader(ForgingTableInfo, SPacketForgingTableInfo::new);
        PacketReader.registerClientPacketReader(OverlayMessage, SPacketOverlayMessage::new);
    }

    private static void initServer() {
        PacketReader.registerServerPacketReader(StartForging, packetByteBuf -> new CPacketStartForging());
        PacketReader.registerServerPacketReader(SyncItems, packetByteBuf -> new CPacketSyncItems());
    }
}

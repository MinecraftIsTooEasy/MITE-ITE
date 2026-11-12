package net.xiaoyu233.mitemod.miteite.network;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.gui.GuiForgingTable;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;
import net.xiaoyu233.mitemod.miteite.item.recipe.IFaultFeedback;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class SPacketForgingTableInfo implements Packet {
    private int infoTypeIndex;
    private SPacketForgingTableInfo.InfoType info;

    public SPacketForgingTableInfo() {
    }

    public SPacketForgingTableInfo(SPacketForgingTableInfo.InfoType info) {
        this.info = info;
        this.infoTypeIndex = this.info.getType().ordinal();
    }

    public SPacketForgingTableInfo(PacketByteBuf packetByteBuf) {
        this.infoTypeIndex = packetByteBuf.readInt();

        try {
            SPacketForgingTableInfo.InfoType info = InfoTypes.values()[this.infoTypeIndex].getCtor().get();
            info.readData(packetByteBuf);
            this.info = info;
        } catch (ArrayIndexOutOfBoundsException var5) {
            var5.printStackTrace();
        }
    }

    public SPacketForgingTableInfo.InfoType getInfo() {
        return this.info;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(this.infoTypeIndex);
        this.info.writeData(packetByteBuf);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        GuiScreen openingGUI = Minecraft.getMinecraft().currentScreen;
        if (openingGUI instanceof GuiForgingTable guiForgingTable) {
            if (this.info instanceof SPacketForgingTableInfo.EnhanceInfo info) {
                guiForgingTable.setEnhanceInfo(info.getChanceOfFailure(), info.getFaultFeedbacks(), info.getDuration(), info.getHammerDurabilityCost(), info.getAxeDurabilityCost());
            } else {
                guiForgingTable.setInfo(this.info.asString(), this.info.getColor());
            }
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return MITEITENetwork.ForgingTableInfo;
    }

    public enum InfoTypes {
        Enhance(EnhanceInfo::new),
        Failed(Failed::new),
        ReqItems(ReqItems::new),
        Succeed(Succeed::new),
        TableLevelInfo(TableLevelInfo::new),
        ToolInfo(ToolInfo::new);
        private final Supplier<InfoType> ctor;

        InfoTypes(Supplier<InfoType> ctor) {
            this.ctor = ctor;
        }

        public Supplier<InfoType> getCtor() {
            return ctor;
        }
    }

    public interface InfoType {
        String asString();

        int getColor();

        InfoTypes getType();

        void readData(PacketByteBuf packetByteBuf);

        void writeData(PacketByteBuf packetByteBuf);
    }

    public static class EnhanceInfo implements SPacketForgingTableInfo.InfoType {
        public static class FaultFeedbackData{
            private final int data;
            private final String name;

            private FaultFeedbackData(int data, String name) {
                this.data = data;
                this.name = name;
            }

            public int getData() {
                return data;
            }

            public String getName() {
                return name;
            }
        }
        private int chanceOfFailure;
        private int duration;
        private int hammerDurabilityCost;
        private int axeDurabilityCost;
        private final List<FaultFeedbackData> faultFeedbacks = new ArrayList<>();
        private EnhanceInfo() {
        }

        private EnhanceInfo(int chanceOfFailure, List<IFaultFeedback> faultFeedbacks, int duration, int hammerDurabilityCost, int axeDurabilityCost) {
            this.chanceOfFailure = chanceOfFailure;
            this.duration = duration;
            this.hammerDurabilityCost = hammerDurabilityCost;
            this.axeDurabilityCost = axeDurabilityCost;
            if (faultFeedbacks != null){
                for (IFaultFeedback faultFeedback : faultFeedbacks) {
                    if (faultFeedback != null){
                        this.faultFeedbacks.add(new FaultFeedbackData(faultFeedback.getData(),faultFeedback.getName()));
                    }
                }
            }
        }

        public static SPacketForgingTableInfo.EnhanceInfo getInstance(int chanceOfFailure, @Nullable List<IFaultFeedback> faultFeedbacks, int duration, int hammerDurabilityCost, int axeDurabilityCost) {
            return new SPacketForgingTableInfo.EnhanceInfo(chanceOfFailure, faultFeedbacks, duration, hammerDurabilityCost, axeDurabilityCost);
        }

        public int getAxeDurabilityCost() {
            return this.axeDurabilityCost;
        }

        public int getChanceOfFailure() {
            return this.chanceOfFailure;
        }

        @Override
        public InfoTypes getType() {
            return InfoTypes.Enhance;
        }

        public int getDuration() {
            return this.duration;
        }

        public List<FaultFeedbackData> getFaultFeedbacks() {
            return faultFeedbacks;
        }

        public String asString() {
            return "";
        }

        public int getColor() {
            return 0;
        }

        public int getHammerDurabilityCost() {
            return this.hammerDurabilityCost;
        }

        public void readData(PacketByteBuf packetByteBuf) {
            this.chanceOfFailure = packetByteBuf.readInt();
            int failFeedbackCount = packetByteBuf.readInt();
            for (int i = 0; i < failFeedbackCount; i++) {
                this.faultFeedbacks.add(new FaultFeedbackData(packetByteBuf.readInt(), packetByteBuf.readString()));
            }
            this.duration = packetByteBuf.readInt();
            this.hammerDurabilityCost = packetByteBuf.readInt();
            this.axeDurabilityCost = packetByteBuf.readInt();
        }

        public void writeData(PacketByteBuf packetByteBuf) {
            packetByteBuf.writeInt(this.chanceOfFailure);
            packetByteBuf.writeInt(this.faultFeedbacks.size());
            for (FaultFeedbackData faultFeedback : this.faultFeedbacks) {
                packetByteBuf.writeInt(faultFeedback.getData());
                packetByteBuf.writeString(faultFeedback.getName());
            }
            packetByteBuf.writeInt(this.duration);
            packetByteBuf.writeInt(this.hammerDurabilityCost);
            packetByteBuf.writeInt(this.axeDurabilityCost);
        }
    }

    public static class Failed implements SPacketForgingTableInfo.InfoType {
        private Failed() {

        }

        public static SPacketForgingTableInfo.Failed getInstance() {
            return new SPacketForgingTableInfo.Failed();
        }

        public String asString() {
            return I18n.getString("gui.forgingTable.failed");
        }

        public int getColor() {
            return 11141120;
        }

        @Override
        public InfoTypes getType() {
            return InfoTypes.Failed;
        }

        public void readData(PacketByteBuf packetByteBuf) {
        }

        public void writeData(PacketByteBuf packetByteBuf) {
        }
    }

    public static class ReqItems implements SPacketForgingTableInfo.InfoType {
        private List<ItemStack> items = new ArrayList<>();

        private ReqItems() {
        }

        public static SPacketForgingTableInfo.ReqItems of(List<ItemStack> items) {
            SPacketForgingTableInfo.ReqItems reqItems = new SPacketForgingTableInfo.ReqItems();
            reqItems.items = items;
            return reqItems;
        }

        public String asString() {
            StringBuilder sb = new StringBuilder();
            sb.append(I18n.getString("gui.forgingTable.item_req"));
            sb.append(": ");

            for (ItemStack item : this.items) {
                sb.append(I18n.getString(item.getMITEStyleDisplayName()));
                sb.append("X").append(item.stackSize).append(",");
            }

            return sb.toString();
        }

        public int getColor() {
            return EnumChatFormatting.LIGHT_GRAY.rgb;
        }

        public void readData(PacketByteBuf packetByteBuf) {
            int i = packetByteBuf.readInt();

            for(int i1 = 0; i1 < i; ++i1) {
                this.items.add(packetByteBuf.readItemStack());
            }

        }

        @Override
        public InfoTypes getType() {
            return InfoTypes.ReqItems;
        }

        public void writeData(PacketByteBuf packetByteBuf) {
            packetByteBuf.writeInt(this.items.size());

            for (ItemStack item : this.items) {
                packetByteBuf.writeItemStack(item);
            }

        }
    }

    public static class Succeed implements SPacketForgingTableInfo.InfoType {
        private Succeed() {
        }

        public static SPacketForgingTableInfo.Succeed getInstance() {
            return new SPacketForgingTableInfo.Succeed();
        }

        public String asString() {
            return I18n.getString("gui.forgingTable.succeed");
        }

        public int getColor() {
            return 5635925;
        }

        public void readData(PacketByteBuf packetByteBuf) {
        }

        public void writeData(PacketByteBuf packetByteBuf) {
        }

        @Override
        public InfoTypes getType() {
            return InfoTypes.Succeed;
        }
    }

    public static class TableLevelInfo implements SPacketForgingTableInfo.InfoType{
        private ForgingTableLevel req;
        @Override
        public String asString() {
            return I18n.getString("gui.forgingTable.levelToLow." + req.name().toLowerCase(Locale.ROOT));
        }

        private TableLevelInfo(){}

        private TableLevelInfo(ForgingTableLevel req) {
            this.req = req;
        }

        public static TableLevelInfo of(ForgingTableLevel req) {
            return new TableLevelInfo(req);
        }

        @Override
        public int getColor() {
            return 0xaa0000;
        }

        @Override
        public void readData(PacketByteBuf packetByteBuf) {
            this.req = ForgingTableLevel.values()[packetByteBuf.readInt()];
        }

        @Override
        public void writeData(PacketByteBuf packetByteBuf) {
            packetByteBuf.writeInt(this.req.ordinal());
        }

        @Override
        public InfoTypes getType() {
            return InfoTypes.TableLevelInfo;
        }
    }

    public static class ToolInfo implements SPacketForgingTableInfo.InfoType {
        private SPacketForgingTableInfo.ToolInfo.Tool tool;

        private ToolInfo(SPacketForgingTableInfo.ToolInfo.Tool tool) {
            this.tool = tool;
        }

        private ToolInfo() {
        }

        public static SPacketForgingTableInfo.ToolInfo of(SPacketForgingTableInfo.ToolInfo.Tool tool) {
            return new SPacketForgingTableInfo.ToolInfo(tool);
        }

        public String asString() {
            return I18n.getString(this.tool.getTranslationKey());
        }

        public int getColor() {
            return 0xaa0000;
        }

        public void readData(PacketByteBuf packetByteBuf) {
            this.tool = SPacketForgingTableInfo.ToolInfo.Tool.values()[packetByteBuf.readInt()];
        }

        public void writeData(PacketByteBuf packetByteBuf) {
            packetByteBuf.writeInt(this.tool.ordinal());
        }

        @Override
        public InfoTypes getType() {
            return InfoTypes.ToolInfo;
        }

        public enum Tool {
            AXE("gui.forgingTable.needAxe"),
            HAMMER("gui.forgingTable.needHammer"),
            MAX_LEVEL("gui.forgingTable.max_level_or_not_found");

            private final String translationKey;

            Tool(String translationKey) {
                this.translationKey = translationKey;
            }

            public String getTranslationKey() {
                return this.translationKey;
            }
        }
    }
}

package net.xiaoyu233.mitemod.miteite.item;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.util.EnumChatFormats;

import java.util.function.Predicate;


//TODO Weapon Modifiers {
//  自然祝福 - 每一个怪物被杀死都有概率恢复饱食度.有时在杀死一群怪物后,有概率对玩家进行治疗。
//  启示录 - 你的受害者将会变得奄奄一息,并且会受到越来越多凋零效果的折磨。
//  海纳百川 - 你的生命值越高，造成的伤害也就越高。
// }
// Tool Modifiers {
//  震击 - 挖掘方块时充能,到达一定层数后提供一段时间的急迫和速度
//  老成 - 挖掘方块时有几率掉落经验
//  自然祝福 - 每挖掘一个方块都有概率恢复饱食度.有时在挖掘一定数量的方块后,有概率对玩家进行治疗。
//  圣盾 - 当这个工具在你手中时,你将被赋予抗火效果.此外,损害减少,但耐久也会随之减低。
//  不动如山 - 格挡时可以减少更多的伤害
//  自动冶炼 - 自动烧制挖掘等级比此属性级数小的目标方块
// }

public enum ToolModifierTypes implements ItemModifierTypes {
    //Tool Modifiers
    EFFICIENCY_MODIFIER(0.25F, "efficiency", EnumChatFormatting.DARK_RED,20, (stack -> !ToolModifierTypes.isWeapon(stack)), 10),
    AQUADYNAMIC_MODIFIER(1.25F, "aquaynamic", EnumChatFormats.LIGHT_YELLOW_GREEN,10, (stack -> !ToolModifierTypes.isWeapon(stack)), 5),
    DURABILITY_MODIFIER(0.1F, "durability", EnumChatFormatting.DARK_PURPLE,40, (stack -> true), 5),
    DAMAGE_MODIFIER(1.0F, "damage", EnumChatFormatting.WHITE,20, (ToolModifierTypes::isWeapon), 10),
    SLOWDOWN_MODIFIER(1.0F, "slowdown", EnumChatFormats.LIGHT_BLUE,10, ToolModifierTypes::isWeapon, 5),
    UNNATURAL_MODIFIER(0.1f, "unnatural", EnumChatFormatting.LIGHT_GRAY,6, itemStack -> !ToolModifierTypes.isWeapon(itemStack),5),
    DEMON_POWER(0.25f, "demon", EnumChatFormatting.RED,4, ToolModifierTypes::isWeapon, 1),
    GEOLOGY(0.5f, "geology", EnumChatFormatting.GOLD,2,itemStack -> itemStack.getItem() instanceof ItemPickaxe, 1);
//    BEHEADING_MODIFIER(0.02f,"斩首",EnumChatFormats.DEAR_GREEN,1, ToolModifierTypes::isWeapon, 5);
    public final String nbtName;
    public final float levelAddition;
    public final String unlocalizedName;
    public final EnumChatFormatting color;
    public final int weight;
    private final Predicate<ItemStack> canApplyTo;
    private final int maxLevel;
    ToolModifierTypes(float levelAddition, String unlocalizedName, EnumChatFormatting color, int weight, Predicate<ItemStack> canApplyTo,int maxLevel){
        this.nbtName = this.name().toLowerCase();
        this.levelAddition = levelAddition;
        this.unlocalizedName = unlocalizedName;
        this.color = color;
        this.weight = weight;
        this.canApplyTo = canApplyTo;
        this.maxLevel = maxLevel;
    }

    public static boolean isWeapon(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof ItemSword || item instanceof ItemBattleAxe || item instanceof ItemWarHammer;
    }

    @Override
    public float getModifierValue(NBTTagCompound itemTag){
        return this.levelAddition * getModifierLevel(itemTag);
    }

    @Override
    public int getModifierLevel(NBTTagCompound itemTag) {
        int lvl = 0;
        if (itemTag != null && itemTag.hasKey("modifiers")) {
            NBTTagCompound modifiers = itemTag.getCompoundTag("modifiers");
            if (modifiers.hasKey(this.nbtName)) {
                lvl = modifiers.getInteger(this.nbtName);
            }
        }

        return lvl;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public String getNbtName() {
        return this.nbtName;
    }

    @Override
    public boolean canApplyTo(ItemStack itemStack) {
        return this.canApplyTo.test(itemStack);
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    public String getDisplayName() {
        return I18n.getString("modifier.tool." + this.unlocalizedName + ".name");
    }
}

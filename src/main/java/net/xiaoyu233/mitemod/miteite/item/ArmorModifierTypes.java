package net.xiaoyu233.mitemod.miteite.item;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.util.EnumChatFormats;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public enum ArmorModifierTypes implements ItemModifierTypes {
    //Armor Modifiers
    DURABILITY_MODIFIER(0.1F,"durability", EnumChatFormatting.DARK_PURPLE,20,5,(stack -> true)),
    PROJECTILE_PROTECTION_MODIFIER(0.5F,"projectile_protection", EnumChatFormats.DARK_GREY,7,5,(stack -> hasNotOtherProtectionModifier(stack,3))),
    EXPLOSION_PROTECTION_MODIFIER(0.5F,"explosion_protection", EnumChatFormatting.DARK_RED,7,5,(stack -> hasNotOtherProtectionModifier(stack,2))),
    FIRE_PROTECTION_MODIFIER(0.5F,"fire_protection", EnumChatFormats.LIGHT_ORANGE,7,5,(stack -> hasNotOtherProtectionModifier(stack,0))),
    PROTECTION_MODIFIER(0.25F,"protection", EnumChatFormatting.WHITE,5,5,(stack -> hasNotOtherProtectionModifier(stack,1))),
    STEADY_MODIFIER(0.15F,"steady", EnumChatFormatting.DARK_GRAY,8,5,(stack -> true)),
    BLESSED_MODIFIER(1F,"blessed", EnumChatFormats.SILVER,8,5,(stack -> true)),
    INDOMITABLE(0.25f,"indomitable", EnumChatFormats.LIGHT_YELLOW_GREEN,5,4,itemStack -> itemStack.getItem() instanceof ItemCuirass),
    IMMUNITY(0.15f,"immunity", EnumChatFormatting.GREEN,3,4,itemStack -> itemStack.getItem() instanceof ItemHelmet);
    public final String nbtName;
    public final float levelAddition;
    public final String unlocalizedName;
    public final EnumChatFormatting color;
    public final int weight;
    private final Predicate<ItemStack>  canApplyTo;
    private final int maxLevel;

    ArmorModifierTypes(float levelAddition, String unlocalizedName, EnumChatFormatting color, int weight,int maxLevel, Predicate<ItemStack> canApplyTo){
        this.nbtName = this.name().toLowerCase();
        this.levelAddition = levelAddition;
        this.unlocalizedName = unlocalizedName;
        this.color = color;
        this.weight = weight;
        this.canApplyTo = canApplyTo;
        this.maxLevel = maxLevel;
    }

    private static boolean hasNotOtherProtectionModifier(ItemStack stack,int protectionType){
        switch (protectionType){
            //FIRE_PROTECTION_MODIFIER
            case 0:
                return !ItemModifierTypes.hasModifier(stack, PROJECTILE_PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack,EXPLOSION_PROTECTION_MODIFIER) && ! ItemModifierTypes.hasModifier(stack,PROTECTION_MODIFIER);
            //PROTECTION_MODIFIER
            case 1:
                return !ItemModifierTypes.hasModifier(stack, PROJECTILE_PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack,EXPLOSION_PROTECTION_MODIFIER) && ! ItemModifierTypes.hasModifier(stack,FIRE_PROTECTION_MODIFIER);
            //EXPLOSION_PROTECTION_MODIFIER
            case 2:
                return !ItemModifierTypes.hasModifier(stack, PROJECTILE_PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack,PROTECTION_MODIFIER) && ! ItemModifierTypes.hasModifier(stack,FIRE_PROTECTION_MODIFIER);
            //PROJECTILE_PROTECTION_MODIFIER
            case 3:
                return !ItemModifierTypes.hasModifier(stack, EXPLOSION_PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack,PROTECTION_MODIFIER) && ! ItemModifierTypes.hasModifier(stack,FIRE_PROTECTION_MODIFIER);
            default:
                return true;
        }

    }

    @Override
    public boolean canApplyTo(ItemStack stack) {
        return this.canApplyTo.test(stack);
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    public float getModifierValue(NBTTagCompound itemTag){
        return this.levelAddition * getModifierLevel(itemTag);
    }

    public int getModifierLevel(@Nullable NBTTagCompound itemTag) {
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
        return this.weight;
    }

    @Override
    public String getNbtName() {
        return this.nbtName;
    }

    public String getDisplayName() {
        return I18n.getString("modifier.armor." + this.unlocalizedName + ".name");
    }
}

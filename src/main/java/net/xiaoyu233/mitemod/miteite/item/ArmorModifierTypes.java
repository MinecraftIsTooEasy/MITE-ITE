package net.xiaoyu233.mitemod.miteite.item;

import net.minecraft.*;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.mitemod.miteite.util.EnumChatFormats;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ArmorModifierTypes implements ItemModifierTypes {
    private static final List<ArmorModifierTypes> VALUES = new ArrayList<>();

    public final String nbtName;
    public final float levelAddition;
    public final String unlocalizedName;
    public final EnumChatFormatting color;
    public final int weight;
    private final Predicate<ItemStack> canApplyTo;
    private final int maxLevel;

    //Armor Modifiers
    public static final ArmorModifierTypes DURABILITY_MODIFIER = new ArmorModifierTypes(
            0.1F, "durability", EnumChatFormatting.DARK_PURPLE, 20, 5, (stack -> true), "DURABILITY_MODIFIER");
    public static final ArmorModifierTypes PROJECTILE_PROTECTION_MODIFIER = new ArmorModifierTypes(
            0.5F, "projectile_protection", EnumChatFormats.DARK_GREY, 7, 5, (stack -> hasNotOtherProtectionModifier(stack,3)), "PROJECTILE_PROTECTION_MODIFIER");
    public static final ArmorModifierTypes EXPLOSION_PROTECTION_MODIFIER = new ArmorModifierTypes(
            0.5F, "explosion_protection", EnumChatFormatting.DARK_RED, 7, 5, (stack -> hasNotOtherProtectionModifier(stack,2)), "EXPLOSION_PROTECTION_MODIFIER");
    public static final ArmorModifierTypes FIRE_PROTECTION_MODIFIER = new ArmorModifierTypes(
            0.5F, "fire_protection", EnumChatFormats.LIGHT_ORANGE, 7, 5, (stack -> hasNotOtherProtectionModifier(stack,0)), "FIRE_PROTECTION_MODIFIER");
    public static final ArmorModifierTypes PROTECTION_MODIFIER = new ArmorModifierTypes(
            0.25F, "protection", EnumChatFormatting.WHITE, 5, 5, (stack -> hasNotOtherProtectionModifier(stack,1)), "PROTECTION_MODIFIER");
    public static final ArmorModifierTypes STEADY_MODIFIER = new ArmorModifierTypes(
            0.15F, "steady", EnumChatFormatting.DARK_GRAY, 8, 5, (stack -> true), "STEADY_MODIFIER");
    public static final ArmorModifierTypes BLESSED_MODIFIER = new ArmorModifierTypes(
            1F, "blessed", EnumChatFormats.SILVER, 8, 5, (stack -> true), "BLESSED_MODIFIER");
    public static final ArmorModifierTypes INDOMITABLE = new ArmorModifierTypes(
            0.25f, "indomitable", EnumChatFormats.LIGHT_YELLOW_GREEN, 5, 4, itemStack -> itemStack.getItem() instanceof ItemCuirass, "INDOMITABLE");
    public static final ArmorModifierTypes IMMUNITY = new ArmorModifierTypes(
            0.15f, "immunity", EnumChatFormatting.GREEN, 3, 4, itemStack -> itemStack.getItem() instanceof ItemHelmet, "IMMUNITY");

    public ArmorModifierTypes(float levelAddition, String unlocalizedName, EnumChatFormatting color, int weight, int maxLevel, Predicate<ItemStack> canApplyTo, String nbtName) {
        this.nbtName = nbtName.toLowerCase();
        this.levelAddition = levelAddition;
        this.unlocalizedName = unlocalizedName;
        this.color = color;
        this.weight = weight;
        this.maxLevel = maxLevel;
        this.canApplyTo = canApplyTo;

        VALUES.add(this);
    }

    public static ArmorModifierTypes[] values() {
        return VALUES.toArray(new ArmorModifierTypes[0]);
    }

    public static ArmorModifierTypes valueOf(String name) {
        for (ArmorModifierTypes modifier : VALUES) {
            if (modifier.unlocalizedName.equalsIgnoreCase(name)) {
                return modifier;
            }
        }
        throw new IllegalArgumentException("No enum constant " + ArmorModifierTypes.class.getName() + "." + name);
    }

    private static boolean hasNotOtherProtectionModifier(ItemStack stack,int protectionType){
        return switch (protectionType) {
            //FIRE_PROTECTION_MODIFIER
            case 0 ->
                    !ItemModifierTypes.hasModifier(stack, PROJECTILE_PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack, EXPLOSION_PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack, PROTECTION_MODIFIER);
            //PROTECTION_MODIFIER
            case 1 ->
                    !ItemModifierTypes.hasModifier(stack, PROJECTILE_PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack, EXPLOSION_PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack, FIRE_PROTECTION_MODIFIER);
            //EXPLOSION_PROTECTION_MODIFIER
            case 2 ->
                    !ItemModifierTypes.hasModifier(stack, PROJECTILE_PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack, PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack, FIRE_PROTECTION_MODIFIER);
            //PROJECTILE_PROTECTION_MODIFIER
            case 3 ->
                    !ItemModifierTypes.hasModifier(stack, EXPLOSION_PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack, PROTECTION_MODIFIER) && !ItemModifierTypes.hasModifier(stack, FIRE_PROTECTION_MODIFIER);
            default -> true;
        };

    }

    @Override
    public boolean canApplyTo(ItemStack stack) {
        return this.canApplyTo.test(stack);
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    public float getModifierValue(NBTTagCompound itemTag) {
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

    public ChatMessageComponent getDisplayName() {
        return ChatMessageComponent.createFromTranslationKey("modifier.armor." + this.unlocalizedName + ".name");
    }
}
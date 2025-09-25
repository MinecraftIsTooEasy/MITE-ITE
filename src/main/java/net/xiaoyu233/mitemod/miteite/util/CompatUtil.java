package net.xiaoyu233.mitemod.miteite.util;

import net.minecraft.*;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.mitemod.miteite.item.ArmorModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.ToolModifierTypes;

public class CompatUtil {
    private boolean toolNbtFixed;
    private boolean pushTimeNextTick;

    public static boolean targetIsNotZombieBoss(EntityLivingBase target) {
        if (!FishModLoader.hasMod("extreme")) return true;
        return true;
    }
    
    public void fixNBT(ItemStack stack) {
        if (!this.toolNbtFixed) {
            this.toolNbtFixed = true;
            if (stack.stackTagCompound == null) {
                stack.setTagCompound(new NBTTagCompound());
                stack.stackTagCompound.setInteger("tool_level", 0);
                stack.stackTagCompound.setInteger("tool_exp", 0);
                stack.stackTagCompound.setCompoundTag("modifiers", new NBTTagCompound());
            } else if (!stack.stackTagCompound.hasKey("tool_level")) {
                stack.stackTagCompound.setInteger("tool_level", 0);
                stack.stackTagCompound.setInteger("tool_exp", 0);
                stack.stackTagCompound.setCompoundTag("modifiers", new NBTTagCompound());
            }

            if (stack.stackTagCompound.hasKey("modifiers")) {
                NBTTagCompound compound = stack.stackTagCompound.getCompoundTag("modifiers");
                int var3;
                int var4;
                float origin;
                if (stack.getItem() instanceof ItemArmor) {
                    if (!compound.hasNoTags()) {
                        ArmorModifierTypes[] var2 = ArmorModifierTypes.values();
                        var3 = var2.length;

                        for(var4 = 0; var4 < var3; ++var4) {
                            ArmorModifierTypes value = var2[var4];
                            if (compound.getTag(value.nbtName) instanceof NBTTagFloat) {
                                origin = compound.getFloat(value.nbtName);
                                compound.setInteger(value.nbtName, (int)(origin / value.levelAddition));
                            }
                        }
                    }
                } else if (!compound.hasNoTags()) {
                    ToolModifierTypes[] var7 = ToolModifierTypes.values();
                    var3 = var7.length;

                    for (var4 = 0; var4 < var3; ++var4) {
                        ToolModifierTypes value = var7[var4];
                        if (compound.getTag(value.nbtName) instanceof NBTTagFloat) {
                            origin = compound.getFloat(value.nbtName);
                            compound.setInteger(value.nbtName, (int)(origin / value.levelAddition));
                        }
                    }
                }
            }
        }
    }

    public static int getMaxToolLevel(ItemStack itemStack) {
        return 15 + itemStack.getForgingGrade();
    }

    public boolean longDayTime(World world) {
        if (!Configs.GameMechanics.FIRST_DAY_LONGER_DAY_TIME.get() || world.getDayOfOverworld() != 1 || (long) world.getTimeOfDay() >= 12000L) {
            return true;
        } else if (this.pushTimeNextTick) {
            this.pushTimeNextTick = false;
            return true;
        } else {
            this.pushTimeNextTick = true;
            return false;
        }
    }
}

package net.xiaoyu233.mitemod.miteite.api;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.NBTTagCompound;
import net.xiaoyu233.mitemod.miteite.item.ItemModifierTypes;

import javax.annotation.Nonnull;

public class ItemLevelUpContext {
    private final ItemStack stack;
    private final EntityPlayer player;
    private final NBTTagCompound itemTag;
    private final NBTTagCompound modifiers;
    private final boolean armor;
    private boolean cancelled;

    public ItemLevelUpContext(@Nonnull ItemStack stack, @Nonnull EntityPlayer player, @Nonnull NBTTagCompound itemTag, @Nonnull NBTTagCompound modifiers, boolean armor) {
        this.stack = stack;
        this.player = player;
        this.itemTag = itemTag;
        this.modifiers = modifiers;
        this.armor = armor;
    }

    @Nonnull
    public ItemStack getStack() {
        return this.stack;
    }

    @Nonnull
    public EntityPlayer getPlayer() {
        return this.player;
    }

    @Nonnull
    public NBTTagCompound getItemTag() {
        return this.itemTag;
    }

    @Nonnull
    public NBTTagCompound getModifiers() {
        return this.modifiers;
    }

    public boolean isArmor() {
        return this.armor;
    }

    public int getToolLevel() {
        return this.itemTag.getInteger("tool_level");
    }

    public int getForgingGrade() {
        return this.stack.getForgingGrade();
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean hasModifier(@Nonnull ItemModifierTypes modifier) {
        return this.modifiers.hasKey(modifier.getNbtName());
    }

    public int getModifierLevel(@Nonnull ItemModifierTypes modifier) {
        return this.modifiers.getInteger(modifier.getNbtName());
    }

    public int applyModifier(@Nonnull ItemModifierTypes modifier) {
        int level = this.getModifierLevel(modifier) + 1;
        this.modifiers.setInteger(modifier.getNbtName(), level);
        return level;
    }
}

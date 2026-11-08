package net.xiaoyu233.mitemod.miteite.inventory.container;

import net.minecraft.IInventory;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;
import net.minecraft.Slot;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingSlot;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingSlotDefinition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ForgingTableSlot extends Slot {
    private final ForgingSlotDefinition definition;

    public ForgingTableSlot(IInventory inventory, int slotIndex, ForgingSlotDefinition definition) {
        super(inventory, slotIndex, definition.getX(), definition.getY());
        this.definition = definition;
        this.xDisplayPosition = definition.getX();
        this.yDisplayPosition = definition.getY();
    }

    @Nonnull
    public ForgingSlot getType() {
        return this.definition.getType();
    }

    @Nullable
    public ResourceLocation getTexture() {
        return this.definition.getTexture();
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return this.definition.isItemValid(stack);
    }
}

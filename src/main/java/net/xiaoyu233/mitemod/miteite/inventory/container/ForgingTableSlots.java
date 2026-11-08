package net.xiaoyu233.mitemod.miteite.inventory.container;

import com.google.common.collect.Lists;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingContext;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingSlot;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingSlotDefinition;
import net.xiaoyu233.mitemod.miteite.network.SPacketFinishForging;
import net.xiaoyu233.mitemod.miteite.registry.ITERegistryImpl;
import net.xiaoyu233.mitemod.miteite.tileentity.TileEntityForgingTable;
import net.xiaoyu233.mitemod.miteite.util.ForgingHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ForgingTableSlots extends InventoryBasic {
    private final ForgingTableSlot[] slots;
    private ContainerForgingTable container;
    private final TileEntityForgingTable tileEntityForgingTable;

    public int getSize() {
        return this.slots.length;
    }

    public int getToolItemSlotIndex() {
        return this.indexOf(ForgingSlot.TOOL);
    }

    public int getAxeSlotIndex() {
        return this.indexOf(ForgingSlot.AXE);
    }

    public int getHammerSlotIndex() {
        return this.indexOf(ForgingSlot.HAMMER);
    }

    public int getOutputIndex() {
        return this.indexOf(ForgingSlot.OUTPUT);
    }

    public ForgingTableSlots(IInventory forgingTable) {
        super("ForgingTable", true, ITERegistryImpl.forgingSlots.size());
        if (forgingTable instanceof TileEntityForgingTable) {
            this.tileEntityForgingTable = (TileEntityForgingTable) forgingTable;
        } else {
            this.tileEntityForgingTable = null;
        }
        this.slots = new ForgingTableSlot[ITERegistryImpl.forgingSlots.size()];
        for (int i = 0; i < this.slots.length; i++) {
            ForgingSlotDefinition definition = ITERegistryImpl.forgingSlots.get(i);
            this.slots[i] = new ForgingTableSlot(forgingTable, i, definition);
        }
    }

    private int indexOf(@Nonnull ForgingSlot type) {
        for (int i = 0; i < this.slots.length; i++) {
            if (this.slots[i].getType() == type) {
                return i;
            }
        }
        return -1;
    }

    @Nullable
    public ForgingTableSlot getSlot(@Nonnull ForgingSlot type) {
        int index = this.indexOf(type);
        return index < 0 ? null : this.slots[index];
    }

    @Nonnull
    public ForgingTableSlot[] getForgingSlots() {
        return this.slots;
    }

    @Nullable
    public TileEntityForgingTable getTileEntity() {
        return this.tileEntityForgingTable;
    }

    public int getTableLevel() {
        return this.tileEntityForgingTable == null ? 0 : this.tileEntityForgingTable.getBlockMetadata();
    }

    @Nonnull
    public ForgingSlot getSlotType(int index) {
        return this.slots[index].getType();
    }

    public boolean canInsert(int index, @Nullable ItemStack stack) {
        return this.slots[index].isItemValid(stack);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return this.canInsert(index, stack);
    }

    @Nonnull
    private List<Slot> getMaterialSlots() {
        List<Slot> materialSlots = Lists.newArrayList();
        for (ForgingTableSlot slot : this.slots) {
            if (slot.getType() == ForgingSlot.MATERIAL) {
                materialSlots.add(slot);
            }
        }
        return materialSlots;
    }

    public void costItems(@Nonnull List<ItemStack> materials) {
        List<Slot> materialSlots = this.getMaterialSlots();
        for (ItemStack required : materials) {
            int remaining = required.stackSize;
            for (Slot slot : materialSlots) {
                if (remaining <= 0) {
                    break;
                }
                ItemStack current = slot.getStack();
                if (current != null && ItemStack.areItemStacksEqual(required, current, true, false, false, true)) {
                    int taken = Math.min(remaining, current.stackSize);
                    int left = current.stackSize - taken;
                    remaining -= taken;
                    if (left > 0) {
                        current.setStackSize(left);
                    } else {
                        slot.putStack(null);
                    }
                }
            }
        }
    }

    @Nonnull
    public List<ItemStack> getNeedItems(@Nonnull List<ItemStack> materials) {
        List<Slot> materialSlots = this.getMaterialSlots();
        List<ItemStack> missing = new ArrayList<>();
        for (ItemStack required : materials) {
            int requiredCount = required.stackSize;
            int available = 0;
            for (Slot slot : materialSlots) {
                ItemStack current = slot.getStack();
                if (current != null && ItemStack.areItemStacksEqual(required, current, true, false, false, true)) {
                    available += current.stackSize;
                }
            }
            if (available < requiredCount) {
                ItemStack need = required.copy();
                need.setStackSize(requiredCount - available);
                missing.add(need);
            }
        }
        return missing;
    }

    public void damageHammerAndAxe(int hammerDurabilityCost, int axeDurabilityCost) {
        ForgingTableSlot hammer = this.getSlot(ForgingSlot.HAMMER);
        ForgingTableSlot axe = this.getSlot(ForgingSlot.AXE);
        if (hammer != null) {
            hammer.putStack(this.damageItem(hammer.getStack(), hammerDurabilityCost));
        }
        if (axe != null) {
            axe.putStack(this.damageItem(axe.getStack(), axeDurabilityCost));
        }
    }

    private ItemStack damageItem(ItemStack itemStack, int damage) {
        if (itemStack != null) {
            int rawDamage = itemStack.getItemDamage();
            int maxDamage = itemStack.getMaxDamage();
            int resultDamage = rawDamage + damage;
            return resultDamage > maxDamage ? null : itemStack.setItemDamage(resultDamage);
        } else {
            return null;
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        for (int i = 0; i < this.slots.length; i++) {
            this.tryWriteSlotStack(nbt, this.slots[i], "Slot" + i);
        }
    }

    public void dropItems(World world, int x, int y, int z) {
        for (int var2 = 0; var2 < this.tileEntityForgingTable.getSizeInventory(); ++var2) {
            ItemStack var3 = this.tileEntityForgingTable.getItem(var2);
            if (var3 != null) {
                float var10 = world.rand.nextFloat() * 0.8F + 0.1F;
                float var11 = world.rand.nextFloat() * 0.8F + 0.1F;

                EntityItem var14;
                for (float var12 = world.rand.nextFloat() * 0.8F + 0.1F; var3.stackSize > 0; world.spawnEntityInWorld(var14)) {
                    int var13 = world.rand.nextInt(21) + 10;
                    if (var13 > var3.stackSize) {
                        var13 = var3.stackSize;
                    }

                    var3.stackSize -= var13;
                    var14 = new EntityItem(world, (float) x + var10, (float) y + var11, (float) z + var12, new ItemStack(var3.itemID, var13, var3.getItemSubtype()));
                    if (var3.isItemDamaged()) {
                        var14.getEntityItem().setItemDamage(var3.getItemDamage());
                    }

                    float var15 = 0.05F;
                    var14.motionX = (float) world.rand.nextGaussian() * var15;
                    var14.motionY = (float) world.rand.nextGaussian() * var15 + 0.2F;
                    var14.motionZ = (float) world.rand.nextGaussian() * var15;
                    if (var3.getItem().hasQuality()) {
                        var14.getEntityItem().setQuality(var3.getQuality());
                    }

                    if (var3.hasTagCompound()) {
                        var14.getEntityItem().setTagCompound((NBTTagCompound) var3.getTagCompound().copy());
                    }
                }
            }
        }
    }

    @Nullable
    public ItemStack getAxeItem() {
        ForgingTableSlot axe = this.getSlot(ForgingSlot.AXE);
        return axe == null ? null : axe.getStack();
    }

    @Nullable
    public ItemStack getHammerItem() {
        ForgingTableSlot hammer = this.getSlot(ForgingSlot.HAMMER);
        return hammer == null ? null : hammer.getStack();
    }

    @Nullable
    public ItemStack getToolItem() {
        ForgingTableSlot tool = this.getSlot(ForgingSlot.TOOL);
        return tool == null ? null : tool.getStack();
    }

    public void setToolItem(ItemStack toolItem) {
        ForgingTableSlot tool = this.getSlot(ForgingSlot.TOOL);
        if (tool != null) {
            tool.putStack(toolItem);
        }
    }

    @Nullable
    public ItemStack getOutput() {
        ForgingTableSlot output = this.getSlot(ForgingSlot.OUTPUT);
        return output == null ? null : output.getStack();
    }

    public void setOutput(ItemStack output) {
        ForgingTableSlot slot = this.getSlot(ForgingSlot.OUTPUT);
        if (slot != null) {
            slot.putStack(output);
        }
    }

    public void onItemsChanged() {
        this.onItemsChanged(null);
    }

    public void onItemsChanged(@Nullable EntityPlayer player) {
        ForgingContext context = null;
        if (this.getToolItem() != null) {
            context = ForgingHelper.preview(this, player, this.getTableLevel());
        }
        this.updateInfo(context);
    }

    @Nullable
    public ForgingContext previewContext(@Nullable EntityPlayer player) {
        if (this.getToolItem() == null) {
            return null;
        }
        return ForgingHelper.preview(this, player, this.getTableLevel());
    }

    public void updateInfo(@Nullable ForgingContext context) {
        if (this.container != null) {
            this.container.updateInfo(context);
        }
    }

    public void onFinishForging(SPacketFinishForging.Status finishStatus) {
        if (this.container != null) {
            this.container.finishForging(finishStatus);
        }
    }

    public void updateSlots() {
        if (this.container != null) {
            this.container.onCraftMatrixChanged();
        }
    }

    public void updateTime(int time) {
        if (this.container != null) {
            this.container.updateTime(time);
        }
    }

    public void initSlots(ContainerForgingTable forgingTable) {
        for (ForgingTableSlot slot : this.slots) {
            forgingTable.addSlot(slot);
        }
        if (this.tileEntityForgingTable != null) {
            this.tileEntityForgingTable.openChest();
        }
        this.container = forgingTable;
    }

    void onContainerClosed() {
        if (!this.container.world.isRemote && this.tileEntityForgingTable != null) {
            this.tileEntityForgingTable.closeChest();
        }
    }

    public void readFromNBT(NBTTagCompound nbt, TileEntityForgingTable tileEntityForgingTable) {
        String[] oldNames = {"Up", "Left", "Right", "DownLeft", "DownRight", "Tool", "Axe", "Hammer", "Output"};
        for (int i = 0; i < this.slots.length; i++) {
            NBTTagCompound tag = null;
            if (nbt.hasKey("Slot" + i)) {
                tag = nbt.getCompoundTag("Slot" + i);
            } else if (i < oldNames.length && nbt.hasKey(oldNames[i])) {
                tag = nbt.getCompoundTag(oldNames[i]);
            }
            tileEntityForgingTable.setItem(i, tag == null ? null : ItemStack.loadItemStackFromNBT(tag));
        }
    }

    private void tryWriteSlotStack(NBTTagCompound nbt, Slot slot, String name) {
        if (slot.getStack() != null) {
            nbt.setCompoundTag(name, slot.getStack().writeToNBT(new NBTTagCompound()));
        }
    }
}

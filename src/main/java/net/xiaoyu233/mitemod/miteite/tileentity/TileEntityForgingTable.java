package net.xiaoyu233.mitemod.miteite.tileentity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingContext;
import net.xiaoyu233.mitemod.miteite.block.BlockForgingTable;
import net.xiaoyu233.mitemod.miteite.inventory.container.ForgingTableSlots;
import net.xiaoyu233.mitemod.miteite.network.SPacketFinishForging;
import net.xiaoyu233.mitemod.miteite.registry.ITERegistryImpl;
import net.xiaoyu233.mitemod.miteite.util.ForgingHelper;

import javax.annotation.Nullable;
import java.util.Arrays;

public class TileEntityForgingTable extends TileEntity implements IInventory {
    private String customName;
    private final ItemStack[] items = new ItemStack[ITERegistryImpl.forgingSlots.size()];
    private final ForgingTableSlots slots = new ForgingTableSlots(this);
    private int forgingTime;
    private int currentFailCheckTime;
    //nullable
    private ForgingContext context;
    private int maxTime;
    private boolean isForging;
    private boolean isUsing;
    private boolean resolving;

    public void setItem(int index, ItemStack itemStack) {
        this.items[index] = itemStack;
    }

    @Override
    //onContainerClosed
    public void closeChest() {
        this.finishForging();
        this.isUsing = false;
    }

    private void completeForging() {
        ForgingContext context = this.context;
        if (context == null) {
            return;
        }
        this.resolving = true;
        ItemStack result = ForgingHelper.complete(context);
        this.getWorldObj().playSoundAtBlock(this.xCoord, this.yCoord, this.zCoord, "random.levelup", 1.0F, 1.0F);
        this.slots.setOutput(result);
        this.slots.onFinishForging(SPacketFinishForging.Status.COMPLETED);
        this.slots.damageHammerAndAxe(context.getHammerDurabilityCost(), context.getAxeDurabilityCost());
        this.slots.costItems(context.getMaterials());
        this.slots.setToolItem(null);
        this.resolving = false;
        ForgingHelper.afterForging(context, true);
        this.slots.updateSlots();
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.items[index] != null) {
            ItemStack var3;
            if (this.items[index].stackSize <= count) {
                var3 = this.items[index];
                this.items[index] = null;
            } else {
                var3 = this.items[index].splitStack(count);
                if (this.items[index].stackSize == 0) {
                    this.items[index] = null;
                }
            }

            return var3;
        } else {
            return null;
        }
    }

    public void dropAllItems() {
        this.slots.dropItems(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    private void failForging() {
        ForgingContext context = this.context;
        if (context == null) {
            return;
        }
        this.resolving = true;
        this.getWorldObj().playSoundAtBlock(this.xCoord, this.yCoord, this.zCoord, "random.anvil_break", 1.0F, 1.1F);
        this.slots.onFinishForging(SPacketFinishForging.Status.FAILED);
        ItemStack result = ForgingHelper.fail(context);
        this.slots.setToolItem(result);
        this.slots.damageHammerAndAxe(context.getFailureHammerDurabilityCost(), context.getFailureAxeDurabilityCost());
        this.slots.costItems(context.getMaterials());
        this.resolving = false;
        ForgingHelper.afterForging(context, false);
        this.slots.updateSlots();
    }

    private void finishForging() {
        this.isForging = false;
        this.forgingTime = 0;
        this.context = null;
        this.maxTime = 0;
        this.slots.updateTime(0);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public ItemStack getItem(int index) {
        return this.items[index];
    }

    public boolean b() {
        return this.customName != null && this.customName.length() > 0;
    }

    @Override
    public int getSizeInventory() {
        return this.items.length;
    }

    public ForgingTableSlots getSlots() {
        return this.slots;
    }

    @Override
    //getItemStack
    public ItemStack getStackInSlot(int var1) {
        return this.items[var1];
    }

    @Override
    //removeItem
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.items[par1] != null) {
            ItemStack var2 = this.items[par1];
            this.items[par1] = null;
            return var2;
        } else {
            return null;
        }
    }

    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return this.slots.isItemValidForSlot(var1, var2);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (player.getWorld().getBlock(this.xCoord, this.yCoord, this.zCoord) instanceof BlockForgingTable && player.getWorld().getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) instanceof TileEntityForgingTable) {
            return player.getDistance((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
        } else {
            return false;
        }
    }

    @Override
    public void onInventoryChanged() {
        super.onInventoryChanged();
        if (this.resolving) {
            return;
        }

        ForgingContext preview = this.slots.getToolItem() != null
                ? ForgingHelper.preview(this.slots, null, this.getBlockMetadata())
                : null;
        this.slots.updateInfo(preview);

        if (this.context != null) {
            boolean changed = preview == null
                    || preview.getRecipe() != this.context.getRecipe()
                    || preview.getTime() != this.context.getTime();
            if (changed) {
                this.finishForging();
                this.slots.onFinishForging(SPacketFinishForging.Status.CANCELED);
            }
        }
    }

    @Override
    public void openChest() {
        this.isUsing = true;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.slots.readFromNBT(par1NBTTagCompound, this);
    }

    @Override
    //setItem
    public void setInventorySlotContents(int var1, ItemStack var2) {
        this.items[var1] = var2;
    }

    public boolean startForging(@Nullable EntityPlayer player) {
        ForgingContext context = ForgingHelper.createContext(this.slots, player, this.getBlockMetadata());
        if (!ForgingHelper.prepare(context)) {
            return false;
        }
        this.context = context;
        this.isForging = true;
        this.maxTime = Math.max(context.getTime(), 1);
        this.currentFailCheckTime = this.getWorldObj().rand.nextInt(this.maxTime);
        return true;
    }

    public void destroyInventory() {
        Arrays.fill(this.items, null);
    }

    public void updateEntity() {
        if (!this.getWorldObj().isRemote && this.isForging && this.context != null) {
            ++this.forgingTime;
            this.slots.updateTime(this.forgingTime);
            if (this.getWorldObj().rand.nextInt(100) < 1) {
                this.getWorldObj().getAsWorldServer().playSoundAtBlock(this.xCoord, this.yCoord, this.zCoord, "random.anvil_land", 1.0F, 1.0F);
            }

            if (this.forgingTime == this.currentFailCheckTime) {
                if (this.getWorldObj().rand.nextInt(100) < this.context.getFailureChance()) {
                    this.failForging();
                    this.finishForging();
                }
            } else if (this.forgingTime >= this.maxTime) {
                this.completeForging();
                this.finishForging();
            }
        }
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        if (this.b()) {
            par1NBTTagCompound.setString("CustomName", this.customName);
        }

        this.slots.writeToNBT(par1NBTTagCompound);
    }

    public boolean isUsing() {
        return this.isUsing;
    }
}

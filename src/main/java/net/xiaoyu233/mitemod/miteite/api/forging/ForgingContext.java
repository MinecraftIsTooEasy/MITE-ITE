package net.xiaoyu233.mitemod.miteite.api.forging;

import net.minecraft.EntityPlayer;
import net.minecraft.EnumQuality;
import net.minecraft.ItemStack;
import net.xiaoyu233.mitemod.miteite.inventory.container.ForgingTableSlots;
import net.xiaoyu233.mitemod.miteite.tileentity.TileEntityForgingTable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ForgingContext {
    @Nullable
    private final TileEntityForgingTable tileEntity;
    private final ForgingTableSlots slots;
    @Nullable
    private final EntityPlayer player;
    @Nullable
    private final ItemStack toolStack;
    private final int tableLevel;

    @Nullable
    private IForgingRecipe recipe;
    private int failureChance;
    private int time;
    private int axeDurabilityCost;
    private int hammerDurabilityCost;
    private int failureAxeDurabilityCost;
    private int failureHammerDurabilityCost;
    @Nullable
    private EnumQuality qualityReward;
    private boolean upgradeLevel = true;
    private boolean cancelled;
    private final List<ItemStack> materials = new ArrayList<>(5);
    private final List<IForgingEffect> successEffects = new ArrayList<>();
    private final List<IForgingEffect> failureEffects = new ArrayList<>();

    public ForgingContext(@Nullable TileEntityForgingTable tileEntity, @Nonnull ForgingTableSlots slots, @Nullable EntityPlayer player, @Nullable ItemStack toolStack, int tableLevel) {
        this.tileEntity = tileEntity;
        this.slots = slots;
        this.player = player;
        this.toolStack = toolStack;
        this.tableLevel = tableLevel;
    }

    @Nullable
    public TileEntityForgingTable getTileEntity() {
        return this.tileEntity;
    }

    @Nonnull
    public ForgingTableSlots getSlots() {
        return this.slots;
    }

    @Nullable
    public EntityPlayer getPlayer() {
        return this.player;
    }

    @Nullable
    public ItemStack getToolStack() {
        return this.toolStack;
    }

    public int getTableLevel() {
        return this.tableLevel;
    }

    @Nullable
    public ItemStack getAxeStack() {
        return this.slots.getAxeItem();
    }

    @Nullable
    public ItemStack getHammerStack() {
        return this.slots.getHammerItem();
    }

    @Nullable
    public IForgingRecipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(@Nullable IForgingRecipe recipe) {
        this.recipe = recipe;
    }

    @Nonnull
    public IForgingRecipe requireRecipe() {
        if (this.recipe == null) {
            throw new IllegalStateException("Forging recipe has not been resolved yet");
        }
        return this.recipe;
    }

    public int getFailureChance() {
        return this.failureChance;
    }

    public void setFailureChance(int failureChance) {
        this.failureChance = failureChance;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getAxeDurabilityCost() {
        return this.axeDurabilityCost;
    }

    public void setAxeDurabilityCost(int axeDurabilityCost) {
        this.axeDurabilityCost = axeDurabilityCost;
    }

    public int getHammerDurabilityCost() {
        return this.hammerDurabilityCost;
    }

    public void setHammerDurabilityCost(int hammerDurabilityCost) {
        this.hammerDurabilityCost = hammerDurabilityCost;
    }

    public int getFailureAxeDurabilityCost() {
        return this.failureAxeDurabilityCost;
    }

    public void setFailureAxeDurabilityCost(int failureAxeDurabilityCost) {
        this.failureAxeDurabilityCost = failureAxeDurabilityCost;
    }

    public int getFailureHammerDurabilityCost() {
        return this.failureHammerDurabilityCost;
    }

    public void setFailureHammerDurabilityCost(int failureHammerDurabilityCost) {
        this.failureHammerDurabilityCost = failureHammerDurabilityCost;
    }

    @Nullable
    public EnumQuality getQualityReward() {
        return this.qualityReward;
    }

    public void setQualityReward(@Nullable EnumQuality qualityReward) {
        this.qualityReward = qualityReward;
    }

    public boolean isUpgradeLevel() {
        return this.upgradeLevel;
    }

    public void setUpgradeLevel(boolean upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    @Nonnull
    public List<ItemStack> getMaterials() {
        return this.materials;
    }

    public void setMaterials(@Nonnull List<ItemStack> materials) {
        this.materials.clear();
        for (ItemStack stack : materials) {
            this.materials.add(stack.copy());
        }
    }

    @Nonnull
    public List<IForgingEffect> getSuccessEffects() {
        return this.successEffects;
    }

    public void addSuccessEffect(@Nonnull IForgingEffect effect) {
        this.successEffects.add(effect);
    }

    @Nonnull
    public List<IForgingEffect> getFailureEffects() {
        return this.failureEffects;
    }

    public void addFailureEffect(@Nonnull IForgingEffect effect) {
        this.failureEffects.add(effect);
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}

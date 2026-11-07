package net.xiaoyu233.mitemod.miteite.api.forging;

import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Predicate;


public final class ForgingSlotDefinition {
    private final ResourceLocation id;
    private final ForgingSlot type;
    private final int x;
    private final int y;
    private final ResourceLocation texture;
    private final Predicate<ItemStack> validator;

    public ForgingSlotDefinition(@Nonnull ResourceLocation id, @Nonnull ForgingSlot type, int x, int y, @Nullable ResourceLocation texture, @Nonnull Predicate<ItemStack> validator) {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.validator = validator;
    }

    @Nonnull
    public ResourceLocation getId() {
        return this.id;
    }

    @Nonnull
    public ForgingSlot getType() {
        return this.type;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Nullable
    public ResourceLocation getTexture() {
        return this.texture;
    }

    public boolean isItemValid(@Nullable ItemStack stack) {
        return stack != null && this.validator.test(stack);
    }
}

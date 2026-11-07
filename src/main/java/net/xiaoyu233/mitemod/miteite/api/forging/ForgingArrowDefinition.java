package net.xiaoyu233.mitemod.miteite.api.forging;

import net.minecraft.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public record ForgingArrowDefinition(ResourceLocation id, int x, int y, ResourceLocation texture, ResourceLocation fullTexture) {
    public ForgingArrowDefinition(@Nonnull ResourceLocation id, int x, int y, @Nullable ResourceLocation texture, @Nullable ResourceLocation fullTexture) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.fullTexture = fullTexture;
    }
    
    @Override
    @Nonnull
    public ResourceLocation id() {
        return this.id;
    }
    
    @Override
    @Nullable
    public ResourceLocation texture() {
        return this.texture;
    }
    
    @Override
    @Nullable
    public ResourceLocation fullTexture() {
        return this.fullTexture;
    }
}

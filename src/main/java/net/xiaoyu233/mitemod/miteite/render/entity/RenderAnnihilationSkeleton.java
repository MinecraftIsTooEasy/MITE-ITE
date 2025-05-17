package net.xiaoyu233.mitemod.miteite.render.entity;

import net.minecraft.*;
import org.lwjgl.opengl.GL11;

public class RenderAnnihilationSkeleton extends RenderBiped {
    public RenderAnnihilationSkeleton() {
        super(new ModelSkeleton(), 0.5F);
    }

    protected boolean forceGlowOverride() {
        return true;
    }

    @Override
    protected void setTextures() {
        this.setTexture(0, "miteite:textures/entity/skeleton/annihilation_skeleton");
    }

    @Override
    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
        return this.textures[0];
    }

    @Override
    protected void func_82422_c() {
        GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.func_110856_a((EntitySkeleton)par1Entity);
    }
}

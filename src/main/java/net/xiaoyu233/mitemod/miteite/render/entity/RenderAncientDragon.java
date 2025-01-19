package net.xiaoyu233.mitemod.miteite.render.entity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.entity.EntityAncientDragon;
import net.xiaoyu233.mitemod.miteite.render.entity.model.ModelAncientDragon;
import org.lwjgl.opengl.GL11;

public class RenderAncientDragon extends RenderLiving {
    public RenderAncientDragon() {
        super(new ModelAncientDragon(), 3.0F);
    }

    protected void preRenderScale(EntityAncientDragon par1EntityGiantZombie, float par2) {
        GL11.glScalef(13.5F, 13.5F, 13.5F);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.textures[0];
    }

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        this.preRenderScale((EntityAncientDragon) par1EntityLivingBase, par2);
    }

    protected void setTextures() {
        this.setTexture(0, "miteite:textures/entity/ancient_dragon/main");
    }
}
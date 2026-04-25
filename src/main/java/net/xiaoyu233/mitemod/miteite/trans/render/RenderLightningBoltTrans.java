package net.xiaoyu233.mitemod.miteite.trans.render;

import net.minecraft.EntityLightningBolt;
import net.minecraft.Minecraft;
import net.minecraft.RenderLightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderLightningBolt.class)
public class RenderLightningBoltTrans {

    @Redirect(method = "doRenderLightningBolt", at = @At(value = "FIELD", target = "Lnet/minecraft/Minecraft;raining_strength_for_render_view_entity:F"))
    private float allowRenderLightningInNoSky(Minecraft minecraft, EntityLightningBolt lightning, double x, double y, double z, float yaw, float partialTicks) {
        float rainStrength = minecraft.raining_strength_for_render_view_entity;
        if (lightning != null && lightning.worldObj != null) {
            return Math.max(rainStrength, 1.0F);
        }
        return rainStrength;
    }
}

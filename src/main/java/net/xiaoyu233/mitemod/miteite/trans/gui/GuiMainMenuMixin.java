package net.xiaoyu233.mitemod.miteite.trans.gui;

import net.minecraft.GuiMainMenu;
import net.minecraft.GuiScreen;
import net.minecraft.ScaledResolution;
import net.xiaoyu233.mitemod.miteite.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixin extends GuiScreen {
    @Inject(method = "drawScreen", at = @At("RETURN"))
    private void drawScreen(int par1, int par2, float par3, CallbackInfo ci) {
        ScaledResolution resolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        String miteiteVersion = "MITE-ITE-" + Constant.MITE_ITE_VERSION + " modded by XiaoYu233 & Xy_Lose";
        this.drawString(this.fontRenderer,  miteiteVersion, resolution.getScaledWidth() - fontRenderer.getStringWidth(miteiteVersion) - 2, this.height - 20, 0xFFA0A0A0);
    }
}

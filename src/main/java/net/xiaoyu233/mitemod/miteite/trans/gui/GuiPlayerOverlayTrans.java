package net.xiaoyu233.mitemod.miteite.trans.gui;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEGuiIngame;
import net.xiaoyu233.mitemod.miteite.util.Constant;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GuiIngame.class)
public class GuiPlayerOverlayTrans extends Gui implements ITEGuiIngame {
   @Shadow
   @Final
   private Minecraft mc;
   private String overlayMsg;
   private int overlayMsgColor;
   private int overlayMsgDisplayTime;

   public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
      super.drawTexturedModalRect(x, y, textureX, textureY, width, height);
   }


   @Inject(
           method = "func_110327_a(II)V",
           at = @At(
                   value = "INVOKE_STRING",
                   target = "Lnet/minecraft/Profiler;endStartSection(Ljava/lang/String;)V",
                   args = "ldc=air",
                   shift = At.Shift.AFTER
           )
   )
   private void injectRenderOverlayMessage(CallbackInfo c) {
      FontRenderer var8 = this.mc.fontRenderer;
      if (this.overlayMsgDisplayTime > 0) {
         this.mc.mcProfiler.startSection("overlayMessage");
         ScaledResolution window = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
         int var25 = window.getScaledWidth();
         int var26 = window.getScaledHeight();
         GL11.glPushMatrix();
         GL11.glTranslatef((float)(var25 / 2), (float)(var26 - 68), 0.0F);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         var8.drawString(this.overlayMsg, -var8.getStringWidth(this.overlayMsg) / 2, -4, this.overlayMsgColor);
         GL11.glDisable(3042);
         GL11.glPopMatrix();
         this.mc.mcProfiler.endSection();
         --this.overlayMsgDisplayTime;
      }
   }

   @Redirect(
           method = "func_110327_a(II)V",
           at = @At(
                   value = "INVOKE",
                   target = "Lnet/minecraft/AttributeInstance;getAttributeValue()D"
           )
   )
   private double redirectHealthLimit(AttributeInstance att) {
      return this.mc.thePlayer.getHealthLimit();
   }

   @Unique
   @Override
   public void setOverlayMsg(String overlayMsg, int displayTime, int color) {
      this.overlayMsg = overlayMsg;
      this.overlayMsgColor = color;
      this.overlayMsgDisplayTime = displayTime;
   }
}

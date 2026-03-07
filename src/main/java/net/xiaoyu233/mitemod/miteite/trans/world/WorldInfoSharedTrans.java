package net.xiaoyu233.mitemod.miteite.trans.world;

import net.minecraft.NBTTagCompound;
import net.minecraft.WorldInfoShared;
import net.xiaoyu233.mitemod.miteite.api.ITEWorldInfoShared;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldInfoShared.class)
public class WorldInfoSharedTrans implements ITEWorldInfoShared {
    @Unique
    private boolean ite$timeBack = false;

    @Inject(method = "updateTagCompound", at = @At("RETURN"))
    private void onNBTWrite(NBTTagCompound par1NBTTagCompound, NBTTagCompound par2NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setBoolean("ite:time_back", this.ite$timeBack);
    }

    @Inject(method = "<init>(Lnet/minecraft/NBTTagCompound;)V", at = @At("RETURN"))
    private void onNBTRead(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        this.ite$timeBack = par1NBTTagCompound.hasKey("ite:time_back") && par1NBTTagCompound.getBoolean("ite:time_back");
    }

    @Override
    public boolean ite$isTimeBack() {
        return this.ite$timeBack;
    }

    @Override
    public void ite$setTimeBack(boolean timeBack) {
        this.ite$timeBack = timeBack;
    }
}

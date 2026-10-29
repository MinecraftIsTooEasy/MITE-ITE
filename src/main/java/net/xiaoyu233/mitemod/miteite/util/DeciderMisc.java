package net.xiaoyu233.mitemod.miteite.util;

import net.minecraft.EntityLiving;

public class DeciderMisc {
   public static boolean mobCanBlock(EntityLiving owner){
      return owner.getHeldItem() != null && owner.getRNG().nextInt(10) > 8;
   }
}

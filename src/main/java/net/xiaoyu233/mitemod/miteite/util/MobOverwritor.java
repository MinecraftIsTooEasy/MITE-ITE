package net.xiaoyu233.mitemod.miteite.util;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEWorld;

public class MobOverwritor {
   public static void setArmor_ITE(EntityLiving entityLiving, Item boots, Item leggings, Item cuirass, Item helmet, int dayBeforeApplyingDefault, int dayOffset){
      int day = Math.max(0, ((ITEWorld)entityLiving.getWorld()).getDayOfOverworld() + dayOffset);
      if (day < dayBeforeApplyingDefault) {
         entityLiving.setBoots(new ItemStack(boots).randomizeForMob(entityLiving, true));
         entityLiving.setLeggings(new ItemStack(leggings).randomizeForMob(entityLiving, true));
         entityLiving.setCuirass(new ItemStack(cuirass).randomizeForMob(entityLiving, true));
         entityLiving.setHelmet(new ItemStack(helmet).randomizeForMob(entityLiving, true));
      } else {
         MonsterUtil.addDefaultArmor(day, entityLiving, true);
      }
   }
}

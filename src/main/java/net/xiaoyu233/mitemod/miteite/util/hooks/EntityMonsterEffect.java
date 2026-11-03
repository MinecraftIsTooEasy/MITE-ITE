package net.xiaoyu233.mitemod.miteite.util.hooks;

import net.minecraft.EntityLiving;
import net.minecraft.EntityMob;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.xiaoyu233.mitemod.miteite.util.MonsterUtil;

import java.util.Random;

public class EntityMonsterEffect {
   public static void addDefaultPotionEffect(EntityLiving theMob, int hour, int day){
      if (day > 32 && ((day % 2 == 0 || day > 64) && hour >= 18 || ((day - 1) % 2 == 0 || day > 64) && hour <= 6)) {
         theMob.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 999999, Math.min(theMob.getRNG().nextInt(Math.max((day - 32) / 96, 1)), 4), true));
         Random rand = theMob.getRNG();
         if (rand.nextInt(5) == 0) {
            theMob.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 999999, Math.min(theMob.getRNG().nextInt(Math.max((day - 32) / 128, 1)), 3), true));
         }
      } else if (day > 128) {
         Random rand = theMob.getRNG();
         if (rand.nextInt(4) < (day - 96) / 32) {
            theMob.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 999999, Math.min(theMob.getRNG().nextInt(Math.max((day - 32) / 96, 1)), 4), true));
         }

         if (rand.nextInt(5) < (day - 96) / 32) {
            theMob.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 999999, Math.min(theMob.getRNG().nextInt(Math.max((day - 32) / 128, 1)), 3), true));
         }
      }
   }
}

package net.xiaoyu233.mitemod.miteite.trans.entity;

import com.google.common.collect.Lists;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEItem;
import net.xiaoyu233.mitemod.miteite.registry.ITERegistryImpl;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Arrays;
import java.util.List;

@Mixin(EntityWolf.class)
public class EntityWolfTrans extends EntityTameable {
//   @Unique
//   private static final List<Item> MEATS;

//   static {
//      MEATS = Lists.newArrayList(new Item[]{Item.porkRaw, Item.beefRaw, Item.chickenRaw, Item.lambchopRaw});
//   }


   public EntityWolfTrans(World par1World) {
      super(par1World);
   }

   @Shadow
   public EntityAgeable createChild(EntityAgeable var1) {
      return null;
   }

   protected EntityPlayer findPlayerToAttack(float max_distance) {
      if ((Configs.Entities.WOLVES_ATTACK_PLAYERS_WITH_MEAT.get())) {
         EntityPlayer var1 = super.findPlayerToAttack(64.0F);
         if (var1 != null) {
            if (!this.isTamed()) {
               return this.hasMeatInHotbar(var1) ? var1 : null;
            } else {
               return super.findPlayerToAttack(max_distance);
            }
         } else {
            return null;
         }
      } else {
         return super.findPlayerToAttack(max_distance);
      }
   }

   @Shadow
   protected int getTamingOutcome(EntityPlayer EntityPlayer) {
      return 0;
   }

   @Unique
   private boolean hasMeatInHotbar(EntityPlayer par1EntityPlayer) {
      if (this.isDecoy()) {
         return false;
      } else if (((ITEItem) par1EntityPlayer.getHeldItemStack().getItem()).isMeat(par1EntityPlayer.getHeldItemStack().getItem())) {
         return true;
      } else {
         int num_meats = 0;
         Item item = par1EntityPlayer.getHeldItemStack().getItem();
         while (item.isMeat(item)) {
            num_meats += par1EntityPlayer.inventory.getHotbarSlotContainItem(item);
         }
         return num_meats > 0 && this.rand.nextInt(2000) < num_meats;
      }
   }

   @Shadow
   protected boolean isHostileToPlayers() {
      return false;
   }
}
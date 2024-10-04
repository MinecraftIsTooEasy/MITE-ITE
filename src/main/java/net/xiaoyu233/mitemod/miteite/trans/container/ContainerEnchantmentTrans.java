package net.xiaoyu233.mitemod.miteite.trans.container;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerEnchantment.class)
public abstract class ContainerEnchantmentTrans extends Container {
   @Shadow
   private int posX;
   @Shadow
   private int posY;
   @Shadow
   private int posZ;

   public ContainerEnchantmentTrans(EntityPlayer player) {
      super(player);
   }

   @ModifyConstant(method={"calcEnchantmentLevelsForSlot"}, constant={@Constant(intValue=24, ordinal=0)})
   private int vibranium(int constant, @Local Item item) {
      if (item.getHardestMetalMaterial() == Materials.vibranium) {
         return Integer.MAX_VALUE;
      }
      return constant;
   }

   @ModifyConstant(method={"calcEnchantmentLevelsForSlot"}, constant={@Constant(intValue=2, ordinal=0)})
   private int modifyCommon(int constant) {
      return constant * 2;
   }

   @Shadow
   public boolean canInteractWith(EntityPlayer entityPlayer) {
      return false;
   }

   @Overwrite
   private int getNumAccessibleBookshelves() {
      int num_bookshelves = 0;
      for (int dy = 0; dy <= 3 && (dy != 3 || this.world.isAirOrPassableBlock(this.posX, this.posY + 1, this.posZ, false)); ++dy) {
         boolean[][] is_block_accessible = new boolean[5][5];
         if (this.world.isAirOrPassableBlock(this.posX, this.posY + dy, this.posZ - 1, false)) {
            is_block_accessible[2][0] = true;
            if (this.world.isAirOrPassableBlock(this.posX - 1, this.posY + dy, this.posZ - 1, false)) {
               is_block_accessible[0][1] = true;
               is_block_accessible[1][0] = true;
            }
            if (this.world.isAirOrPassableBlock(this.posX + 1, this.posY + dy, this.posZ - 1, false)) {
               is_block_accessible[3][0] = true;
               is_block_accessible[4][1] = true;
            }
         }
         if (this.world.isAirOrPassableBlock(this.posX + 1, this.posY + dy, this.posZ, false)) {
            is_block_accessible[4][2] = true;
            if (this.world.isAirOrPassableBlock(this.posX + 1, this.posY + dy, this.posZ - 1, false)) {
               is_block_accessible[3][0] = true;
               is_block_accessible[4][1] = true;
            }
            if (this.world.isAirOrPassableBlock(this.posX + 1, this.posY + dy, this.posZ + 1, false)) {
               is_block_accessible[4][3] = true;
               is_block_accessible[3][4] = true;
            }
         }
         if (this.world.isAirOrPassableBlock(this.posX, this.posY + dy, this.posZ + 1, false)) {
            is_block_accessible[2][4] = true;
            if (this.world.isAirOrPassableBlock(this.posX + 1, this.posY + dy, this.posZ + 1, false)) {
               is_block_accessible[4][3] = true;
               is_block_accessible[3][4] = true;
            }
            if (this.world.isAirOrPassableBlock(this.posX - 1, this.posY + dy, this.posZ + 1, false)) {
               is_block_accessible[1][4] = true;
               is_block_accessible[0][3] = true;
            }
         }
         if (this.world.isAirOrPassableBlock(this.posX - 1, this.posY + dy, this.posZ, false)) {
            is_block_accessible[0][2] = true;
            if (this.world.isAirOrPassableBlock(this.posX - 1, this.posY + dy, this.posZ + 1, false)) {
               is_block_accessible[1][4] = true;
               is_block_accessible[0][3] = true;
            }
            if (this.world.isAirOrPassableBlock(this.posX - 1, this.posY + dy, this.posZ - 1, false)) {
               is_block_accessible[0][1] = true;
               is_block_accessible[1][0] = true;
            }
         }
         for (int dx = -2; dx <= 2; ++dx) {
            for (int dz = -2; dz <= 2; ++dz) {
               if (!is_block_accessible[2 + dx][2 + dz] || this.world.getBlock(this.posX + dx, this.posY + dy, this.posZ + dz) != Block.bookShelf) continue;
               ++num_bookshelves;
            }
         }
      }
      return num_bookshelves;
   }
}


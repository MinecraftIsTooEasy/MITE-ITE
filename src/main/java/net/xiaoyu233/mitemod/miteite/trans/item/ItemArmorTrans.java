package net.xiaoyu233.mitemod.miteite.trans.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import net.xiaoyu233.fml.util.ReflectHelper;
import net.xiaoyu233.mitemod.miteite.item.ArmorModifierTypes;
import net.xiaoyu233.mitemod.miteite.api.IUpgradableItem;
import net.xiaoyu233.mitemod.miteite.item.ModifierUtils;
import net.xiaoyu233.mitemod.miteite.registry.ITERegistryImpl;
import net.xiaoyu233.mitemod.miteite.util.ItemLevelUpHelper;
import net.xiaoyu233.mitemod.miteite.util.ItemUtil;
import net.xiaoyu233.mitemod.miteite.util.StringUtil;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@Mixin(ItemArmor.class)
public abstract class ItemArmorTrans extends Item implements IDamageableItem, IUpgradableItem {
   @Shadow
   @Final
   public int armorType;
   @Shadow
   protected Material effective_material;

   @Unique
   private final BiFunction<Integer, Boolean, Integer> defaultExp = (level, isWeapon) -> Integer.valueOf(150 + (level.intValue() * 75 * (isWeapon.booleanValue() ? 2 : 1)));

   @Redirect(method = "getTotalArmorProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemArmor;getProtectionAfterDamageFactor(Lnet/minecraft/ItemStack;Lnet/minecraft/EntityLivingBase;)F"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/Item;isArmor()Z")))
   private static float redirectAddTotalDefense(ItemArmor instance, ItemStack item_stack, EntityLivingBase owner, ItemStack[] armors, DamageSource damage_source, boolean include_enchantments, EntityLivingBase ownerPar) {
      float additionalTotalDefense = instance.getProtectionAfterDamageFactor(item_stack, owner);
      if (damage_source != null) {
         if (damage_source.getResponsibleEntity() instanceof EntityLiving && damage_source.getResponsibleEntity().isEntityUndead()) {
            additionalTotalDefense += ArmorModifierTypes.BLESSED_MODIFIER.getModifierValue(item_stack.stackTagCompound);
         }

         additionalTotalDefense += ItemUtil.getProtectionForDamage(item_stack, damage_source, owner);
      }
      return additionalTotalDefense;
   }

   @Inject(method = "addInformation", at = @At("HEAD"))
   public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot, CallbackInfo callbackInfo) {
      int forgingGrade;
      if (itemStack.hasTagCompound()) {
         int toolLevel = itemStack.getTagCompound().getInteger("tool_level");
         if (itemStack.getTagCompound().hasKey("tool_level")) {
            int maxArmorLevel = this.getMaxToolLevel(itemStack);
            if (this.isMaxToolLevel(itemStack)) {
               info.add(I18n.getStringParams("miteite.tool.modifier.maxlevel", maxArmorLevel));
            } else {
               info.add(I18n.getString("miteite.tool.modifier.level") + toolLevel + "/" + maxArmorLevel);
               if (itemStack.getTagCompound().hasKey("tool_exp")) {
                  info.add(I18n.getString("miteite.tool.modifier.exp") + EnumChatFormatting.WHITE + itemStack.getTagCompound().getInteger("tool_exp") + "/" + this.getExpReqForLevel(toolLevel, this.armorType, ReflectHelper.dyCast(this)));
               }
            }
         }

         if (itemStack.getTagCompound().hasKey("forging_grade") && (forgingGrade = itemStack.getTagCompound().getInteger("forging_grade")) != 0) {
            info.add(I18n.getString("miteite.tool.forging.grade") + StringUtil.intToRoman(forgingGrade));
            if (extended_info) {
               info.add(I18n.getString("miteite.tool.forging.exp") + ItemStack.field_111284_a.format(this.getEquipmentExpBounce(itemStack) * 100) + "%");
               info.add(I18n.getString("miteite.tool.forging.armor") + ItemStack.field_111284_a.format(this.getEnhancedProtection(itemStack)));
            }
         }

         if (extended_info) {
            NBTTagCompound compound = itemStack.stackTagCompound.getCompoundTag("modifiers");
            if (!compound.hasNoTags()) {
               info.add(I18n.getString("miteite.tool.modifier.modifiers"));
               for (Object tag : compound.getTags()) {
                  String nbtName = ((NBTBase) tag).getName();
                  ArmorModifierTypes value = ArmorModifierTypes.getByNbtName(nbtName);
                  if (value != null) {
                     info.add("  " + value.color.toString() + value.getDisplayName() + "§r " + StringUtil.intToRoman(compound.getInteger(nbtName)));
                  } else {
                     info.add("  " + EnumChatFormatting.WHITE + nbtName + "§r " + StringUtil.intToRoman(compound.getInteger(nbtName)));
                  }
               }
            }
         }
      }
   }

   @Override
   public void addExpForTool(ItemStack stack, EntityPlayer player, int exp) {
      super.addExpForTool(stack, player, (int) (exp * (this.getEquipmentExpBounce(stack) + 1)) * 2);
   }

   @Unique
   public int getExpReqForLevel(int tool_level, int slotIndexl, ItemArmor armor) {
       int base = ITERegistryImpl.aExpForLevelMap.getOrDefault(this.effective_material, this.defaultExp).apply(Integer.valueOf(tool_level), Boolean.FALSE).intValue();
       return switch (slotIndexl) {
           case 0 -> 2 * base;
           case 1 -> 4 * base;
           case 2 -> 3 * base;
           case 3 -> base;
           default -> 64 * tool_level;
       };
   }

   @Unique
   private float getEnhancedProtection(ItemStack itemStack) {
      return (float) (itemStack.getEnhanceFactor() * (double) this.getRawProtection() * 0.68f + (double) ((float) itemStack.getForgingGrade() / 3.0F)) * (0.75f);
   }

   public int getExpReqForLevel(int i, boolean weapon) {
      return this.getExpReqForLevel(i, this.armorType, ReflectHelper.dyCast(this));
   }

//   @Inject(method = "getMaterialProtection", at = @At("HEAD"), cancellable = true)
//   public void injectVibraniumProtections(CallbackInfoReturnable<Integer> cir) {
//      if (this.effective_material == Materials.vibranium){
//         cir.setReturnValue(15);
//      }
//   }

   @Override
   public int getMaxDamage(ItemStack item_stack) {
      return super.getMaxDamage(item_stack);
   }

   @ModifyReturnValue(method = "getMultipliedProtection", at = @At("RETURN"))
   public final float getMultipliedProtection(float multiplied_protection, ItemStack item_stack) {
      if (item_stack != null && item_stack.stackTagCompound != null) {
         float protection_modifier = ArmorModifierTypes.PROTECTION_MODIFIER.getModifierValue(item_stack.stackTagCompound);
         if (protection_modifier > 0.0F) {
            multiplied_protection += protection_modifier;
         }
      }

      if (item_stack.getForgingGrade() != 0) {
         multiplied_protection += this.getEnhancedProtection(item_stack);
      }

      return multiplied_protection;
   }

   @Shadow
   public abstract float getProtectionAfterDamageFactor(ItemStack itemStack, EntityLivingBase owner);

   @Unique
   private float getRawProtection() {
      return (float)(this.getNumComponentsForDurability() * this.getMaterialProtection()) / 24.0F;
   }

   @Shadow
   public abstract int getRepairCost();

   @Shadow public abstract int getMaterialProtection();

   @Shadow public abstract String getArmorType();

   public boolean hasExpAndLevel() {
      return true;
   }

   public int getMaxToolLevel(ItemStack itemStack) {
      return this.getMaterialForDurability().getMinHarvestLevel() * 3 + itemStack.getForgingGrade();
   }

   public boolean isMaxToolLevel(ItemStack itemStack) {
      return getMaxToolLevel(itemStack) <= this.getToolLevel(itemStack);
   }

   public boolean isWeapon(Item b) {
      return false;
   }

   public void onItemLevelUp(NBTTagCompound tagCompound, EntityPlayer player, ItemStack stack) {
      ItemLevelUpHelper.fireItemLevelUp(stack, player, tagCompound, new ArrayList<>(ModifierUtils.getAllCanBeAppliedArmorModifiers(stack)), true);
      player.suppressNextStatIncrement();
   }
}

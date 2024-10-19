package net.xiaoyu233.mitemod.miteite.events.listener;

import moddedmite.rustedironcore.api.event.listener.ICombatListener;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.ArmorModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.ToolModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.enchantment.Enchantments;
import net.xiaoyu233.mitemod.miteite.util.Configs;

public class PlayerCombatListener implements ICombatListener {
    public float onPlayerBlockReachModify(EntityPlayer player, Block block, int metadata, float original) {
        ItemStack item_stack = player.getHeldItemStack();
        if (item_stack == null) {
            return original;
        }
        int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EXTEND, item_stack);
        return original + (enchantmentLevel * 0.25f);
    }

    public float onPlayerEntityReachModify(EntityPlayer player, EnumEntityReachContext context, Entity entity, float original) {
        ItemStack item_stack = player.getHeldItemStack();
        if (item_stack == null) {
            return original;
        }
        int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EXTEND, item_stack);
        return original + (enchantmentLevel * 0.25f);
    }

    public float onPlayerReceiveKnockBackModify(EntityPlayer player, Entity attacker, float original) {
        ItemStack[] itemStacks = player.getWornItems();
        for (ItemStack stack : itemStacks) {
            if (stack != null) {
                original *= Math.max(1.0f - ArmorModifierTypes.STEADY_MODIFIER.getModifierValue(stack.stackTagCompound), 0.0f);
            }
        }
        return original;
    }

    public float onPlayerRawMeleeDamageModify(EntityPlayer player, Entity target, boolean critical, boolean suspended_in_liquid, float original) {
        ItemStack chestplate;
        float critBouns = 0.0f;
        ItemStack heldItemStack = player.getHeldItemStack();
        int critLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.CRIT, heldItemStack);
        if (critical) {
            critBouns = (float) (critLevel * Configs.Item.Enchantment.CRIT_ENCHANTMENT_DAMAGE_BOOST_PER_LVL.get());
        }
        float indomitableAmp = 1.0f;
        float healthPercent = player.getHealth() / player.getMaxHealth();
        if (healthPercent <= 0.5f && (chestplate = player.getCurrentArmor(1)) != null) {
            float value = ArmorModifierTypes.INDOMITABLE.getModifierValue(chestplate.getTagCompound());
            if (value != 0.0f) {
                indomitableAmp = getIndomitableAmp(healthPercent);
            }
        }
        float demonHunterAmp = 1.0f;
        if (!target.getWorld().isOverworld() && heldItemStack != null) {
            demonHunterAmp = 1.0f + ToolModifierTypes.DEMON_POWER.getModifierValue(heldItemStack.getTagCompound());
        }
        return (critBouns + original) * indomitableAmp * demonHunterAmp;
    }

    private float getIndomitableAmp(float healthPercent) {
        if (healthPercent <= 0.1f) {
            return 2.0f;
        }
        if (healthPercent <= 0.2f) {
            return 1.6f;
        }
        if (healthPercent <= 0.35f) {
            return 1.35f;
        }
        if (healthPercent <= 0.5f) {
            return 1.25f;
        }
        return 1.0f;
    }

    public float onPlayerRawStrVsBlockModify(EntityPlayer player, Item tool, Block block, int metadata, float original) {
        if (tool.isEffectiveAgainstBlock(block, metadata) && (tool instanceof ItemTool)) {
            return getMultipliedHarvestEfficiency((ItemTool) tool, block, player.getHeldItemStack(), player);
        }
        return original;
    }

    private float getMultipliedHarvestEfficiency(ItemTool tool, Block block, ItemStack itemStack, EntityPlayer player) {
        int deltaLevel;
        float commonModifierValue = ToolModifierTypes.EFFICIENCY_MODIFIER.getModifierValue(itemStack.getTagCompound());
        float unnaturalModifierValue = ToolModifierTypes.UNNATURAL_MODIFIER.getModifierValue(itemStack.getTagCompound());
        if (unnaturalModifierValue > 0.0f && (deltaLevel = itemStack.getItem().getMaterialForRepairs().min_harvest_level - block.blockMaterial.min_harvest_level) > 0) {
            commonModifierValue += deltaLevel * unnaturalModifierValue;
        }
        float enhanceModifierValue = (float) (1.0d + itemStack.getEnhanceFactor());
        return (player.isInWater() || player.isInRain()) ? tool.getBaseHarvestEfficiency(block) * (tool.getMaterialHarvestEfficiency() + commonModifierValue + ToolModifierTypes.AQUADYNAMIC_MODIFIER.getModifierValue(itemStack.getTagCompound())) : tool.getBaseHarvestEfficiency(block) * (tool.getMaterialHarvestEfficiency() + commonModifierValue) * enhanceModifierValue;
    }
}


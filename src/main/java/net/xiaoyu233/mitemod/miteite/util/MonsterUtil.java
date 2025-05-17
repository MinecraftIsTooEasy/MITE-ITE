package net.xiaoyu233.mitemod.miteite.util;

import net.minecraft.EnchantmentData;
import net.minecraft.EntityLiving;
import net.minecraft.ItemStack;

import java.util.List;
import java.util.Random;

import static net.minecraft.EnchantmentHelper.getEnchantmentLevelsAlteredByItemEnchantability;

public class MonsterUtil {
    public static void addDefaultArmor(int dayCount, EntityLiving monster, boolean haveAll) {
        if (dayCount > Configs.Entities.ENHANCE_LIMIT.get()) dayCount = Configs.Entities.ENHANCE_LIMIT.get();
        Random rand = monster.getRNG();
        if (rand.nextInt(3 - Math.min(dayCount / 128, 2)) == 0 || dayCount > 365 || haveAll) {
            for (int index = 4; index > 0; --index) {
                if (rand.nextInt(5 - Math.min(dayCount / 32, 4)) == 0 || dayCount > 192 || haveAll) {
                    monster.setCurrentItemOrArmor(index, (new ItemStack(Constant.ARMORS[index - 1][Math.min(getRandomItemTier(rand, dayCount) + (dayCount > 365 ? 1 : 0), Constant.ARMORS[index - 1].length - 1)])).randomizeForMob(monster, dayCount > 64));
                }
            }
        }
    }

    public static ItemStack addRandomEnchantment(Random par0Random, ItemStack itemStack, int enchantment_levels, int maxTotalLevel, int maxEnchantmentNum) {
        enchantment_levels = getEnchantmentLevelsAlteredByItemEnchantability(enchantment_levels, itemStack.getItem());
        if (enchantment_levels >= 1) {
            List<EnchantmentData> enchantmentList = EnchantmentUtil.buildEnchantmentList(par0Random, itemStack, enchantment_levels, maxTotalLevel, maxEnchantmentNum);
            if (enchantmentList != null) {
                for (EnchantmentData enchantment : enchantmentList) {
                    itemStack.addEnchantment(enchantment.enchantmentobj, enchantment.enchantmentLevel);
                }
            }

        }
        return itemStack;
    }

    public static int getRandomItemTier(Random rand, int dayCount) {
        if (dayCount > Configs.Entities.ENHANCE_LIMIT.get()) dayCount = Configs.Entities.ENHANCE_LIMIT.get();
        return getRandomItemTier(rand, Math.min(10, dayCount / 16), rand.nextInt(2 + Math.min(dayCount / 64, 6)) + 1, dayCount);
    }

    public static int getRandomItemTier(Random random, int maxTier, int minTier, int dayCount) {
        if (dayCount > Configs.Entities.ENHANCE_LIMIT.get()) dayCount = Configs.Entities.ENHANCE_LIMIT.get();
        int now = minTier;
        while (now < maxTier && random.nextInt(Math.max(2 * now + 1 - dayCount / 12, 1)) == 0) {
            ++now;
        }
        return now;
    }
}

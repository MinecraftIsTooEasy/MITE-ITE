package net.xiaoyu233.mitemod.miteite.item.enchantment;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

public class EnchantmentCrit extends Enchantment {
    protected EnchantmentCrit(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 5;
    }

    @Override
    public String getNameSuffix() {
        return "crit";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemSword || item instanceof ItemCudgel;
    }

    @Override
    public boolean isOnCreativeTab(CreativeTabs creativeTabs) {
        return creativeTabs == MITEITEItemRegistryInit.tabMITEITE;
    }


}

package net.xiaoyu233.mitemod.miteite.item.enchantment;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import net.xiaoyu233.mitemod.miteite.item.material.Materials;

public class EnchantmentDefence extends Enchantment {
    protected EnchantmentDefence(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 1;
    }

//    @Override
    public float enchantIndividualChance(int enchantmentLevel) {
        return 0.25f + 0.5f * (enchantmentLevel / 158f);
    }

//    @Override
    public boolean enchantIndividually() {
        return true;
    }

    @Override
    public String getNameSuffix() {
        return "defence";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemTool && item.getHardestMetalMaterial() != null && item.getHardestMetalMaterial().getDurability() >= Materials.vibranium.getDurability();
    }

    @Override
    public boolean isOnCreativeTab(CreativeTabs creativeTabs) {
        return creativeTabs == MITEITEItemRegistryInit.tabMITEITE;
    }

}

package net.xiaoyu233.mitemod.miteite.item;

import net.minecraft.Item;
import net.minecraft.Material;
import net.xiaoyu233.mitemod.miteite.registry.ITERegistryImpl;
import net.xiaoyu233.mitemod.miteite.util.Constant;

public class ItemEnhanceStone extends Item {
    private final Material material;

    public ItemEnhanceStone(Material material) {
        this(material, material.getName());
    }

    public ItemEnhanceStone(Material material, String name) {
        super(Constant.getNextItemID(), "enhance_stone/" + name);
        this.setMaterial(material);
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }

    public int getFailChance() {
        return ITERegistryImpl.getEnhanceStoneFailChance(this.material);
    }
}

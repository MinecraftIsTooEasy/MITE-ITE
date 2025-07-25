package net.xiaoyu233.mitemod.miteite.item.material;

import huix.glacier.api.extension.material.*;
import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.Item;
import net.minecraft.ItemCoin;
import net.minecraft.Material;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

public class MaterialVibranium extends Material implements IArmorMaterial, IArrowMaterial, IBowMaterial, IRepairableMaterial, IToolMaterial, ICoinMaterial, IBucketMaterial {

    public MaterialVibranium(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
        this.setMetal(false).setHarmedByLava(false).setMinHarvestLevel(6);
    }

    @Override
    public float getChanceOfRecovery() {
        return 1.0F;
    }

    @Override
    public float getDamageVsEntity() {
        return 9.0F;
    }

    @Override
    public int getProtection() {
        return 15;
    }

    @Override
    public Item getRepairItem() {
        return MITEITEItemRegistryInit.VIBRANIUM_NUGGET;
    }

    @Override
    public float getHarvestEfficiency() {
        return 3.5F;
    }

    @Override
    public int getExperienceValue() {
        return 100000;
    }

    @Override
    public ItemCoin getForInstance() {
        return null;
    }

    @Override
    public Item getNuggetPeer() {
        return MITEITEItemRegistryInit.VIBRANIUM_NUGGET;
    }

    @Override
    public int velocityBonus() {
        return 75;
    }

    @Override
    public int maxDamage() {
        return 512;
    }

    @Override
    public float getMeltingChance() {
        return 0;
    }
}

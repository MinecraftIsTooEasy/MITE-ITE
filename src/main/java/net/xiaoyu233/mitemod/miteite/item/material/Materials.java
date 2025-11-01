package net.xiaoyu233.mitemod.miteite.item.material;

import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.MapColor;
import net.minecraft.Material;

public class Materials extends Material {
    public static final Material vibranium = new MaterialVibranium(EnumEquipmentMaterials.MITE_ITE_VIBRANIUM);

    public Materials(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
    }

    public Materials(String name, MapColor map_color) {
        super(name, map_color);
    }

    public Materials(String name) {
        super(name);
    }
}
package net.xiaoyu233.mitemod.miteite.block;

import net.minecraft.BlockAnvil;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;

public class BlockAnvilMITEITE extends BlockAnvil {
    protected BlockAnvilMITEITE(int par1, Material metal_type) {
        super(par1, metal_type);
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("miteite:anvil/" + this.metal_type.getName() + "/base");
        super.iconArray = new Icon[anvilIconNames.length];

        for(int var2 = 0; var2 < super.iconArray.length; ++var2) {
            super.iconArray[var2] = par1IconRegister.registerIcon("miteite:anvil/" + this.metal_type.getName() + "/" + anvilIconNames[var2]);
        }

    }
}

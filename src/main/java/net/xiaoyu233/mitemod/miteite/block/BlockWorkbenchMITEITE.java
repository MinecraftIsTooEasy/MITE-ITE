package net.xiaoyu233.mitemod.miteite.block;

import moddedmite.rustedironcore.api.block.WorkbenchBlock;
import net.minecraft.Block;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;

public class BlockWorkbenchMITEITE extends WorkbenchBlock {
    private Icon my_workbench_icon_top;

    public BlockWorkbenchMITEITE(int id, Material material, float speedModifier, Material checkAgainst) {
        super(id, material, speedModifier, checkAgainst);
    }

    public Icon getIcon(int side, int metadata) {
        if (side == 0) {
            return Block.planks.getBlockTextureFromSide(side);
        } else if (side == 1) {
            return this.my_workbench_icon_top;
        } else {
            return side != 2 && side != 3 ? this.my_side_icon : this.my_front_icon;
        }
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.my_workbench_icon_top = par1IconRegister.registerIcon("crafting_table_top");
        this.my_front_icon = par1IconRegister.registerIcon("miteite:crafting_table/" + this.getMaterial().getName() + "/front");
        this.my_side_icon = par1IconRegister.registerIcon("miteite:crafting_table/" + this.getMaterial().getName() + "/side");
    }
}

package net.xiaoyu233.mitemod.miteite.block;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEPlayer;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;
import net.xiaoyu233.mitemod.miteite.render.block.SideIconHolder;
import net.xiaoyu233.mitemod.miteite.tileentity.TileEntityForgingTable;
import org.spongepowered.asm.mixin.SoftOverride;

import java.util.*;
import java.util.Locale;
import java.util.stream.Collectors;

public class BlockForgingTable extends Block implements ITileEntityProvider,IBlockWithSubtypes {
    private final BlockSubtypes subtypes = new BlockSubtypes(Arrays.stream(ForgingTableLevel.values()).map(value -> value.name().toLowerCase(Locale.ROOT)).collect(Collectors.toList()).toArray(new String[0]));
    private final Map<Integer, SideIconHolder> textureMap = new HashMap<>();

    protected BlockForgingTable(int par1) {
        super(par1, Material.anvil, new BlockConstants());
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setMaxStackSize(1);
        this.setLightOpacity(0);
    }

    @Override
    public boolean isValidMetadata(int metadata) {
        return 0 <= metadata && metadata < ForgingTableLevel.values().length ;
    }

    @Override
    public int getBlockSubtypeUnchecked(int metadata) {
        return metadata;
    }

    @SoftOverride
    public String getItemDisplayName(ItemStack itemStack) {
        return Translator.get("tile.forging_table." + this.getNames()[itemStack.getItemSubtype()] + ".name");
    }

    @Override
    public String getMetadataNotes() {
        return "metadata is the level of the forging table,min is 0 and max is " + subtypes.getNames().length;
    }


    @Override
    public Icon getIcon(int side, int metadata) {
        SideIconHolder icon = this.textureMap.get(metadata);
        return switch (side) {
            //top
            case 1 -> icon.getTexture_top();
            //bottom
            case 0 -> icon.getTexture_bottom();
            case 2, 3, 5, 4 -> icon.getTexture_side();
            default -> super.getIcon(side, metadata);
        };
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        for (ForgingTableLevel value : ForgingTableLevel.values()) {
            SideIconHolder sideIconHolder = new SideIconHolder();
            sideIconHolder.setTexture_top(par1IconRegister.registerIcon("miteite:forging_table/" + value.name().toLowerCase() + "/top"));
            sideIconHolder.setTexture_bottom(par1IconRegister.registerIcon("miteite:forging_table/" + value.name().toLowerCase() + "/bottom"));
            sideIconHolder.setTexture_side(par1IconRegister.registerIcon("miteite:forging_table/" + value.name().toLowerCase() + "/side"));
            this.textureMap.put(value.ordinal(), sideIconHolder);
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int block_id, int metadata) {
        super.breakBlock(world, x, y, z, block_id, metadata);
        TileEntityForgingTable forgingTable = (TileEntityForgingTable)world.getBlockTileEntity(x, y, z);
        forgingTable.dropAllItems();
        world.removeBlockTileEntity(x, y, z);
    }

    //onBlockDestroyed

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityForgingTable();
    }


    @Override
    public void getItemStacks(int id, CreativeTabs creative_tabs, List list) {
        super.getItemStacks(id, creative_tabs, list);
    }

    public void addItemBlockMaterials(ItemBlock item_block) {
        item_block.addMaterial(Material.iron);
    }

    @Override
    public String[] getTextures() {
        return this.subtypes.getTextures();
    }

    @Override
    public String[] getNames() {
        return this.subtypes.getNames();
    }

    public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
        return true;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        if (!world.isAirOrPassableBlock(x, y + 1, z, false)) {
            return false;
        } else {
            if (player.onServer()) {
                TileEntityForgingTable tile_entity = (TileEntityForgingTable)world.getBlockTileEntity(x, y, z);
                if (tile_entity == null || tile_entity.isUsing()) {
                    return false;
                }

                player.displayGUIForgingTable(x, y, z, tile_entity.getSlots());
            }

            return true;
        }
    }
}

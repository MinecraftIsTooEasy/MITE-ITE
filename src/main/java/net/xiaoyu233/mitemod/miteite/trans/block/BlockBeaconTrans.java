package net.xiaoyu233.mitemod.miteite.trans.block;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEBeacon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockBeacon.class)
public abstract class BlockBeaconTrans extends BlockContainer {
   @Shadow public TileEntity createNewTileEntity(World world) {
      return null;
   }

   protected BlockBeaconTrans(int par1, Material par2Material, BlockConstants constants) {
      super(par1, par2Material, constants);
   }

   @Override
   public void breakBlock(World world, int x, int y, int z, int block_id, int metadata) {
      TileEntityBeacon beacon = (TileEntityBeacon) world.getBlockTileEntity(x, y, z);
      beacon.clearAllPlayersBoost();
      world.removeBlockTileEntity(x, y, z);
   }
}

package net.xiaoyu233.mitemod.miteite.events;

import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.event.listener.IBeaconUpdateHandler;
import moddedmite.rustedironcore.api.event.listener.IEnchantingListener;
import moddedmite.rustedironcore.property.MaterialProperties;
import net.minecraft.ItemStack;
import net.minecraft.TileEntityBeacon;
import net.xiaoyu233.mitemod.miteite.block.Blocks;
import net.xiaoyu233.mitemod.miteite.events.listener.FurnaceExtend;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.item.Materials;

import java.util.Random;

public class MITEITERICEvents extends Handlers {
    public static void register() {
        Enchanting.register(new IEnchantingListener() {
            public int onMaxEnchantNumModify(Random random, ItemStack item_stack, int enchantment_levels, int original) {
                return item_stack.getItem().getHardestMetalMaterial() == Materials.vibranium ? original + 1 : original;
            }
        });
        BeaconUpdate.register(new IBeaconUpdateHandler() {
            public boolean onBlockValidModify(TileEntityBeacon tileEntityBeacon, int blockID, boolean original) {
                return original || blockID == Blocks.blockVibranium.blockID;
            }

            public boolean onItemValidModify(TileEntityBeacon tileEntityBeacon, ItemStack itemStack, boolean original) {
                return original || itemStack.getItem() == Items.VIBRANIUM_INGOT;
            }
        });
        Smelting.register(new FurnaceExtend());
        SpawnCondition.register(new MITEITESpawnConditions());
        PropertiesRegistry.register(() -> {
            MaterialProperties.RepairItem.register(Materials.vibranium, Items.VIBRANIUM_NUGGET);
            MaterialProperties.HarvestEfficiency.register(Materials.vibranium, Float.valueOf(3.5f));
        });
    }
}

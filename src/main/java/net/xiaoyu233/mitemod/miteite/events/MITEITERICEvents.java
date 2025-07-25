package net.xiaoyu233.mitemod.miteite.events;

import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.event.handler.GravelDropHandler;
import moddedmite.rustedironcore.api.event.listener.IBeaconUpdateHandler;
import moddedmite.rustedironcore.api.event.listener.IEnchantingListener;
import moddedmite.rustedironcore.api.event.listener.IFurnaceUpdateListener;
import moddedmite.rustedironcore.property.ItemProperties;
import moddedmite.rustedironcore.property.MaterialProperties;
import net.minecraft.*;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.mitemod.miteite.api.ITEPlugin;
import net.xiaoyu233.mitemod.miteite.block.MITEITEBlockRegistryInit;
import net.xiaoyu233.mitemod.miteite.events.listener.FurnaceExtend;
import net.xiaoyu233.mitemod.miteite.events.listener.PlayerAttributeListener;
import net.xiaoyu233.mitemod.miteite.events.listener.PlayerCombatListener;
import net.xiaoyu233.mitemod.miteite.events.listener.CraftingRegistry;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import net.xiaoyu233.mitemod.miteite.item.material.Materials;
import net.xiaoyu233.mitemod.miteite.registry.ITERegistryImpl;
import net.xiaoyu233.mitemod.miteite.registry.ITEVanillaPlugin;
import net.xiaoyu233.mitemod.miteite.util.Configs;


import java.util.Random;

public class MITEITERICEvents extends Handlers {
    public static void register() {
        FurnaceUpdate.register(new IFurnaceUpdateListener() {
            public int onFurnaceCookTimeTargetModify(TileEntityFurnace tileEntityFurnace, int original) {
                ItemStack stackInSlot = tileEntityFurnace.getStackInSlot(0);
                if (stackInSlot == null) {
                    return original;
                }
                int cookTime = stackInSlot.getItem().getCookTime();
                if (cookTime == 200) {
                    cookTime = original;
                }
                return cookTime / Math.max(tileEntityFurnace.heat_level, 1);
            }
        });
        Enchanting.register(new IEnchantingListener() {
            public int onMaxEnchantNumModify(Random random, ItemStack item_stack, int enchantment_levels, int original) {
                return item_stack.getItem().getHardestMetalMaterial() == Materials.vibranium ? original + 1 : original;
            }
        });
        GravelDrop.registerGravelLootEntry(new GravelDropHandler.GravelLootEntry((
                GravelDropHandler.AdamantiumEntry.weight()) / 3.0f, info -> MITEITEItemRegistryInit.VIBRANIUM_NUGGET.itemID));
        BeaconUpdate.register(new IBeaconUpdateHandler() {
            public boolean onBlockValidModify(TileEntityBeacon tileEntityBeacon, int blockID, boolean original) {
                return original || blockID == MITEITEBlockRegistryInit.blockVibranium.blockID;
            }

            public boolean onItemValidModify(TileEntityBeacon tileEntityBeacon, ItemStack itemStack, boolean original) {
                return original || itemStack.getItem() == MITEITEItemRegistryInit.VIBRANIUM_INGOT;
            }
        });
        SpawnCondition.register(new MITEITESpawnConditions());
        Smelting.register(new FurnaceExtend());
        Crafting.register(new CraftingRegistry());
        PropertiesRegistry.register(() -> {
//            MaterialProperties.RepairItem.register(Materials.vibranium, MITEITEItemRegistryInit.VIBRANIUM_NUGGET);
//            MaterialProperties.HarvestEfficiency.register(Materials.vibranium, Float.valueOf(3.5f));
            ItemProperties.BurnTime.register(MITEITEItemRegistryInit.BLAZE_COAL_POWDER, 6800);
            ItemProperties.HeatLevel.register(MITEITEItemRegistryInit.BLAZE_COAL_POWDER, 5);
            ITERegistryImpl MITEITERegistry = new ITERegistryImpl();
            new ITEVanillaPlugin().register(MITEITERegistry);
            FishModLoader.getEntrypointContainers("mite_ite", ITEPlugin.class).forEach(container -> {
                container.getEntrypoint().register(MITEITERegistry);
            });
        });
        Combat.register(new PlayerCombatListener());
        Trading.register(event -> {
            event.getForProfession(0).ifPresent(x -> {
                x.removeBuyEntryForId(Block.cloth.blockID);
                x.addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5f)) {
                        recipeList.add(new MerchantRecipe(new ItemStack(Block.cloth, Configs.Entities.VILLAGER_WOOL_TO_EMERALD_SHARD_COUNT.get()), new ItemStack(Item.shardEmerald, Configs.Entities.VILLAGER_WOOL_TO_EMERALD_SHARD_SHARD_COUNT.get())));
                    }
                });
            });
        });
        PlayerAttribute.register(new PlayerAttributeListener());
    }

}

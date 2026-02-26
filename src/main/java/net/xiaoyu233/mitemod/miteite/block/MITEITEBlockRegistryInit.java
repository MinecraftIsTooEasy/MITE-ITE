package net.xiaoyu233.mitemod.miteite.block;

import moddedmite.rustedironcore.api.block.WorkbenchBlock;
import moddedmite.rustedironcore.api.event.events.CraftingRecipeRegisterEvent;
import net.minecraft.*;
import net.xiaoyu233.fml.api.block.StrongBoxBlock;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;
import net.xiaoyu233.mitemod.miteite.MITEITEMod;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import net.xiaoyu233.mitemod.miteite.item.material.Materials;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;

import static net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit.VIBRANIUM_INGOT;

public class MITEITEBlockRegistryInit extends Block {
    public static final Block blockForgingTable = new BlockForgingTable(getNextBlockID()).setHardness(8.0F).setResistance(0.875f).setStepSound(Block.soundStoneFootstep).setCreativeTab(MITEITEItemRegistryInit.tabMITEITE);
    public static final BlockAnvil anvilVibranium = (BlockAnvil) new BlockAnvilMITEITE(getNextBlockID(), Materials.vibranium).setCreativeTab(MITEITEItemRegistryInit.tabMITEITE);
    public static final BlockOreStorage blockVibranium = (BlockOreStorage) new BlockOreStorage(getNextBlockID(),Materials.vibranium).setCreativeTab(MITEITEItemRegistryInit.tabMITEITE);
    public static final Block furnaceVibraniumBurning = new BlockFurnaceVibranium(getNextBlockID(), true).setHardness(8.0F).setResistance(0.875f).setStepSound(Block.soundStoneFootstep);
    public static final Block furnaceVibraniumIdle = new BlockFurnaceVibranium(getNextBlockID(), false).setCreativeTab(CreativeTabs.tabDecorations).setHardness(8.0F).setResistance(0.875f).setStepSound(Block.soundStoneFootstep).setCreativeTab(MITEITEItemRegistryInit.tabMITEITE);
    public static final Block netherAdamantiumOre = new BlockNetherAdamantiumOre(getNextBlockID()).setCreativeTab(CreativeTabs.tabBlock).setHardness(4.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreNetherAdamantium").setCreativeTab(MITEITEItemRegistryInit.tabMITEITE);
    public static final Block chestVibranium = new StrongBoxBlock(getNextBlockID(), Materials.vibranium).setStepSound(soundMetalFootstep).setCreativeTab(MITEITEItemRegistryInit.tabMITEITE);
    public static final WorkbenchBlock vibraniumWorkBench = (WorkbenchBlock) new BlockWorkbenchMITEITE(getNextBlockID(), Materials.vibranium, 0.55F, Material.adamantium).setCreativeTab(MITEITEItemRegistryInit.tabMITEITE);

    protected MITEITEBlockRegistryInit(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    public static int getNextBlockID() {
        return IdUtil.getNextBlockID();
    }

    public static void registerItemBlocks(ItemRegistryEvent registryEvent) {
        anvilVibranium.stepSound = Block.soundAnvilFootstep;
        registryEvent.registerAnvil(MITEITEMod.ITENameSpace, "anvil_vibranium", anvilVibranium);
        registryEvent.registerItemBlock(MITEITEMod.ITENameSpace, "miteite:block_vibranium", "block_vibranium", blockVibranium);
        registryEvent.registerItemBlock(MITEITEMod.ITENameSpace,"furnace_vibranium_idle", furnaceVibraniumIdle);
        registryEvent.registerItemBlock(MITEITEMod.ITENameSpace, "furnace_vibranium_burning", furnaceVibraniumBurning);
        registryEvent.registerItemBlock(MITEITEMod.ITENameSpace, "block_forging_table", blockForgingTable);
        registryEvent.registerItemBlock(MITEITEMod.ITENameSpace, "miteite:nether_adamantium_ore", "nether_adamantium_ore", netherAdamantiumOre);
        registryEvent.registerItemBlock(MITEITEMod.ITENameSpace, "vibranium_chest", chestVibranium);
        registryEvent.registerItemBlock(MITEITEMod.ITENameSpace, "toolbench.vibranium", vibraniumWorkBench);
    }

    public static void registerRecipes(CraftingRecipeRegisterEvent register) {
        register.registerShapedRecipe(new ItemStack(anvilVibranium),true,
                "AVA",
                " I ",
                "IaI",
                'A', Item.ingotAdamantium,
                'V', blockVibranium,
                'I', VIBRANIUM_INGOT,
                'a', Block.anvilAncientMetal
        );
        register.registerShapelessRecipe(new ItemStack(blockVibranium),true,
                        VIBRANIUM_INGOT, VIBRANIUM_INGOT, VIBRANIUM_INGOT,
                        VIBRANIUM_INGOT, VIBRANIUM_INGOT, VIBRANIUM_INGOT,
                        VIBRANIUM_INGOT, VIBRANIUM_INGOT, VIBRANIUM_INGOT
        );
        register.registerShapedRecipe(new ItemStack(MITEITEBlockRegistryInit.furnaceVibraniumIdle),true,
                "VOA",
                "DND",
                "AOV",
                'V',VIBRANIUM_INGOT,
                'O',Block.obsidian,
                'D',Item.diamond,
                'A',Item.ingotAdamantium,
                'N', Block.furnaceNetherrackIdle
        );
        register.registerShapedRecipe(new ItemStack(MITEITEBlockRegistryInit.blockForgingTable,1,0),true,
                "WIT",
                " A ",
                "OOO",
                'W',Block.planks,
                'A',Block.anvil,
                'I', MITEITEItemRegistryInit.ingotIron,
                'T',new ItemStack(Block.workbench,1,7),
                'O', MITEITEBlockRegistryInit.obsidian);
        register.registerShapedRecipe(new ItemStack(MITEITEBlockRegistryInit.chestVibranium), true,
                "III",
                "I I",
                "III",
                'I', VIBRANIUM_INGOT);
        registerForgingTableUpgradeRecipes(register,ForgingTableLevel.IRON, Item.ingotMithril);
        registerForgingTableUpgradeRecipes(register,ForgingTableLevel.MITHRIL, Item.ingotAdamantium);
        registerForgingTableUpgradeRecipes(register,ForgingTableLevel.ADAMANTIUM, VIBRANIUM_INGOT);
        FurnaceRecipes.smelting().addSmelting(MITEITEBlockRegistryInit.netherAdamantiumOre.blockID, new ItemStack(Item.ingotAdamantium));
        vibraniumWorkBench.registerSimpleRecipe(register);
    }

    private static void registerForgingTableUpgradeRecipes(CraftingRecipeRegisterEvent register, ForgingTableLevel originalLevel, Item ingot){
        register.registerShapedRecipe(new ItemStack(MITEITEBlockRegistryInit.blockForgingTable, 1, originalLevel.getLevel() + 1), true,
                "III",
                " T ",
                'I', ingot,
                'T', new ItemStack(MITEITEBlockRegistryInit.blockForgingTable, 1, originalLevel.getLevel()));
    }
}

package net.xiaoyu233.mitemod.miteite.item;

import moddedmite.rustedironcore.api.event.events.CraftingRecipeRegisterEvent;
import net.minecraft.*;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.fml.api.item.*;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.util.ReflectHelper;
import net.xiaoyu233.mitemod.miteite.MITEITEMod;
import net.xiaoyu233.mitemod.miteite.block.MITEITEBlockRegistryInit;
import net.xiaoyu233.mitemod.miteite.creativetab.CreativeTabsITE;
import net.xiaoyu233.mitemod.miteite.util.Constant;

@SuppressWarnings("ConstantConditions")
public class MITEITEItemRegistryInit extends Item {
    public static final CreativeTabs tabMITEITE = new CreativeTabsITE();

    public static final Item BLAZE_COAL_POWDER = new ItemBlazeCoalPowder(Constant.getNextItemID()).setCreativeTab(tabMITEITE);
    public static final Item DIAMOND_CHUNK = new Item(Constant.getNextItemID(), Material.diamond,"diamond_chunk").setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(Material.diamond) /(float)4).setCreativeTab(tabMITEITE);
    public static final Item OBSIDIAN_STICK = new Item(Constant.getNextItemID(),Material.obsidian,"obsidian_stick").setCreativeTab(tabMITEITE);
    public static final ItemAxe VIBRANIUM_AXE = (ItemAxe) new AxeItem(Constant.getNextItemID(), Materials.vibranium).setCreativeTab(tabMITEITE);
    public static final ItemBattleAxe VIBRANIUM_BATTLE_AXE = (ItemBattleAxe) new BattleAxeItem(Constant.getNextItemID(), Materials.vibranium).setCreativeTab(tabMITEITE);
    public static final ItemArmor VIBRANIUM_BOOTS = (ItemArmor) new ItemBoots(Constant.getNextItemID(), Materials.vibranium,false).setCreativeTab(tabMITEITE);
    public static final ItemBow VIBRANIUM_BOW = (ItemBow) new ItemBow(Constant.getNextItemID(), Materials.vibranium).setCreativeTab(tabMITEITE);
    public static final ItemArmor VIBRANIUM_CHESTPLATE = (ItemArmor) new ItemCuirass(Constant.getNextItemID(), Materials.vibranium, false).setCreativeTab(tabMITEITE);
    public static final ItemDagger VIBRANIUM_DAGGER = (ItemDagger) ReflectHelper.createInstance(ItemDagger.class, new Class[]{int.class, Material.class}, Constant.getNextItemID(), Materials.vibranium).setCreativeTab(tabMITEITE);
    public static final ItemArmor VIBRANIUM_HELMET = (ItemArmor) new ItemHelmet(Constant.getNextItemID(), Materials.vibranium,false).setCreativeTab(tabMITEITE);
    public static final Item VIBRANIUM_INGOT = new ItemIngot(Constant.getNextItemID(),Materials.vibranium).setCreativeTab(tabMITEITE);
    public static final ItemArmor VIBRANIUM_LEGGINGS = (ItemArmor) new ItemLeggings(Constant.getNextItemID(), Materials.vibranium,false).setCreativeTab(tabMITEITE);
    public static final ItemNugget VIBRANIUM_NUGGET = (ItemNugget) new NuggetItem(Constant.getNextItemID(), Materials.vibranium).setCreativeTab(tabMITEITE);
    public static final ItemPickaxe VIBRANIUM_PICKAXE = (ItemPickaxe) ReflectHelper.createInstance(ItemPickaxe.class, new Class[]{int.class, Material.class}, Constant.getNextItemID(), Materials.vibranium).setCreativeTab(tabMITEITE);
    public static final ItemShovel VIBRANIUM_SHOVEL = (ItemShovel) ReflectHelper.createInstance(ItemShovel.class, new Class[]{int.class, Material.class}, Constant.getNextItemID(), Materials.vibranium).setCreativeTab(tabMITEITE);
    public static final ItemSword VIBRANIUM_SWORD = (ItemSword) ReflectHelper.createInstance(ItemSword.class, new Class[]{int.class, Material.class}, Constant.getNextItemID(), Materials.vibranium).setCreativeTab(tabMITEITE);
    public static final ItemWarHammer VIBRANIUM_WAR_HAMMER = (ItemWarHammer) ReflectHelper.createInstance(ItemWarHammer.class, new Class[]{int.class, Material.class}, Constant.getNextItemID(), Materials.vibranium).setCreativeTab(tabMITEITE);
    public static final ItemEnhanceStone IRON_ENHANCE_STONE = (ItemEnhanceStone) new ItemEnhanceStone(ItemEnhanceStone.Types.iron).setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(Material.iron) * 2f).setCreativeTab(tabMITEITE);
    public static final ItemEnhanceStone MITHRIL_ENHANCE_STONE = (ItemEnhanceStone) new ItemEnhanceStone(ItemEnhanceStone.Types.mithril).setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(Material.mithril) * 2f).setCreativeTab(tabMITEITE);
    public static final ItemEnhanceStone ADAMANTIUM_ENHANCE_STONE = (ItemEnhanceStone) new ItemEnhanceStone(ItemEnhanceStone.Types.adamantium).setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(Material.diamond) * 2f).setCreativeTab(tabMITEITE);
    public static final ItemEnhanceStone UNIVERSAL_ENHANCE_STONE = (ItemEnhanceStone) new ItemEnhanceStone(ItemEnhanceStone.Types.universal).setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(Material.ender_pearl) * 2f).setCreativeTab(tabMITEITE);

    public static void registerItems(ItemRegistryEvent event) {
        event.register(MITEITEMod.ITENameSpace, "miteite:obsidian_stick", "obsidian_stick", OBSIDIAN_STICK);
        event.register(MITEITEMod.ITENameSpace, "miteite:ingots/vibranium", "vibranium", VIBRANIUM_INGOT);
        event.register(MITEITEMod.ITENameSpace, "miteite:nuggets/vibranium", "vibranium_nugget", VIBRANIUM_NUGGET);
        event.register(MITEITEMod.ITENameSpace, "miteite:armor/vibranium_helmet", "vibranium_helmet", VIBRANIUM_HELMET);
        event.register(MITEITEMod.ITENameSpace, "miteite:armor/vibranium_chestplate", "vibranium_chestplate", VIBRANIUM_CHESTPLATE);
        event.register(MITEITEMod.ITENameSpace, "miteite:armor/vibranium_leggings", "vibranium_leggings", VIBRANIUM_LEGGINGS);
        event.register(MITEITEMod.ITENameSpace, "miteite:armor/vibranium_boots", "vibranium_boots", VIBRANIUM_BOOTS);
        event.register(MITEITEMod.ITENameSpace, "miteite:tools/vibranium_sword", "vibranium_sword", VIBRANIUM_SWORD);
        event.register(MITEITEMod.ITENameSpace, "miteite:tools/vibranium_shovel", "vibranium_shovel", VIBRANIUM_SHOVEL);
        event.register(MITEITEMod.ITENameSpace, "miteite:tools/vibranium_pickaxe", "vibranium_pickaxe", VIBRANIUM_PICKAXE);
        event.register(MITEITEMod.ITENameSpace, "miteite:tools/vibranium_axe", "vibranium_axe", VIBRANIUM_AXE);
        event.register(MITEITEMod.ITENameSpace, "miteite:tools/vibranium_dagger", "vibranium_dagger", VIBRANIUM_DAGGER);
        event.register(MITEITEMod.ITENameSpace, "miteite:tools/vibranium_war_hammer", "vibranium_war_hammer", VIBRANIUM_WAR_HAMMER);
        event.register(MITEITEMod.ITENameSpace, "miteite:tools/vibranium_battle_axe", "vibranium_battle_axe", VIBRANIUM_BATTLE_AXE);
        event.register(MITEITEMod.ITENameSpace, "miteite:diamond_chunk", "diamond_chunk", DIAMOND_CHUNK);
        event.register(MITEITEMod.ITENameSpace, "miteite:blaze_coal_powder", "blaze_coal_powder", BLAZE_COAL_POWDER);
        event.register(MITEITEMod.ITENameSpace, "miteite:bows/vibranium/", "bows/vibranium/", VIBRANIUM_BOW).setUnlocalizedName("vibranium_bow");
        event.register(MITEITEMod.ITENameSpace, "miteite:enhance_stone/iron", "enhance_stone/iron", IRON_ENHANCE_STONE);
        event.register(MITEITEMod.ITENameSpace, "miteite:enhance_stone/mithril", "enhance_stone/mithril", MITHRIL_ENHANCE_STONE);
        event.register(MITEITEMod.ITENameSpace, "miteite:enhance_stone/adamantium", "enhance_stone/adamantium", ADAMANTIUM_ENHANCE_STONE);
        event.register(MITEITEMod.ITENameSpace, "miteite:enhance_stone/universal", "enhance_stone/universal", UNIVERSAL_ENHANCE_STONE);
        Constant.initItemArray();
    }

    public static void registerRecipes(CraftingRecipeRegisterEvent register) {
        register.registerShapedRecipe(new ItemStack(OBSIDIAN_STICK), true, "#", "#", '#', Block.obsidian);
        if (!FishModLoader.hasMod("extreme")) {
            register.registerShapedRecipe(new ItemStack(VIBRANIUM_INGOT),
                    false,
                    "NIN",
                    "IDI",
                    "NIN",
                    'N',
                    Item.mithrilNugget,
                    'I',
                    Item.ingotAdamantium,
                    'D',
                    Item.diamond);
        }
        register.registerShapelessRecipe(new ItemStack(VIBRANIUM_NUGGET, 9), true, VIBRANIUM_INGOT);
        register.registerShapelessRecipe(new ItemStack(VIBRANIUM_INGOT),
                true,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET);
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_HELMET),
                true,
                "#A#",
                "# #",
                '#', VIBRANIUM_INGOT,
                'A', Item.helmetAdamantium).extendsNBT().keepQuality();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_CHESTPLATE),
                true,
                "# #",
                "#A#",
                "###",
                '#', VIBRANIUM_INGOT,
                'A', Item.plateAdamantium).extendsNBT().keepQuality();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_LEGGINGS),
                true,
                "#A#",
                "# #",
                "# #",
                '#', VIBRANIUM_INGOT,
                'A', Item.legsAdamantium).extendsNBT().keepQuality();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_BOOTS),
                true,
                "A #",
                "# #",
                '#', VIBRANIUM_INGOT,
                'A', Item.bootsAdamantium).extendsNBT().keepQuality();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_PICKAXE),
                true,
                "###",
                " A ",
                " S ",
                '#', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', pickaxeAncientMetal);
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_SHOVEL),
                true,
                "#",
                "A",
                "S",
                '#', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', shovelAncientMetal).extendsNBT().keepQuality();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_SWORD),
                true,
                " # ",
                " # ",
                " A ",
                '#', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', swordAncientMetal).extendsNBT().keepQuality();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_AXE),
                true,
                "##",
                "A#",
                "S ",
                '#', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', axeAncientMetal).extendsNBT().keepQuality();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_DAGGER),
                true,
                "I",
                "A",
                'I', VIBRANIUM_INGOT,
                'A', daggerAncientMetal).extendsNBT().keepQuality();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_WAR_HAMMER),
                true,
                "III",
                "IAI",
                " S ",
                'I', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', warHammerAncientMetal).extendsNBT().keepQuality();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_BATTLE_AXE),
                true,
                "I I",
                "IAI",
                " S ",
                'I', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', battleAxeAncientMetal).extendsNBT().keepQuality();
        register.registerShapelessRecipe(new ItemStack(VIBRANIUM_INGOT, 9), true, MITEITEBlockRegistryInit.blockVibranium);
        register.registerShapelessRecipe(new ItemStack(Item.diamond),
                false,
                DIAMOND_CHUNK,
                DIAMOND_CHUNK,
                DIAMOND_CHUNK,
                DIAMOND_CHUNK);
        register.registerShapedRecipe(new ItemStack(BLAZE_COAL_POWDER),
                true,
                " B ",
                "BCB",
                " B ",
                'B', Item.blazePowder,
                'C', Item.coal);
        FurnaceRecipes.smelting().addSmelting(Block.coalBlock.blockID, new ItemStack(DIAMOND_CHUNK));
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_BOW),
                true,
                "NSL",
                "AVL",
                "NSL",
                'S', OBSIDIAN_STICK,
                'V', VIBRANIUM_INGOT,
                'L', Item.silk,
                'N', VIBRANIUM_NUGGET,
                'A', bowAncientMetal).extendsNBT().keepQuality();
        register.registerShapedRecipe(new ItemStack(Item.emerald),
                true,
                "SS",
                "SS",
                'S', Item.shardEmerald);
        register.registerShapelessRecipe(new ItemStack(Item.shardEmerald, 4), true, Item.emerald);
        register.registerShapedRecipe(new ItemStack(IRON_ENHANCE_STONE),
                true,
                " C ",
                "SIS",
                " C ",
                'I', Item.ingotIron,
                'C', Item.ingotCopper,
                'S', Item.ingotSilver);
        register.registerShapedRecipe(new ItemStack(MITHRIL_ENHANCE_STONE),
                true,
                " I ",
                "GMG",
                " I ",
                'I', Item.ingotIron,
                'G', Item.ingotGold,
                'M', Item.ingotMithril);
        register.registerShapedRecipe(new ItemStack(ADAMANTIUM_ENHANCE_STONE),
                true,
                " D ",
                "MAM",
                " R ",
                'R', Block.blockRedstone,
                'D', Item.diamond,
                'M', Item.ingotMithril,
                'A', Item.ingotAdamantium);
        Item.ghastTear.setCraftingDifficultyAsComponent(5);
        register.registerShapedRecipe(new ItemStack(UNIVERSAL_ENHANCE_STONE),
                true,
                "gDg",
                "bTb",
                "BEB",
                'g', Item.glowstone,
                'D', Item.bottleOfDisenchanting,
                'b', Item.blazePowder,
                'T', Item.ghastTear,
                'B', Item.book,
                'E', Item.enderPearl);
    }
}

package net.xiaoyu233.mitemod.miteite.item.recipe;

import com.google.common.base.Objects;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import net.xiaoyu233.mitemod.miteite.item.Materials;

import java.util.function.Consumer;

public class ForgingTableRecipes {
//    private static final BiMap<RecipeKey,ForgingRecipe> RECIPES = HashBiMap.create();
//    private static final Consumer<ForgingRecipe> regiseterer = forgingRecipe -> {
//        RecipeKey key = new RecipeKey(forgingRecipe.getMaterial(),forgingRecipe.getLevelToUpgrade());
//        RECIPES.put(key,forgingRecipe);
//    };
//
//    public static ForgingRecipe getRecipe(Material toolItem, int forgingLevel){
//        return RECIPES.get(new RecipeKey(toolItem,forgingLevel));
//    }
//
//    private static void registerThreeToSix(Material material, ForgingTableLevel level){
//        ForgingRecipe.Builder.of(material,4,level).
//                setChanceOfFailure(Items.MITHRIL_ENHANCE_STONE.getFailChance()).
//                setAxeDurabilityCost(4096).
//                setHammerDurabilityCost(4096).
//                setTimeReq(60 * 20).
//                setQualityReward(EnumQuality.excellent).
//                addFaultFeedback(DowngradeFeedback.of(1)).
//                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
//                addMaterials(new ItemStack(Items.ingotGold,1),
//                        new ItemStack(Items.ingotMithril,1),
//                        new ItemStack(Items.redstone,1),
//                        new ItemStack(Items.MITHRIL_ENHANCE_STONE,1)).
//                build(regiseterer);
//        ForgingRecipe.Builder.of(material,5,level).
//                setChanceOfFailure(Items.MITHRIL_ENHANCE_STONE.getFailChance()).
//                setAxeDurabilityCost(4096).
//                setHammerDurabilityCost(6144).
//                setTimeReq(70 * 20).
//                setQualityReward(EnumQuality.superb).
//                addFaultFeedback(DowngradeFeedback.of(1)).
//                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
//                addMaterials(new ItemStack(Items.ingotGold,2),
//                        new ItemStack(Items.ingotMithril,1),
//                        new ItemStack(Items.redstone,2),
//                        new ItemStack(Items.MITHRIL_ENHANCE_STONE,1)).
//                build(regiseterer);
//        ForgingRecipe.Builder.of(material,6,level).
//                setChanceOfFailure(Items.MITHRIL_ENHANCE_STONE.getFailChance()).
//                setAxeDurabilityCost(6144).
//                setHammerDurabilityCost(6144).
//                setTimeReq(80 * 20).
//                setQualityReward(EnumQuality.superb).
//                addFaultFeedback(DowngradeFeedback.of(1)).
//                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
//                addMaterials(new ItemStack(Items.ingotGold,2),
//                        new ItemStack(Items.ingotMithril,2),
//                        new ItemStack(Block.blockRedstone,1),
//                        new ItemStack(Items.MITHRIL_ENHANCE_STONE,1)).
//                build(regiseterer);
//
//    }
//
//    public static void registerAll(){
//        //Iron Ancient metal Mithril
//        registerZeroToTwoRecipes(Material.iron,ForgingTableLevel.IRON);
//        registerZeroToTwoRecipes(Material.ancient_metal,ForgingTableLevel.MITHRIL);
//        registerZeroToFourRecipes(Material.mithril,ForgingTableLevel.MITHRIL);
//        //Vibranium
//        {
//            registerZeroToThreeRecipes(Materials.vibranium,ForgingTableLevel.VIBRANIUM);
//            registerThreeToSix(Materials.vibranium,ForgingTableLevel.VIBRANIUM);
//            ForgingRecipe.Builder.of(Materials.vibranium,7,ForgingTableLevel.VIBRANIUM).
//                    setChanceOfFailure(Items.ADAMANTIUM_ENHANCE_STONE.getFailChance()).
//                    setAxeDurabilityCost(6144).
//                    setHammerDurabilityCost(8192).
//                    setTimeReq(90 * 20).
//                    setQualityReward(EnumQuality.masterwork).
//                    addFaultFeedback(DowngradeFeedback.of(1)).
//                    addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).
//                    addMaterials(new ItemStack(Items.ghastTear,1),
//                            new ItemStack(Items.ingotAdamantium,1),
//                            new ItemStack(Item.ingotMithril,2),
//                            new ItemStack(Items.ADAMANTIUM_ENHANCE_STONE,1)).
//                    build(regiseterer);
//            ForgingRecipe.Builder.of(Materials.vibranium,8,ForgingTableLevel.VIBRANIUM).
//                    setChanceOfFailure(Items.ADAMANTIUM_ENHANCE_STONE.getFailChance()).
//                    setAxeDurabilityCost(8192).
//                    setHammerDurabilityCost(10240).
//                    setTimeReq(100 * 20).
//                    setQualityReward(EnumQuality.masterwork).
//                    addFaultFeedback(DowngradeFeedback.of(1)).
//                    addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).
//                    addMaterials(new ItemStack(Items.ghastTear,2),
//                            new ItemStack(Items.ingotAdamantium,1),
//                            new ItemStack(Item.ingotMithril,2),
//                            new ItemStack(Items.ADAMANTIUM_ENHANCE_STONE,1)).
//                    build(regiseterer);
//            ForgingRecipe.Builder.of(Materials.vibranium,9,ForgingTableLevel.VIBRANIUM).
//                    setChanceOfFailure(Items.ADAMANTIUM_ENHANCE_STONE.getFailChance()).
//                    setAxeDurabilityCost(8192).
//                    setHammerDurabilityCost(10240).
//                    setTimeReq(110 * 20).
//                    setQualityReward(EnumQuality.legendary).
//                    addFaultFeedback(DowngradeFeedback.of(1)).
//                    addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).
//                    addMaterials(new ItemStack(Items.ghastTear,2),
//                            new ItemStack(Items.ingotAdamantium,2),
//                            new ItemStack(Item.ingotMithril,2),
//                            new ItemStack(Items.ADAMANTIUM_ENHANCE_STONE,1)).
//                    build(regiseterer);
//        }
//        //Adamantium
//        {
//            registerZeroToThreeRecipes(Material.adamantium,ForgingTableLevel.ADAMANTIUM);
//            registerThreeToSix(Material.adamantium,ForgingTableLevel.ADAMANTIUM);
//        }
//    }
//
//    private static void registerZeroToFourRecipes(Material material,ForgingTableLevel level){
//        registerZeroToThreeRecipes(material, level);
//        ForgingRecipe.Builder.of(material,4,level).
//                setChanceOfFailure(Items.MITHRIL_ENHANCE_STONE.getFailChance()).
//                setAxeDurabilityCost(4096).
//                setHammerDurabilityCost(4096).
//                setTimeReq(60 * 20).
//                setQualityReward(EnumQuality.excellent).
//                addFaultFeedback(DowngradeFeedback.of(1)).
//                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
//                addMaterials(new ItemStack(Items.ingotGold,1),
//                        new ItemStack(Items.ingotMithril,1),
//                        new ItemStack(Items.redstone,1),
//                        new ItemStack(Items.MITHRIL_ENHANCE_STONE,1)).
//                build(regiseterer);
//    }
//
//    public static void registerZeroToThreeRecipes(Material material, ForgingTableLevel level){
//        registerZeroToTwoRecipes(material,level);
//        ForgingRecipe.Builder.of(material,3,level).
//                setChanceOfFailure(Items.IRON_ENHANCE_STONE.getFailChance()).
//                setAxeDurabilityCost(3072).
//                setHammerDurabilityCost(4096).
//                setTimeReq(50 * 20).
//                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
//                setQualityReward(EnumQuality.excellent).
//                addMaterials(new ItemStack(Items.ingotGold,2),
//                        new ItemStack(Items.ingotIron,2),
//                        new ItemStack(Items.IRON_ENHANCE_STONE,2)).
//                build(regiseterer);
//
//    }
//
//    private static void registerZeroToTwoRecipes(Material material, ForgingTableLevel level){
//        ForgingRecipe.Builder.of(material,0,level).
//                setChanceOfFailure(Items.IRON_ENHANCE_STONE.getFailChance()).
//                setAxeDurabilityCost(2048).
//                setHammerDurabilityCost(2048).
//                setTimeReq(20 * 20).
//                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
//                addMaterials(new ItemStack(Items.ingotGold,1),
//                        new ItemStack(Items.ingotIron,1),
//                        new ItemStack(Items.IRON_ENHANCE_STONE,1)).
//                build(regiseterer);
//        ForgingRecipe.Builder.of(material,1,level).
//                setChanceOfFailure(Items.IRON_ENHANCE_STONE.getFailChance()).
//                setAxeDurabilityCost(2048).
//                setHammerDurabilityCost(3072).
//                setTimeReq(30 * 20).
//                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
//                setQualityReward(EnumQuality.fine).
//                addMaterials(new ItemStack(Items.ingotGold,1),
//                        new ItemStack(Items.ingotIron,2),
//                        new ItemStack(Items.IRON_ENHANCE_STONE,1)).
//                build(regiseterer);
//        ForgingRecipe.Builder.of(material,2,level).
//                setChanceOfFailure(Items.IRON_ENHANCE_STONE.getFailChance()).
//                setAxeDurabilityCost(3072).
//                setHammerDurabilityCost(3072).
//                setTimeReq(40 * 20).
//                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
//                setQualityReward(EnumQuality.fine).
//                addMaterials(new ItemStack(Items.ingotGold,2),
//                        new ItemStack(Items.ingotIron,2),
//                        new ItemStack(Items.IRON_ENHANCE_STONE,1)).
//                build(regiseterer);
//    }
//
//    public static class RecipeKey{
//        private final Material toolItem;
//        private final int levelToUpgrade;
//
//        public RecipeKey(Material toolItem, int levelToUpgrade) {
//            this.toolItem = toolItem;
//            this.levelToUpgrade = levelToUpgrade;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            RecipeKey key = (RecipeKey) o;
//            return levelToUpgrade == key.levelToUpgrade && Objects.equal(toolItem, key.toolItem);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hashCode(toolItem, levelToUpgrade);
//        }
//    }

    private static final BiMap<RecipeKey, ForgingRecipe> RECIPES = HashBiMap.create();
    private static final Consumer<ForgingRecipe> registerer = forgingRecipe -> {
        RecipeKey key = new RecipeKey(forgingRecipe.getMaterial(), forgingRecipe.getLevelToUpgrade());
        RECIPES.put(key, forgingRecipe);
    };

    public static ForgingRecipe getRecipe(Material toolItem, int forgingLevel) {
        return (ForgingRecipe) RECIPES.get(new RecipeKey(toolItem, forgingLevel));
    }

    public static void registerForgingRecipe(Material material, ForgingTableLevel level, int maxLevel) {
        if (maxLevel >= 0) {
            ForgingRecipe.Builder.of(material, 0, level).setChanceOfFailure(MITEITEItemRegistryInit.IRON_ENHANCE_STONE.getFailChance()).setAxeDurabilityCost(2048).setHammerDurabilityCost(2048).setTimeReq(400).addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).addMaterials(new ItemStack(MITEITEItemRegistryInit.ingotGold, 1), new ItemStack(MITEITEItemRegistryInit.ingotIron, 1), new ItemStack(MITEITEItemRegistryInit.IRON_ENHANCE_STONE, 1)).build(registerer);
        }
        if (maxLevel >= 1) {
            ForgingRecipe.Builder.of(material, 1, level).setChanceOfFailure(MITEITEItemRegistryInit.IRON_ENHANCE_STONE.getFailChance()).setAxeDurabilityCost(2048).setHammerDurabilityCost(3072).setTimeReq(600).addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).setQualityReward(EnumQuality.fine).addMaterials(new ItemStack(MITEITEItemRegistryInit.ingotGold, 1), new ItemStack(MITEITEItemRegistryInit.ingotIron, 2), new ItemStack(MITEITEItemRegistryInit.IRON_ENHANCE_STONE, 1)).build(registerer);
        }
        if (maxLevel >= 2) {
            ForgingRecipe.Builder.of(material, 2, level).setChanceOfFailure(MITEITEItemRegistryInit.IRON_ENHANCE_STONE.getFailChance()).setAxeDurabilityCost(3072).setHammerDurabilityCost(3072).setTimeReq(800).addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).setQualityReward(EnumQuality.fine).addMaterials(new ItemStack(MITEITEItemRegistryInit.ingotGold, 2), new ItemStack(MITEITEItemRegistryInit.ingotIron, 2), new ItemStack(MITEITEItemRegistryInit.IRON_ENHANCE_STONE, 1)).build(registerer);
        }
        if (maxLevel >= 3) {
            ForgingRecipe.Builder.of(material, 3, level).setChanceOfFailure(MITEITEItemRegistryInit.IRON_ENHANCE_STONE.getFailChance()).setAxeDurabilityCost(3072).setHammerDurabilityCost(4096).setTimeReq(1000).addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).setQualityReward(EnumQuality.excellent).addMaterials(new ItemStack(MITEITEItemRegistryInit.ingotGold, 2), new ItemStack(MITEITEItemRegistryInit.ingotIron, 2), new ItemStack(MITEITEItemRegistryInit.IRON_ENHANCE_STONE, 2)).build(registerer);
        }
        if (maxLevel >= 4) {
            ForgingRecipe.Builder.of(material, 4, level).setChanceOfFailure(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE.getFailChance()).setAxeDurabilityCost(4096).setHammerDurabilityCost(4096).setTimeReq(1200).setQualityReward(EnumQuality.excellent).addFaultFeedback(DowngradeFeedback.of(1)).addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).addMaterials(new ItemStack(MITEITEItemRegistryInit.ingotGold, 1), new ItemStack(MITEITEItemRegistryInit.ingotMithril, 1), new ItemStack(MITEITEItemRegistryInit.redstone, 1), new ItemStack(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE, 1)).build(registerer);
        }
        if (maxLevel >= 5) {
            ForgingRecipe.Builder.of(material, 5, level).setChanceOfFailure(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE.getFailChance()).setAxeDurabilityCost(4096).setHammerDurabilityCost(6144).setTimeReq(1400).setQualityReward(EnumQuality.superb).addFaultFeedback(DowngradeFeedback.of(1)).addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).addMaterials(new ItemStack(MITEITEItemRegistryInit.ingotGold, 2), new ItemStack(MITEITEItemRegistryInit.ingotMithril, 1), new ItemStack(MITEITEItemRegistryInit.redstone, 2), new ItemStack(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE, 1)).build(registerer);
        }
        if (maxLevel >= 6) {
            ForgingRecipe.Builder.of(material, 6, level).setChanceOfFailure(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE.getFailChance()).setAxeDurabilityCost(6144).setHammerDurabilityCost(6144).setTimeReq(1600).setQualityReward(EnumQuality.superb).addFaultFeedback(DowngradeFeedback.of(1)).addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).addMaterials(new ItemStack(MITEITEItemRegistryInit.ingotGold, 2), new ItemStack(MITEITEItemRegistryInit.ingotMithril, 2), new ItemStack(Block.blockRedstone, 1), new ItemStack(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE, 1)).build(registerer);
        }
        if (maxLevel >= 7) {
            ForgingRecipe.Builder.of(Materials.vibranium, 7, ForgingTableLevel.VIBRANIUM).setChanceOfFailure(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE.getFailChance()).setAxeDurabilityCost(6144).setHammerDurabilityCost(8192).setTimeReq(1800).setQualityReward(EnumQuality.masterwork).addFaultFeedback(DowngradeFeedback.of(1)).addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).addMaterials(new ItemStack(MITEITEItemRegistryInit.ghastTear, 1), new ItemStack(MITEITEItemRegistryInit.ingotAdamantium, 1), new ItemStack(Item.ingotMithril, 2), new ItemStack(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE, 1)).build(registerer);
        }
        if (maxLevel >= 8) {
            ForgingRecipe.Builder.of(Materials.vibranium, 8, ForgingTableLevel.VIBRANIUM).setChanceOfFailure(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE.getFailChance()).setAxeDurabilityCost(8192).setHammerDurabilityCost(10240).setTimeReq(2000).setQualityReward(EnumQuality.masterwork).addFaultFeedback(DowngradeFeedback.of(1)).addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).addMaterials(new ItemStack(MITEITEItemRegistryInit.ghastTear, 2), new ItemStack(MITEITEItemRegistryInit.ingotAdamantium, 1), new ItemStack(Item.ingotMithril, 2), new ItemStack(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE, 1)).build(registerer);
        }
        if (maxLevel >= 9) {
            ForgingRecipe.Builder.of(Materials.vibranium, 9, ForgingTableLevel.VIBRANIUM).setChanceOfFailure(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE.getFailChance()).setAxeDurabilityCost(8192).setHammerDurabilityCost(10240).setTimeReq(2200).setQualityReward(EnumQuality.legendary).addFaultFeedback(DowngradeFeedback.of(1)).addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).addMaterials(new ItemStack(MITEITEItemRegistryInit.ghastTear, 2), new ItemStack(MITEITEItemRegistryInit.ingotAdamantium, 2), new ItemStack(Item.ingotMithril, 2), new ItemStack(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE, 1)).build(registerer);
        }
    }

    public static class RecipeKey {
        private final Material toolItem;
        private final int levelToUpgrade;

        public RecipeKey(Material toolItem, int levelToUpgrade) {
            this.toolItem = toolItem;
            this.levelToUpgrade = levelToUpgrade;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            RecipeKey key = (RecipeKey) o;
            return this.levelToUpgrade == key.levelToUpgrade && Objects.equal(this.toolItem, key.toolItem);
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{this.toolItem, Integer.valueOf(this.levelToUpgrade)});
        }
    }
}

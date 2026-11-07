package net.xiaoyu233.mitemod.miteite.registry;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.MITEITEMod;
import net.xiaoyu233.mitemod.miteite.api.ITEPlugin;
import net.xiaoyu233.mitemod.miteite.api.ITERegistry;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingSlot;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingListener;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingRecipeProvider;
import net.xiaoyu233.mitemod.miteite.item.ArmorModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import net.xiaoyu233.mitemod.miteite.item.ToolModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.material.Materials;
import net.xiaoyu233.mitemod.miteite.item.recipe.DefaultForgingListener;
import net.xiaoyu233.mitemod.miteite.item.recipe.DowngradeFeedback;
import net.xiaoyu233.mitemod.miteite.item.recipe.DurabilityFeedback;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingRecipe;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableRecipes;

public class ITEVanillaPlugin implements ITEPlugin {
    private static final IForgingListener DEFAULT_FORGING_LISTENER = new DefaultForgingListener();
    private static final IForgingRecipeProvider DEFAULT_FORGING_RECIPE_PROVIDER = context -> {
        ItemStack tool = context.getToolStack();
        if (tool == null) {
            return null;
        }
        return ForgingTableRecipes.getRecipe(tool.getItem().getMaterialForRepairs(), tool.getForgingGrade());
    };

    @Override
    public void register(ITERegistry registry) {
        registry.registerWeaponCriteria(item -> item instanceof ItemSword || item instanceof ItemBattleAxe || item instanceof ItemWarHammer);
        registry.registerMeatCriteria(item -> item instanceof ItemMeat);

        registry.registerExpForLevel(Material.copper, 100, 50);
        registry.registerExpForLevel(Material.silver, 100, 50);
        registry.registerExpForLevel(Material.gold, 125, 60);
        registry.registerExpForLevel(Material.iron, 150, 75);
        registry.registerExpForLevel(Material.ancient_metal, 150, 75);
        registry.registerExpForLevel(Material.mithril, 200, 100);
        registry.registerExpForLevel(Material.adamantium, 200, 120);
        registry.registerExpForLevel(Materials.vibranium, 200, 150);

        this.registerForgingContent(registry);

        registry.registerArmorModifier(ArmorModifierTypes.DURABILITY_MODIFIER);
        registry.registerArmorModifier(ArmorModifierTypes.PROJECTILE_PROTECTION_MODIFIER);
        registry.registerArmorModifier(ArmorModifierTypes.EXPLOSION_PROTECTION_MODIFIER);
        registry.registerArmorModifier(ArmorModifierTypes.FIRE_PROTECTION_MODIFIER);
        registry.registerArmorModifier(ArmorModifierTypes.PROTECTION_MODIFIER);
        registry.registerArmorModifier(ArmorModifierTypes.STEADY_MODIFIER);
        registry.registerArmorModifier(ArmorModifierTypes.BLESSED_MODIFIER);
        registry.registerArmorModifier(ArmorModifierTypes.INDOMITABLE);

        registry.registerToolModifier(ToolModifierTypes.EFFICIENCY_MODIFIER);
        registry.registerToolModifier(ToolModifierTypes.AQUADYNAMIC_MODIFIER);
        registry.registerToolModifier(ToolModifierTypes.DURABILITY_MODIFIER);
        registry.registerToolModifier(ToolModifierTypes.DAMAGE_MODIFIER);
        registry.registerToolModifier(ToolModifierTypes.SLOWDOWN_MODIFIER);
        registry.registerToolModifier(ToolModifierTypes.UNNATURAL_MODIFIER);
        registry.registerToolModifier(ToolModifierTypes.DEMON_POWER);
        registry.registerToolModifier(ToolModifierTypes.GEOLOGY);
    }

    private void registerForgingContent(ITERegistry registry) {
        registry.registerEnhanceStone(Material.iron, 35);
        registry.registerEnhanceStone(Material.mithril, 55);
        registry.registerEnhanceStone(Material.adamantium, 65);
        registry.registerEnhanceStone(Material.glowstone, 50);

        ResourceLocation slot = new ResourceLocation(MITEITEMod.ITEID, "textures/gui/container/forging_table/slot.png");

        registry.registerForgingSlot(new ResourceLocation(MITEITEMod.ITEID, "forging/up"), ForgingSlot.MATERIAL, 42, 6, slot, stack -> true);
        registry.registerForgingSlot(new ResourceLocation(MITEITEMod.ITEID, "forging/left"), ForgingSlot.MATERIAL, 15, 31, slot, stack -> true);
        registry.registerForgingSlot(new ResourceLocation(MITEITEMod.ITEID, "forging/right"), ForgingSlot.MATERIAL, 69, 31, slot, stack -> true);
        registry.registerForgingSlot(new ResourceLocation(MITEITEMod.ITEID, "forging/down_left"), ForgingSlot.MATERIAL, 24, 62, slot, stack -> true);
        registry.registerForgingSlot(new ResourceLocation(MITEITEMod.ITEID, "forging/down_right"), ForgingSlot.MATERIAL, 60, 62, slot, stack -> true);
        registry.registerForgingSlot(new ResourceLocation(MITEITEMod.ITEID, "forging/tool"), ForgingSlot.TOOL, 42, 34, slot, stack -> stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemArmor);
        registry.registerForgingSlot(new ResourceLocation(MITEITEMod.ITEID, "forging/axe"), ForgingSlot.AXE, 137, 62,
                new ResourceLocation(MITEITEMod.ITEID, "textures/gui/container/forging_table/slot_axe.png"), stack -> stack.getItem() instanceof ItemAxe);
        registry.registerForgingSlot(new ResourceLocation(MITEITEMod.ITEID, "forging/hammer"), ForgingSlot.HAMMER, 137, 6,
                new ResourceLocation(MITEITEMod.ITEID, "textures/gui/container/forging_table/slot_hammer.png"), stack -> stack.getItem() instanceof ItemWarHammer);
        registry.registerForgingSlot(new ResourceLocation(MITEITEMod.ITEID, "forging/output"), ForgingSlot.OUTPUT, 137, 34, slot, stack -> false);

        registry.registerForgingArrow(new ResourceLocation(MITEITEMod.ITEID, "forging/arrow"), 96, 35,
                new ResourceLocation(MITEITEMod.ITEID, "textures/gui/container/forging_table/arrow.png"),
                new ResourceLocation(MITEITEMod.ITEID, "textures/gui/container/forging_table/arrow_full.png"));

        registry.registerForgingListener(DEFAULT_FORGING_LISTENER);
        registry.registerForgingRecipeProvider(DEFAULT_FORGING_RECIPE_PROVIDER);

        this.registerForgingRecipeSet(registry, Material.iron, ForgingTableLevel.IRON, 2);
        this.registerForgingRecipeSet(registry, Material.ancient_metal, ForgingTableLevel.MITHRIL, 2);
        this.registerForgingRecipeSet(registry, Material.mithril, ForgingTableLevel.MITHRIL, 4);
        this.registerForgingRecipeSet(registry, Material.adamantium, ForgingTableLevel.ADAMANTIUM, 6);
        this.registerForgingRecipeSet(registry, Materials.vibranium, ForgingTableLevel.VIBRANIUM, 9);
    }

    private void registerForgingRecipeSet(ITERegistry registry, Material material, ForgingTableLevel level, int maxLevel) {
        if (maxLevel >= 0) {
            this.registerForgingRecipe(registry, ForgingRecipe.Builder.of(material, 0, level)
                    .setChanceOfFailure(MITEITEItemRegistryInit.IRON_ENHANCE_STONE.getFailChance())
                    .setAxeDurabilityCost(2048)
                    .setHammerDurabilityCost(2048)
                    .setTimeReq(400)
                    .addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5)))
                    .addMaterials(
                            new ItemStack(MITEITEItemRegistryInit.ingotGold, 1),
                            new ItemStack(MITEITEItemRegistryInit.ingotIron, 1),
                            new ItemStack(MITEITEItemRegistryInit.IRON_ENHANCE_STONE, 1)
                    ));
        }
        if (maxLevel >= 1) {
            this.registerForgingRecipe(registry, ForgingRecipe.Builder.of(material, 1, level)
                    .setChanceOfFailure(MITEITEItemRegistryInit.IRON_ENHANCE_STONE.getFailChance())
                    .setAxeDurabilityCost(2048)
                    .setHammerDurabilityCost(3072)
                    .setTimeReq(600)
                    .addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5)))
                    .setQualityReward(EnumQuality.fine)
                    .addMaterials(
                            new ItemStack(MITEITEItemRegistryInit.ingotGold, 1),
                            new ItemStack(MITEITEItemRegistryInit.ingotIron, 2),
                            new ItemStack(MITEITEItemRegistryInit.IRON_ENHANCE_STONE, 1)
                    ));
        }
        if (maxLevel >= 2) {
            this.registerForgingRecipe(registry, ForgingRecipe.Builder.of(material, 2, level)
                    .setChanceOfFailure(MITEITEItemRegistryInit.IRON_ENHANCE_STONE.getFailChance())
                    .setAxeDurabilityCost(3072)
                    .setHammerDurabilityCost(3072)
                    .setTimeReq(800)
                    .addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5)))
                    .setQualityReward(EnumQuality.fine)
                    .addMaterials(
                            new ItemStack(MITEITEItemRegistryInit.ingotGold, 2),
                            new ItemStack(MITEITEItemRegistryInit.ingotIron, 2),
                            new ItemStack(MITEITEItemRegistryInit.IRON_ENHANCE_STONE, 1)
                    ));
        }
        if (maxLevel >= 3) {
            this.registerForgingRecipe(registry, ForgingRecipe.Builder.of(material, 3, level)
                    .setChanceOfFailure(MITEITEItemRegistryInit.IRON_ENHANCE_STONE.getFailChance())
                    .setAxeDurabilityCost(3072)
                    .setHammerDurabilityCost(4096)
                    .setTimeReq(1000)
                    .addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5)))
                    .setQualityReward(EnumQuality.excellent)
                    .addMaterials(
                            new ItemStack(MITEITEItemRegistryInit.ingotGold, 2),
                            new ItemStack(MITEITEItemRegistryInit.ingotIron, 2),
                            new ItemStack(MITEITEItemRegistryInit.IRON_ENHANCE_STONE, 2)
                    ));
        }
        if (maxLevel >= 4) {
            this.registerForgingRecipe(registry, ForgingRecipe.Builder.of(material, 4, level)
                    .setChanceOfFailure(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE.getFailChance())
                    .setAxeDurabilityCost(4096)
                    .setHammerDurabilityCost(4096)
                    .setTimeReq(1200)
                    .setQualityReward(EnumQuality.excellent)
                    .addFaultFeedback(DowngradeFeedback.of(1))
                    .addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5)))
                    .addMaterials(
                            new ItemStack(MITEITEItemRegistryInit.ingotGold, 1),
                            new ItemStack(MITEITEItemRegistryInit.ingotMithril, 1),
                            new ItemStack(MITEITEItemRegistryInit.redstone, 1),
                            new ItemStack(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE, 1)
                    ));
        }
        if (maxLevel >= 5) {
            this.registerForgingRecipe(registry, ForgingRecipe.Builder.of(material, 5, level)
                    .setChanceOfFailure(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE.getFailChance())
                    .setAxeDurabilityCost(4096)
                    .setHammerDurabilityCost(6144)
                    .setTimeReq(1400)
                    .setQualityReward(EnumQuality.superb)
                    .addFaultFeedback(DowngradeFeedback.of(1))
                    .addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5)))
                    .addMaterials(
                            new ItemStack(MITEITEItemRegistryInit.ingotGold, 2),
                            new ItemStack(MITEITEItemRegistryInit.ingotMithril, 1),
                            new ItemStack(MITEITEItemRegistryInit.redstone, 2),
                            new ItemStack(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE, 1)
                    ));
        }
        if (maxLevel >= 6) {
            this.registerForgingRecipe(registry, ForgingRecipe.Builder.of(material, 6, level)
                    .setChanceOfFailure(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE.getFailChance())
                    .setAxeDurabilityCost(6144)
                    .setHammerDurabilityCost(6144)
                    .setTimeReq(1600)
                    .setQualityReward(EnumQuality.superb)
                    .addFaultFeedback(DowngradeFeedback.of(1))
                    .addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5)))
                    .addMaterials(
                            new ItemStack(MITEITEItemRegistryInit.ingotGold, 2),
                            new ItemStack(MITEITEItemRegistryInit.ingotMithril, 2),
                            new ItemStack(Block.blockRedstone, 1),
                            new ItemStack(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE, 1)
                    ));
        }
        if (maxLevel >= 7) {
            this.registerForgingRecipe(registry, ForgingRecipe.Builder.of(material, 7, ForgingTableLevel.VIBRANIUM)
                    .setChanceOfFailure(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE.getFailChance())
                    .setAxeDurabilityCost(6144)
                    .setHammerDurabilityCost(8192)
                    .setTimeReq(1800)
                    .setQualityReward(EnumQuality.masterwork)
                    .addFaultFeedback(DowngradeFeedback.of(1))
                    .addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10)))
                    .addMaterials(
                            new ItemStack(MITEITEItemRegistryInit.ghastTear, 1),
                            new ItemStack(MITEITEItemRegistryInit.ingotAdamantium, 1),
                            new ItemStack(Item.ingotMithril, 2),
                            new ItemStack(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE, 1)
                    ));
        }
        if (maxLevel >= 8) {
            this.registerForgingRecipe(registry, ForgingRecipe.Builder.of(material, 8, ForgingTableLevel.VIBRANIUM)
                    .setChanceOfFailure(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE.getFailChance())
                    .setAxeDurabilityCost(8192)
                    .setHammerDurabilityCost(10240)
                    .setTimeReq(2000)
                    .setQualityReward(EnumQuality.masterwork)
                    .addFaultFeedback(DowngradeFeedback.of(1))
                    .addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10)))
                    .addMaterials(
                            new ItemStack(MITEITEItemRegistryInit.ghastTear, 2),
                            new ItemStack(MITEITEItemRegistryInit.ingotAdamantium, 1),
                            new ItemStack(Item.ingotMithril, 2),
                            new ItemStack(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE, 1)
                    ));
        }
        if (maxLevel >= 9) {
            this.registerForgingRecipe(registry, ForgingRecipe.Builder.of(material, 9, ForgingTableLevel.VIBRANIUM)
                    .setChanceOfFailure(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE.getFailChance())
                    .setAxeDurabilityCost(8192)
                    .setHammerDurabilityCost(10240)
                    .setTimeReq(2200)
                    .setQualityReward(EnumQuality.legendary)
                    .addFaultFeedback(DowngradeFeedback.of(1))
                    .addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10)))
                    .addMaterials(
                            new ItemStack(MITEITEItemRegistryInit.ghastTear, 2),
                            new ItemStack(MITEITEItemRegistryInit.ingotAdamantium, 2),
                            new ItemStack(Item.ingotMithril, 2),
                            new ItemStack(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE, 1)
                    ));
        }
    }

    private void registerForgingRecipe(ITERegistry registry, ForgingRecipe.Builder builder) {
        builder.build(registry::registerForgingRecipe);
    }
}

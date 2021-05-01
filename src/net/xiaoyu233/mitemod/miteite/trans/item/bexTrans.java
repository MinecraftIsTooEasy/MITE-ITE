package net.xiaoyu233.mitemod.miteite.trans.item;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(bex.class)
public class bexTrans {
   @Overwrite
   private float getBenchAndToolsModifier(Container container) {
      if (!(container instanceof ContainerWorkbench)) {
         return 0.0F;
      } else {
         ContainerWorkbench container_workbench = (ContainerWorkbench)container;
         SlotResult slot_crafting = (SlotResult)container_workbench.getSlot(0);
         ItemStack item_stack = slot_crafting.getStack();
         Item item = item_stack == null ? null : item_stack.getItem();
         aah recipe = container_workbench.getRecipe();
         Material material_to_check_tool_bench_hardness_against = recipe == null ? item.getHardestMetalMaterial() : recipe.getMaterialToCheckToolBenchHardnessAgainst();
         if (material_to_check_tool_bench_hardness_against == null) {
            return 0.2F;
         } else {
            Material tool_material = BlockWorkbench.getToolMaterial(container_workbench.getBlockMetadata());
            if (tool_material != Material.flint && tool_material != Material.obsidian) {
               if (tool_material != Material.copper && tool_material != Material.silver && tool_material != Material.gold) {
                  if (tool_material == Material.iron) {
                     return 0.4F;
                  } else if (tool_material == Material.ancient_metal) {
                     return 0.5F;
                  } else if (tool_material == Material.mithril) {
                     return 0.6F;
                  } else if (tool_material == Material.adamantium) {
                     return 0.7F;
                  } else if (tool_material == Materials.vibranium) {
                     return 0.8F;
                  } else {
                     Minecraft.setErrorMessage("getBenchAndToolsModifier: unrecognized tool material " + tool_material);
                     return 0.0F;
                  }
               } else {
                  return 0.3F;
               }
            } else {
               return 0.2F;
            }
         }
      }
   }
}

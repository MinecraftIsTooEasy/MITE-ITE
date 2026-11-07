package net.xiaoyu233.mitemod.miteite.compat.emi;

import dev.emi.emi.EmiRenderHelper;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.TextWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.emi.emi.runtime.EmiDrawContext;
import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ItemStack;
import net.minecraft.ItemTool;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingRecipe;
import net.xiaoyu233.mitemod.miteite.item.recipe.IFaultFeedback;
import org.jetbrains.annotations.Nullable;
import shims.java.net.minecraft.text.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class ForgingEmiRecipe implements EmiRecipe {
    private static final ResourceLocation BACKGROUND = new ResourceLocation("emi", "textures/gui/background.png");

    private final EmiRecipeCategory category;
    private final ForgingRecipe recipe;
    private final EmiIngredient toolInput;
    private final EmiIngredient toolOutput;
    private final List<EmiStack> outputStacks;
    private final List<EmiStack> materialInputs;
    private final List<IFaultFeedback> faultFeedbacks;
    private final ResourceLocation id;

    public ForgingEmiRecipe(EmiRecipeCategory category, ForgingRecipe recipe) {
        this.category = category;
        this.recipe = recipe;

        List<Item> tools;
        if (recipe.tool() != null) {
            tools = Collections.singletonList(recipe.tool());
        } else {
            tools = Arrays.stream(Item.itemsList)
                    .filter(Objects::nonNull)
                    .filter(item -> item instanceof ItemTool || item instanceof ItemArmor)
                    .filter(item -> item.getMaterialForRepairs() == recipe.material())
                    .toList();
        }
        List<EmiStack> inputs = tools.stream()
                .map(item -> EmiStack.of(stackOf(item, recipe.levelToUpgrade())))
                .toList();
        List<EmiStack> outputs = tools.stream()
                .map(item -> EmiStack.of(stackOf(item, recipe.levelToUpgrade() + 1)))
                .toList();
        this.toolInput = inputs.isEmpty() ? EmiStack.EMPTY : EmiIngredient.of(inputs);
        this.toolOutput = outputs.isEmpty() ? EmiStack.EMPTY : EmiIngredient.of(outputs);
        this.outputStacks = outputs;

        this.materialInputs = recipe.materialsToUpgrade().stream()
                .map(stack -> EmiStack.of(stack, stack.stackSize))
                .toList();
        this.faultFeedbacks = recipe.faultFeedback().stream()
                .filter(Objects::nonNull)
                .toList();

        this.id = new ResourceLocation("miteite", "forging/" + sanitize(recipe) + "/" + recipe.levelToUpgrade());
    }

    private static ItemStack stackOf(Item item, int grade) {
        ItemStack stack = new ItemStack(item, 1);
        stack.setForgingGrade(grade);
        return stack;
    }

    private static String sanitize(ForgingRecipe recipe) {
        if (recipe.tool() != null) {
            return "item_" + recipe.tool().itemID;
        }
        if (recipe.material() == null) {
            return "unknown";
        }
        return Optional.ofNullable(recipe.material().getName())
                .map(name -> name.toLowerCase().replaceAll("[^a-z0-9/._-]", "_"))
                .orElse("unknown");
    }

    private int infoRows() {
        return 6 + this.faultFeedbacks.size();
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return this.category;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return this.id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return Stream.concat(Stream.of(this.toolInput), this.materialInputs.stream()).toList();
    }

    @Override
    public List<EmiStack> getOutputs() {
        return this.outputStacks;
    }

    @Override
    public int getDisplayWidth() {
        return 150;
    }

    @Override
    public int getDisplayHeight() {
        return 52 + infoRows() * 10 + 8;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addDrawable(0, 0, this.getDisplayWidth(), getDisplayHeight(), (draw, mouseX, mouseY, delta) ->
                EmiRenderHelper.drawNinePatch(EmiDrawContext.wrap(draw), BACKGROUND, 0, 0, this.getDisplayWidth(), 52, 27, 0, 4, 1));
        int slot = 18;
        int arrow = 24;
        int x1 = (this.getDisplayWidth() - (slot * 2 + arrow + 8)) / 2;
        widgets.addSlot(this.toolInput, x1, 6).recipeContext(this);
        widgets.addTexture(EmiTexture.EMPTY_ARROW, x1 + slot + 4, 6);
        widgets.addSlot(this.toolOutput, x1 + slot + 4 + arrow + 4, 6).recipeContext(this);

        int x2 = (this.getDisplayWidth() - 5 * slot) / 2;
        for (int i = 0; i < 5; i++) {
            int ingX = x2 + i * slot;
            if (i < this.materialInputs.size()) {
                widgets.addSlot(this.materialInputs.get(i), ingX, 28);
            } else {
                widgets.addSlot(ingX, 28);
            }
        }

        int y = 56;
        widgets.addText(Text.translatable("emi.miteite.forging.grade"), 4, y, 0xFFFFFF, true);
        widgets.addText(Text.literal(this.recipe.levelToUpgrade() + " -> " + (this.recipe.levelToUpgrade() + 1)), this.getDisplayWidth() - 4, y, 0xFFFFFF, true).horizontalAlign(TextWidget.Alignment.END);

        widgets.addText(Text.translatable("gui.forgingTable.chanceOfFailure"), 4, y + 10, 0xFFFFFF, true);
        widgets.addText(Text.literal(this.recipe.chanceOfFailure() + "%"), this.getDisplayWidth() - 4, y + 10, 0xFFFFFF, true).horizontalAlign(TextWidget.Alignment.END);

        widgets.addText(Text.translatable("gui.forgingTable.time"), 4, y + 20, 0xFFFFFF, true);
        widgets.addText(Text.literal(this.recipe.timeReq() / 20.0F + "S"), this.getDisplayWidth() - 4, y + 20, 0xFFFFFF, true).horizontalAlign(TextWidget.Alignment.END);

        widgets.addText(Text.translatable("gui.forgingTable.axeCost"), 4, y + 30, 0xFFFFFF, true);
        widgets.addText(Text.literal(String.valueOf(this.recipe.axeDurabilityCost())), this.getDisplayWidth() - 4, y + 30, 0xFFFFFF, true).horizontalAlign(TextWidget.Alignment.END);

        widgets.addText(Text.translatable("gui.forgingTable.hammerCost"), 4, y + 40, 0xFFFFFF, true);
        widgets.addText(Text.literal(String.valueOf(this.recipe.hammerDurabilityCost())), this.getDisplayWidth() - 4, y + 40, 0xFFFFFF, true).horizontalAlign(TextWidget.Alignment.END);

        widgets.addText(Text.translatable("gui.forgingTable.ifFail"), 4, y + 50, 0xFFFF55, true);

        for (IFaultFeedback feedback : this.faultFeedbacks) {
            widgets.addText(Text.literal("   ").append(Text.translatable(feedback.getName(), feedback.getData())), 4, y + 60 + 10 * faultFeedbacks.indexOf(feedback), 0xFF5555, true);
        }
    }
}

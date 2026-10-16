package net.xiaoyu233.mitemod.miteite.util;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEItem;
import net.xiaoyu233.mitemod.miteite.api.IItemLevelUpListener;
import net.xiaoyu233.mitemod.miteite.api.ItemLevelUpContext;
import net.xiaoyu233.mitemod.miteite.item.ItemModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.ModifierUtils;
import net.xiaoyu233.mitemod.miteite.registry.ITERegistryImpl;

import java.util.List;

public final class ItemLevelUpHelper {
    private ItemLevelUpHelper() {
    }

    public static void fireItemLevelUp(ItemStack stack, EntityPlayer player, NBTTagCompound tagCompound, List<ItemModifierTypes> candidates, boolean armor) {
        NBTTagCompound modifiers = tagCompound.getCompoundTag("modifiers");
        ItemLevelUpContext context = new ItemLevelUpContext(stack, player, tagCompound, modifiers, armor);
        List<IItemLevelUpListener> listeners = ITERegistryImpl.itemLevelUpListeners;

        for (IItemLevelUpListener listener : listeners) {
            listener.modifyCandidates(context, candidates);
        }
        if (context.isCancelled()) {
            return;
        }

        ItemModifierTypes modifierType = null;
        for (IItemLevelUpListener listener : listeners) {
            modifierType = listener.selectModifier(context, candidates);
            if (modifierType != null) {
                break;
            }
        }
        if (modifierType == null) {
            modifierType = ModifierUtils.getModifierWithWeight(candidates, player.getRNG());
        }

        if (modifierType != null) {
            boolean isNewModifier = !modifiers.hasKey(modifierType.getNbtName());
            int newLevel = stack.getItem().addModifierLevelFor(modifiers, modifierType);
            ChatMessageComponent itemName = ChatMessageComponent.createFromTranslationKey(stack.getUnlocalizedName() + ".name");
            ChatMessageComponent displayName = modifierType.getDisplayName().setColor(modifierType.getColor());
            if (isNewModifier) {
                player.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("miteite.msg.modifier.new", itemName, displayName));
            } else {
                player.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("miteite.msg.modifier.level", itemName, displayName, newLevel));
            }
            for (IItemLevelUpListener listener : listeners) {
                listener.onModifierApplied(context, modifierType, newLevel, isNewModifier);
            }
        }

        for (IItemLevelUpListener listener : listeners) {
            listener.afterItemLevelUp(context);
        }
    }
}

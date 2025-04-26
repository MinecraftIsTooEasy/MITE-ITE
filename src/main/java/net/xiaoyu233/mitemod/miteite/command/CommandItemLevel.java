package net.xiaoyu233.mitemod.miteite.command;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.xiaoyu233.mitemod.miteite.item.ArmorModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.ToolModifierTypes;

import java.util.Arrays;
import java.util.List;

public class CommandItemLevel extends CommandBase {
    public String getCommandName() {
        return "itemlevel";
    }

    @Override
    public String getCommandUsage(ICommandSender ICommandSender2) {
        return "commands.itemlevel.usage";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public void processCommand(ICommandSender iCommandSender, String[] args) {
        if (args.length < 2) {
            throw new WrongUsageException("commands.itemlevel.usage");
        }
        ServerPlayer player = getPlayer(iCommandSender, args[0]);
        ItemStack itemStack;
        NBTTagCompound compound;
        if (args[1].equals("set")) {
            if (args[2].equals("level")) {
                itemStack = player.getHeldItemStack();
                if (itemStack.stackTagCompound != null) {
                    compound = itemStack.stackTagCompound;
                    if (compound.hasKey("tool_level")) {
                        compound.setInteger("tool_level", Integer.parseInt(args[3]));
                        iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.itemlevel.set.level.success" + args[3]).setColor(EnumChatFormatting.WHITE));
                    }
                }
            }
            if (args[2].equals("xp")) {
                itemStack = player.getHeldItemStack();
                if (itemStack.stackTagCompound != null) {
                    compound = itemStack.stackTagCompound;
                    if (compound.hasKey("tool_exp")) {
                        compound.setInteger("tool_exp", Integer.parseInt(args[3]));
                        iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.itemlevel.set.xp.success" + args[3]).setColor(EnumChatFormatting.WHITE));
                    }
                }
            }
        }
//        if (args[1].equals("add")) {
//            if (args[2].equals("armor")) {
//                itemStack = player.getHeldItemStack();
//                if (itemStack.stackTagCompound != null) {
//                    compound = itemStack.stackTagCompound;
//                    if (compound.hasKey("modifiers")) {
//                        String modifierName = args[3];
//                        ToolModifierTypes modifierType = ToolModifierTypes.valueOf(modifierName);
//                        int posLength = modifierType.getModifierLevel(compound);
//                        NBTTagCompound modifiers = compound.getCompoundTag("modifiers");
//                        modifiers.setInteger(modifierType.nbtName, posLength + Integer.parseInt(args[3]));
//                    }
//                }
//            }
//            if (args[2].equals("tool")) {
//                itemStack = player.getHeldItemStack();
//                if (itemStack.stackTagCompound != null) {
//                    compound = itemStack.stackTagCompound;
//                    if (compound.hasKey("modifiers")) {
//                        String modifierName = args[3];
//                        ToolModifierTypes modifierType = ToolModifierTypes.valueOf(modifierName);
//                        int posLength = modifierType.getModifierLevel(compound);
//                        NBTTagCompound modifiers = compound.getCompoundTag("modifiers");
//                        modifiers.setInteger(modifierType.nbtName, posLength + Integer.parseInt(args[3].substring(19 + modifierName.length() + 1)));
//                    }
//                }
//            }
//        }
    }

    protected String[] getAllUsernames() {
        return MinecraftServer.getServer().getAllUsernames();
    }

    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par2ArrayOfStr.length == 1) {
            return CommandEffect.getListOfStringsMatchingLastWord(par2ArrayOfStr, this.getAllUsernames());
        }
        if (par2ArrayOfStr.length == 2) {
            return getListOfStringsMatchingLastWord(par2ArrayOfStr, /*"add",*/ "set");
        }
        if (par2ArrayOfStr.length == 3 && par2ArrayOfStr[1].equals("set")) {
            return getListOfStringsMatchingLastWord(par2ArrayOfStr, "level", "xp");
        }
//        if (par2ArrayOfStr.length == 3 && par2ArrayOfStr[1].equals("add")) {
//            return getListOfStringsMatchingLastWord(par2ArrayOfStr, "armor", "tool");
//        }
//        if (par2ArrayOfStr.length == 4 && par2ArrayOfStr[2].equals("armor")) {
//            return getListOfStringsMatchingLastWord(par2ArrayOfStr, this.getArmorModifierTypeText());
//        }
//        if (par2ArrayOfStr.length == 4 && par2ArrayOfStr[2].equals("tool")) {
//            return getListOfStringsMatchingLastWord(par2ArrayOfStr, this.getToolModifierTypeText());
//        }
        return null;
    }

    private String getArmorModifierTypeText() {
        return Arrays.stream(ArmorModifierTypes.values()).findAny().get().getNbtName();
    }

    private String getToolModifierTypeText() {
        return Arrays.stream(ToolModifierTypes.values()).findAny().get().getNbtName();
    }
}


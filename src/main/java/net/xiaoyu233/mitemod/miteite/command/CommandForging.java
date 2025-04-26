package net.xiaoyu233.mitemod.miteite.command;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;

import java.util.List;

public class CommandForging extends CommandBase {
    @Override
    public String getCommandName() {
        return "forging";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "commands.forging.usage";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] args) {
        if (args.length < 2) {
            throw new WrongUsageException("commands.forging.usage");
        }
        ServerPlayer player = getPlayer(iCommandSender, args[0]);
        if (!args[1].equals("set")) {
            throw new WrongUsageException("commands.forging.usage");
        } else {
            ItemStack itemStack = player.getHeldItemStack();
            if (itemStack.stackTagCompound != null) {
                NBTTagCompound compound = itemStack.stackTagCompound;
                if (compound.hasKey("forging_grade")) {
                    compound.setInteger("forging_grade", Integer.parseInt(args[2]));
                }
            }
            iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.forging.set.success" + args[2]).setColor(EnumChatFormatting.WHITE));
        }
    }

    protected String[] getAllUsernames() {
        return MinecraftServer.getServer().getAllUsernames();
    }

    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par2ArrayOfStr.length == 1) {
            return CommandEffect.getListOfStringsMatchingLastWord(par2ArrayOfStr, this.getAllUsernames());
        }
        if (par2ArrayOfStr.length == 2) {
            return getListOfStringsMatchingLastWord(par2ArrayOfStr, "set");
        }
        return null;
    }
}

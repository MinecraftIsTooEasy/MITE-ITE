package net.xiaoyu233.mitemod.miteite.events.listener;

import moddedmite.rustedironcore.api.event.listener.ITickListener;
import net.minecraft.World;
import net.minecraft.server.MinecraftServer;
import net.xiaoyu233.mitemod.miteite.api.ITEWorldInfoShared;
import net.xiaoyu233.mitemod.miteite.util.Configs;

public class TickListener implements ITickListener {
    @Override
    public void onServerTick(MinecraftServer server) {
        if (!Configs.GameMechanics.FIRST_DAY_LONGER_DAY_TIME.get()) return;
        World world = server.getEntityWorld();
        ITEWorldInfoShared info = (ITEWorldInfoShared) world.getWorldInfo().shared;
        if (info.ite$isTimeBack()) return;
        if (world.getTotalWorldTime() == 12000L) {
            info.ite$setTimeBack(true);
            world.setTotalWorldTime(0L);
        }
    }
}

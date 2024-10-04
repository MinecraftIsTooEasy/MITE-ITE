package net.xiaoyu233.mitemod.miteite.events;

import moddedmite.rustedironcore.api.event.events.SpawnConditionRegisterEvent;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.util.Configs;


import java.util.function.Consumer;

public class MITEITESpawnConditions implements Consumer<SpawnConditionRegisterEvent> {

    @Override
    public void accept(SpawnConditionRegisterEvent event) {
        event.register(EntityGhast.class, (world, x, y, z) -> {
            if (!world.isBloodMoon(true) || (world.isOverworld() && world.getDayOfOverworld() < (Configs.GameMechanics.MobSpawning.GHAST_SPAWN_LIMIT_DAY.get()))){
                return null;
            }
            return EntityGhast.class;
        });
    }
}

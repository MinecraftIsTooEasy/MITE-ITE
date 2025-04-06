package net.xiaoyu233.mitemod.miteite.events;

import moddedmite.rustedironcore.api.event.events.SpawnConditionRegisterEvent;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.entity.EntityAnnihilationSkeleton;
import net.xiaoyu233.mitemod.miteite.util.Configs;


import java.util.function.Consumer;

public class MITEITESpawnConditions implements Consumer<SpawnConditionRegisterEvent> {

    @Override
    public void accept(SpawnConditionRegisterEvent event) {
        event.register(EntityGhast.class, (world, x, y, z) -> {
            if (!world.isBloodMoon(true) || (world.isOverworld() && world.getDayOfOverworld() < (Configs.GameMechanics.MobSpawning.GHAST_SPAWN_LIMIT_DAY.get()))) {
                return null;
            }
            return EntityGhast.class;
        });
        event.register(EntityGiantZombie.class, (world, x, y, z) -> {
            if (world.isBloodMoon(false)) {
                return EntityGiantZombie.class;
            }
            return null;
        });
        event.register(EntityAncientBoneLord.class, (world, x, y, z) -> {
            if (world.isBloodMoon(false)) {
                return EntityAncientBoneLord.class;
            }
            return null;
        });
        event.register(EntityAnnihilationSkeleton.class, (world, x, y, z) -> {
            if (world.isFullWaterBlock(x, y, z, true)) {
                return null;
            }
            return EntityAnnihilationSkeleton.class;
        });
    }
}

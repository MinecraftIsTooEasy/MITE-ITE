package net.xiaoyu233.mitemod.miteite.util;

import net.minecraft.EntityLivingBase;
import net.xiaoyu233.fml.FishModLoader;

public class CompatUtil {
    public static boolean targetIsNotZombieBoss(EntityLivingBase target) {
        if (!FishModLoader.hasMod("extreme")) return true;
        return true;
    }
}

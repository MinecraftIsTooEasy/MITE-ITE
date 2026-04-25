package net.xiaoyu233.mitemod.miteite.events.listener;

import moddedmite.rustedironcore.api.event.listener.IPlayerAttributeListener;
import net.minecraft.EntityPlayer;

public class PlayerAttributeListener  implements IPlayerAttributeListener {
    public float onHealthLimitModify(EntityPlayer player, float original) {
        int max = Math.max(Math.min(6 + ((player.getExperienceLevel() / 5) * 2), 40), 6);
        return Math.max(max, (int) original);
    }
}

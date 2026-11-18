package net.xiaoyu233.mitemod.miteite.events.listener;

import moddedmite.rustedironcore.api.event.listener.IEntityMobListener;
import net.minecraft.EntityMob;
import net.minecraft.NBTTagCompound;
import net.xiaoyu233.mitemod.miteite.api.ITEMob;

public class EntityMobListener implements IEntityMobListener {

    @Override
    public void onReadEntityFromNBT(EntityMob entityMob, NBTTagCompound tag) {
        if (tag.hasKey("AttackCauseFire")) {
            ((ITEMob) entityMob).ite$setBurningAttack(tag.getBoolean("AttackCauseFire"));
        }
    }

    @Override
    public void onWriteEntityToNBT(EntityMob entityMob, NBTTagCompound tag) {
        tag.setBoolean("AttackCauseFire", ((ITEMob) entityMob).ite$hasBurningAttack());
    }
}

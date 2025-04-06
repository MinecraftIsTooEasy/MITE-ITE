package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.xiaoyu233.mitemod.miteite.util.EntityUtil;
import org.spongepowered.asm.mixin.SoftOverride;

public class EntityZombiePigmanLord extends EntityPigZombie {
    private int fx_counter;
    public EntityZombiePigmanLord(World par1World) {
        super(par1World);
    }

    @Override
    public void addRandomEquipment() {
        int day = this.getWorld().getDayOfOverworld();
        this.setCurrentItemOrArmor(0,new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_SWORD,1).randomizeForMob(this,day > 64));
        this.setCurrentItemOrArmor(1,new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_HELMET,1).randomizeForMob(this,day > 64));
        this.setCurrentItemOrArmor(2,new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_CHESTPLATE,1).randomizeForMob(this,day > 64));
        this.setCurrentItemOrArmor(3,new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_LEGGINGS,1).randomizeForMob(this,day > 64));
        this.setCurrentItemOrArmor(4,new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_BOOTS,1).randomizeForMob(this,day > 64));
        this.addPotionEffect(new PotionEffect(1,Integer.MAX_VALUE,0));
        this.addPotionEffect(new PotionEffect(5,Integer.MAX_VALUE,0));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int day = Math.min(Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
        this.setEntityAttribute(SharedMonsterAttributes.attackDamage, 12.0D + (double) day / 48.0D);
        this.setEntityAttribute(SharedMonsterAttributes.maxHealth, 50.0D + (double) day / 14.0D);
        this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.3D);
    }

    @Override
    public void handleHealthUpdate(EnumEntityState par1) {
        super.handleHealthUpdate(par1);
        if (par1 == EnumEntityState.unused0){
            EntityUtil.generateRandomParticles(this, EnumParticle.lava);
        }
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player){
            int diamond_count = 3;
            for (int i1 = 0; i1 < diamond_count; i1++) {
                this.dropItem(Item.diamond);
            }
        }
    }

    @SoftOverride //ITELivingEntity
    public boolean canBeDisarmed() {
        return false;
    }

    @Override
    public boolean canCatchFire() {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.getWorld().isRemote){
            if (fx_counter > 0){
                fx_counter--;
            }else {
                this.fx_counter = 60;
                this.worldObj.setEntityState(this,EnumEntityState.unused0);
            }
        }
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }
}

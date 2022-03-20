package com.momo.fd.entity.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityThrowableBase extends EntityThrowable {
    public EntityThrowableBase(World worldIn)
    {
        super(worldIn);
    }

    public EntityThrowableBase(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityThrowableBase(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, Item.getIdFromItem(Items.SNOWBALL));
            }
        }
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(RayTraceResult result)
    {
        if(!this.world.isRemote)
        {
            if(result.entityHit != null){
                float amount = 0F;

                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), amount);
            }
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() { }
}

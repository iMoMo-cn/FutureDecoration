package com.momo.fd.item.misc;

import com.momo.fd.entity.projectiles.EntityThrowableBase;
import com.momo.fd.item.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemThrowableBase extends ItemBase {

    public ItemThrowableBase(String name, EntityThrowableBase entityThrowableIn)
    {
        super(name);
        setMaxStackSize(16);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack item = playerIn.getHeldItem(handIn);
        if(!playerIn.capabilities.isCreativeMode){item.shrink(1);}

//        if(!worldIn.isRemote)
//        {
//            EntityThrowableBase entityThrowable = new EntityThrowableBase(worldIn, playerIn);
//            float pitch = playerIn.rotationPitch, yaw = playerIn.rotationYaw;
//            entityThrowable.shoot(playerIn, pitch, yaw, 0.0F, 1.5F, 1.0F);
//            worldIn.spawnEntity(entityThrowable);
//        }

        return ActionResult.newResult(EnumActionResult.SUCCESS, item);
    }

}

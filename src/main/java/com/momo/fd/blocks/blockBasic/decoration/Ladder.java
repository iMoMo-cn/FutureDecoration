package com.momo.fd.blocks.blockBasic.decoration;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Ladder extends BlockLadder implements IHasModel {
    public Material material;

    public Ladder(String name, Material materialIn) {

        setUnlocalizedName(name);
        setRegistryName(name);

        material = materialIn;

        if(material == Material.IRON)
        {
            setSoundType(Chain.CHAIN);
        }
        else {
            setSoundType(SoundType.LADDER);
        }

        setHardness(0.4F);
        setResistance(5.0F);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }



//    @Override
//    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
//    {
//        if(worldIn.isRemote)
//        {
//            if(entityIn instanceof EntityPlayer && entityIn.motionY != 0)
//            {
//                if()
//                {
//                    worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), ModSoundHandler.BLOCK_CHAIN_STEP, SoundCategory.BLOCKS, 1.0F, 1.0F, true);
//                }
//           }
//        }
//
//    }
}

package com.momo.fd.blocks.blockBush;


import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.BlockBase;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import javafx.scene.effect.Effect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;


public class Flower extends BlockBush implements IHasModel {

    public Flower(String name)
    {
        super(Material.PLANTS);
        setHardness(0.0F);
        setLightOpacity(0);
        this.blockSoundType = SoundType.PLANT;

        setRegistryName(name);
        setUnlocalizedName(name);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return super.getBoundingBox(state, source, pos).offset(state.getOffset(source, pos));
    }

    /**
     * Called When an Entity Collided with the Block
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if(worldIn.getBlockState(pos).getBlock() == ModBlocks.WITHER_ROSE)
        {
            if(entityIn instanceof EntityLivingBase)
            {
                EntityLivingBase entity = (EntityLivingBase)entityIn;

                entity.attackEntityFrom(DamageSource.WITHER, 1.0F);
            }
        }
    }

    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }
}

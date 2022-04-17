package com.momo.fd.blocks.blockBasic.sign;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockTile.tileSign.*;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Random;

public class StandingSign extends BlockStandingSign implements IHasModel {
    private Item itemSign;

    public StandingSign(String name)
    {
        super();
        this.setRegistryName(name);
        this.setUnlocalizedName(name);

        setHardness(1.0F);
        setSoundType(SoundType.WOOD);
        disableStats();

        ModBlocks.BLOCKS.add(this);
    }

    public StandingSign setItemSign(Item itemSignIn)
    {
        this.itemSign = itemSignIn;
        return this;
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(ROTATION).build());
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return itemSign;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(itemSign);
    }

    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        if(this == ModBlocks.ACACIA_SIGN_STANDING) return new TileEntitySignAcacia();
        else if(this == ModBlocks.BIRCH_SIGN_STANDING) return new TileEntitySignBirch();
        else if(this == ModBlocks.DARK_OAK_SIGN_STANDING) return new TileEntitySignDarkOak();
        else if(this == ModBlocks.JUNGLE_SIGN_STANDING) return new TileEntitySignJungle();
        else return new TileEntitySignSpruce();
    }
}

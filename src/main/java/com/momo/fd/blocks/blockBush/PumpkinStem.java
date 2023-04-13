package com.momo.fd.blocks.blockBush;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class PumpkinStem extends BlockStem implements IHasModel {
    public PumpkinStem() {
        super(ModBlocks.PUMPKIN);

        setRegistryName("pumpkin_stem");
        setUnlocalizedName("pumpkin_stem");

        ModBlocks.BLOCKS.add(this);
    }

    @Override
    public void registerModels() {

    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(Items.PUMPKIN_SEEDS);
    }

    @SubscribeEvent
    public static void SeedsPlant(BlockEvent.PlaceEvent evt)
    {
        Item handleItem = evt.getItemInHand().getItem();
        Block placeBlock = evt.getPlacedBlock().getBlock();
        BlockPos placePos = evt.getPos();

        if(handleItem == Items.PUMPKIN_SEEDS && placeBlock == Blocks.PUMPKIN_STEM )
        {
            if(!evt.getWorld().isRemote)
            {
                evt.getWorld().setBlockState(placePos, ModBlocks.PUMPKIN_STEM.getDefaultState());
            }
        }
    }
}

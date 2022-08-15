package com.momo.fd.events;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class BoneMealEvent {
    @SubscribeEvent
    public static void UseOnGrass(BonemealEvent event){
        World world = event.getWorld();

        BlockPos pos = event.getPos();
        IBlockState iBlockState = event.getBlock();
        Block block = iBlockState.getBlock();

        final BlockPos chunkPos = new BlockPos(pos.getX(), 0, pos.getZ());
        Biome biome = world.getChunkFromBlockCoords(pos).getBiome(chunkPos, world.getBiomeProvider());

        if(block == Blocks.GRASS)
        {
            if(biome == Biomes.PLAINS)
            {
                growFlower(ModBlocks.CORNFLOWER, pos, world);
            }

            if(biome == Biomes.MUTATED_FOREST || biome == Biomes.MUTATED_PLAINS)
            {
                growFlower(ModBlocks.LILY_OF_THE_VALLEY, pos, world);
                growFlower(ModBlocks.ROSE, pos, world);
                growFlower(ModBlocks.CORNFLOWER, pos, world);
            }
        }
    }

    private static boolean growFlower(Block block, BlockPos pos, World worldIn)
    {
        Random rand = new Random();
        BlockPos blockpos = pos.up();

        for (int i = 0; i < 8; ++i)
        {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true)
            {
                if (j >= i / 16)
                {
                    if (worldIn.isAirBlock(blockpos1))
                    {
                        if (rand.nextInt(8) == 0)
                        {
                            if (block.canPlaceBlockAt(worldIn, blockpos1)) {
                                worldIn.setBlockState(blockpos1, block.getDefaultState(), 2);
                            }
                        }
                    }

                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (worldIn.getBlockState(blockpos1.down()).getBlock() != Blocks.GRASS || worldIn.getBlockState(blockpos1).isNormalCube())
                {
                    break;
                }

                ++j;
            }
        }

        return true;
    }
}

package com.momo.fd.world.gen;

import com.momo.fd.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModPumpkinGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int x = chunkX * 16;
        int z = chunkZ * 16;

        for(int i = 0; i < 16; i++)
        {
            for(int j = 0; j < 16; j++)
            {
                int x1 = x + i;
                int z1 = z + j;

                BlockPos topPos = world.getHeight(new BlockPos(x1, 0, z1));
                int height = topPos.getY();

                for(int k = 0; k < 10; k++)
                {
                    BlockPos newPos = new BlockPos(x + 7, height - k, z + 7);

                    Block block = world.getBlockState(newPos).getBlock();

                    if(block == Blocks.PUMPKIN)
                    {
                        world.setBlockState(newPos, ModBlocks.PUMPKIN.getDefaultState(), 2|16);
                    }
                }
            }
        }
    }

}

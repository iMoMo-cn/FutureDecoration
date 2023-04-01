package com.momo.fd.world.gen;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockVariant.BlockVariantBase;
import com.momo.fd.blocks.blockVariant.EnumVariants;
import com.momo.fd.init.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModDeepslateGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!ModConfig.GENERAL_CONF.CREATE_DEEP_SLATE)
        {
            return;
        }

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

                generateDeepslate(world, height, x1, z1);
            }
        }
    }

    private boolean generateDeepslate(World world, int height, int x, int z) {

        if(height >= 5)
        {
            for (int y = 0; y < height; y++) {

                BlockPos newPos = new BlockPos(x + 16, y, z + 16);

                Block block = world.getBlockState(newPos).getBlock();

                if( y <= 16 )
                {
                    generateDeepslateOre(block, world, newPos);
                }
                else if( y <= 32 )
                {
                    Random r = new Random();

                    if(r.nextFloat() > 0.0625 * (y - 16))
                    {
                        generateDeepslateOre(block, world, newPos);
                    }
                }
                else { return true; }
            }
        }
        return true;
    }

    private boolean generateDeepslateOre(Block block, World world, BlockPos pos)
    {
        if(block == Blocks.STONE){ world.setBlockState(pos, ModBlocks.DEEPSLATE.getDefaultState(), 2|16); }
        else if(block == Blocks.IRON_ORE){ world.setBlockState(pos, ModBlocks.ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block0), 2|16); }
        else if(block == Blocks.GOLD_ORE){ world.setBlockState(pos, ModBlocks.ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block1), 2|16); }
        else if(block == ModBlocks.COPPER_ORE){ world.setBlockState(pos, ModBlocks.ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block2), 2|16); }
        else if(block == Blocks.COAL_ORE){ world.setBlockState(pos, ModBlocks.ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block3), 2|16); }
        else if(block == Blocks.LAPIS_ORE){ world.setBlockState(pos, ModBlocks.ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block4), 2|16); }
        else if(block == Blocks.DIAMOND_ORE){ world.setBlockState(pos, ModBlocks.ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block5), 2|16); }
        else if(block == Blocks.EMERALD_ORE){ world.setBlockState(pos, ModBlocks.ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block6), 2|16); }
        else if(block == Blocks.REDSTONE_ORE){ world.setBlockState(pos, ModBlocks.DEEPSLATE_REDSTONE_ORE.getDefaultState(), 2|16); }

        return true;
    }
}

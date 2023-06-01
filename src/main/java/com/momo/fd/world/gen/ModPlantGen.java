package com.momo.fd.world.gen;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockBush.berries.BerriesBush;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModPlantGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int x = chunkX * 16 + random.nextInt(8);
        int z = chunkZ * 16 + random.nextInt(8);

        final BlockPos blockPos = world.getHeight(new BlockPos(x, 0, z));
        final BlockPos chunkPos = new BlockPos(x, 0, z);
        final Biome biome = world.getChunkFromBlockCoords(chunkPos).getBiome(chunkPos, world.getBiomeProvider());

        if(biome == Biomes.MUTATED_FOREST)
        {
            generatePlant(ModBlocks.LILY_OF_THE_VALLEY, world, blockPos, random, 50);
            generatePlant(ModBlocks.ROSE, world, blockPos, random, 50);
            generatePlant(ModBlocks.CORNFLOWER, world, blockPos, random, 50);
        }

        if(biome == Biomes.MUTATED_PLAINS || biome == Biomes.PLAINS)
        {
            generatePlant(ModBlocks.CORNFLOWER, world, blockPos, random, 5, 3, 0.112F);
        }

        if(biome == Biomes.FOREST || biome == Biomes.BIRCH_FOREST|| biome == Biomes.MUTATED_BIRCH_FOREST || biome == Biomes.BIRCH_FOREST_HILLS || biome == Biomes.MUTATED_BIRCH_FOREST_HILLS)
        {
            generatePlant(ModBlocks.LILY_OF_THE_VALLEY, world, blockPos, random, 10, 5, 0.25F);
            generatePlant(ModBlocks.ROSE, world, blockPos, random, 10, 5, 0.12F);
        }

        if(biome == Biomes.ROOFED_FOREST || biome == Biomes.MUTATED_ROOFED_FOREST)
        {
            generatePlant(ModBlocks.ROSE, world, blockPos, random, 10, 5, 0.12F);
            generatePlant(ModBlocks.LILY_OF_THE_VALLEY, world, blockPos, random, 10, 5, 0.25F);
        }

        if(biome == Biomes.TAIGA || biome == Biomes.TAIGA_HILLS || biome == Biomes.MUTATED_TAIGA || biome == Biomes.REDWOOD_TAIGA
                || biome == Biomes.REDWOOD_TAIGA_HILLS || biome == Biomes.MUTATED_REDWOOD_TAIGA || biome == Biomes.COLD_TAIGA
                || biome == Biomes.COLD_TAIGA_HILLS || biome == Biomes.MUTATED_TAIGA_COLD)
        {
            generateBerryBush((BerriesBush)ModBlocks.BERRY_BUSH, world, blockPos, random, 0.15F);
        }

        if(biome == Biomes.SAVANNA || biome == Biomes.SAVANNA_PLATEAU || biome == Biomes.MUTATED_SAVANNA || biome == Biomes.MUTATED_SAVANNA_ROCK)
        {
            generateBerryBush((BerriesBush)ModBlocks.SAVANNA_BERRY_BUSH, world, blockPos, random, 0.15F);
        }

        generatePumpkin(world, chunkX * 16, chunkZ * 16);
    }

    private boolean generatePlant(Block block, World world, BlockPos pos, Random random, int extra){
        generatePlant(block, world, pos, random, extra, 0,1.0F);
        return true;
    }

    private boolean generatePlant(Block block, World world, BlockPos pos, Random random, int extra, int less){
        generatePlant(block, world, pos, random, extra, less,1.0F);
        return true;
    }

    private boolean generatePlant(Block block, World world, BlockPos pos, Random random, int extra, float chance){
        generatePlant(block, world, pos, random, extra, 0, chance);
        return true;
    }

    private boolean generatePlant(Block block, World world, BlockPos pos, Random random, int extra, int less, float chance) {

        if(random.nextFloat() < chance) {
            for (int i = 0; i < random.nextInt(extra) + less; i++) {
                int posX = (pos.getX() + random.nextInt(16));
                int posY = (pos.getY());
                int posZ = (pos.getZ() + random.nextInt(16));

                BlockPos newPos = new BlockPos(posX, posY, posZ);

                Boolean flag = world.getBlockState(newPos).getBlock() == Blocks.WATER;

                if (block.canPlaceBlockAt(world, newPos) && !flag) {
                    world.setBlockState(newPos, block.getDefaultState(), 2);
                }
            }
        }
        return true;
    }

    private boolean generateBerryBush(BerriesBush berryBush, World world, BlockPos pos, Random random, float chance)
    {
        if(random.nextFloat() < chance)
        {
            int posX = (pos.getX() + random.nextInt(16));
            int posY = (pos.getY());
            int posZ = (pos.getZ() + random.nextInt(16));

            BlockPos newPos = new BlockPos(posX, posY, posZ);

            IBlockState newBlockState = berryBush.getDefaultState().withProperty(berryBush.getProperty(), Integer.valueOf(3));

            Boolean flag = world.getBlockState(newPos).getBlock() == Blocks.WATER;

            if (berryBush.canPlaceBlockAt(world, newPos) && !flag) { world.setBlockState(newPos, newBlockState, 2);}

            for(int i = 0; i < 4; i++)
            {
                for(int y = -1; y < 2; y++)
                {
                    BlockPos newPos1 = new BlockPos(posX + random.nextInt(5) + 1, posY + y, posZ + random.nextInt(5) + 1);

                    flag = world.getBlockState(newPos1).getBlock() == Blocks.WATER;

                    if (berryBush.canPlaceBlockAt(world, newPos1) && !flag) { world.setBlockState(newPos1, newBlockState, 2); }
                }
            }
        }
        return true;
    }

    private boolean generatePumpkin(World world, int x, int z)
    {
        for(int i = 0; i < 16; i++)
        {
            for(int j = 0; j < 16; j++)
            {
                int x1 = x + i;
                int z1 = z + j;

                BlockPos topPos = world.getHeight(new BlockPos(x1, 0, z1));
                int height = topPos.getY();

                for (int y = 0; y < 30; y++)
                {
                    BlockPos newPos = new BlockPos(x1 + 16, height - y + 15, z1 + 16);

                    Block block = world.getBlockState(newPos).getBlock();

                    if(block == Blocks.PUMPKIN)
                    {
                        world.setBlockState(newPos, ModBlocks.PUMPKIN.getDefaultState(), 2);
                    }
                }
            }
        }
        return true;
    }
}

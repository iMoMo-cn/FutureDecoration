package com.momo.fd.world.gen;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockBush.BerriesBush;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.BiomeDictionary;
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

        if(biome == Biomes.TAIGA || biome == Biomes.TAIGA_HILLS || biome == Biomes.MUTATED_TAIGA)
        {
            generateBerryBush(world, blockPos, random, 0.35F);
        }

        if(biome == Biomes.REDWOOD_TAIGA || biome == Biomes.REDWOOD_TAIGA_HILLS || biome == Biomes.MUTATED_REDWOOD_TAIGA)
        {
            generateBerryBush(world, blockPos, random, 0.35F);
        }

        if(biome == Biomes.COLD_TAIGA || biome == Biomes.COLD_TAIGA_HILLS || biome == Biomes.MUTATED_TAIGA_COLD)
        {
            generateBerryBush(world, blockPos, random, 0.125F);
        }
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

                final BlockPos newPos = new BlockPos(posX, posY, posZ);

                if (block.canPlaceBlockAt(world, newPos)) {
                    world.setBlockState(newPos, block.getDefaultState(), 2);
                }
            }
        }
        return true;
    }

    private boolean generateBerryBush(World world, BlockPos pos, Random random, float chance)
    {
        if(random.nextFloat() < chance)
        {
            int posX = (pos.getX() + random.nextInt(16));
            int posY = (pos.getY());
            int posZ = (pos.getZ() + random.nextInt(16));

            final BlockPos newPos = new BlockPos(posX, posY, posZ);
            final BerriesBush block = (BerriesBush)ModBlocks.BERRY_BUSH;
            final IBlockState newBlockState = block.getDefaultState().withProperty(block.getProperty(), Integer.valueOf(3));

            if (block.canPlaceBlockAt(world, newPos)) { world.setBlockState(newPos, newBlockState, 2);}

            for(int i = 0; i < 4; i++)
            {
                for(int y = -1; y < 2; y++)
                {
                    final BlockPos newPos1 = new BlockPos(posX + random.nextInt(5) + 1, posY + y, posZ + random.nextInt(5) + 1);

                    if (block.canPlaceBlockAt(world, newPos1)) { world.setBlockState(newPos1, newBlockState, 2); }
                }
            }
        }
        return true;
    }
}

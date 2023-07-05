package com.momo.fd.world.gen;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockBush.caveVine.CaveVinePlant;
import com.momo.fd.blocks.blockMisc.MossBlock;
import com.momo.fd.world.tree.WorldGenBigAzaleaTree;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModLushCaveGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if(world.getWorldType() != WorldType.FLAT && world.getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES)
        {
            int x = chunkX * 16;
            int z = chunkZ * 16;
            int height = world.getHeight(new BlockPos(x, 0, z)).getY();

            final BlockPos chunkPos = new BlockPos(x, 0, z);
            final Biome biome = world.getChunkFromBlockCoords(chunkPos).getBiome(chunkPos, world.getBiomeProvider());
            boolean biomeFlag = biome == Biomes.MUTATED_FOREST || biome == Biomes.MUTATED_PLAINS;

            MinecraftServer server = world.getMinecraftServer();
            TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
            Template lush_cave = manager.get(server, new ResourceLocation(MoMoFramework.MODID, "lush_cave"));


            if(lush_cave == null)
            {
                MoMoFramework.Log("find a bad template %s", "lush_cave");
                return;
            }


            if(biomeFlag && height > 35 && random.nextFloat() < 0.0315F)
            {
                //add lush cave
                BlockPos cavePos = new BlockPos(x + 16, height - 20, z + 16);
                lush_cave.addBlocksToWorld(world, cavePos, new PlacementSettings(), 2|4|16);

                //add azalea tree
                BlockPos treePos = new BlockPos(x + 19 + random.nextInt(5), height, z + 19 + random.nextInt(5));
                WorldGenerator worldgenerator = new WorldGenBigAzaleaTree(true);

                worldgenerator.generate(world, random, treePos);

                //add moss decoration
                BlockPos mossPos = new BlockPos(x + 22, height - 19, z + 22);

                for(int i = -1; i <= 1; i++)
                {
                    for(int j = -1; j <= 1; j++)
                    {
                        BlockPos newPos = mossPos.add(i, 0, j);
                        IBlockState mossState = world.getBlockState(mossPos);
                        Block mossBlock = mossState.getBlock();
                        if(mossBlock == ModBlocks.MOSS_BLOCK && random.nextFloat() < 0.7)
                        {
                            ((MossBlock)mossBlock).grow(world, random, newPos, mossState);
                        }
                    }
                }

                //add cave vine
                BlockPos vinePos = new BlockPos(x + 22, height - 12, z + 22);
                IBlockState vineState1 = ModBlocks.CAVE_VINE_PLANT.getDefaultState().withProperty(CaveVinePlant.BERRIES, false);
                IBlockState vineState2 = ModBlocks.CAVE_VINE_PLANT.getDefaultState().withProperty(CaveVinePlant.BERRIES, true);
                IBlockState vineState3 = ModBlocks.CAVE_VINE.getDefaultState().withProperty(CaveVinePlant.BERRIES, false);

                for(int i = -2; i <= 2; i++)
                {
                    for(int j = -2; j <= 2; j++)
                    {
                        BlockPos newPos = vinePos.add(i, 0, j);

                        if(random.nextFloat() < 0.215)
                        {
                            int k1 = 0;
                            for(int k = 0; k < random.nextInt(5); k++)
                            {
                                IBlockState vineState = vineState1;
                                switch (random.nextInt(3))
                                {
                                    case 0: case 1: vineState = vineState1; break;
                                    case 2: vineState = vineState2; break;
                                }

                                world.setBlockState(newPos.down(k), vineState, 2);

                                k1++;
                            }
                            world.setBlockState(newPos.down(k1), vineState3, 3);
                        }
                    }
                }
            }
        }
    }
}

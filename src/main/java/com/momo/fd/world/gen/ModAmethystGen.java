package com.momo.fd.world.gen;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import static com.momo.fd.blocks.blockBasic.directional.DirectionalBlock.FACING;

public class ModAmethystGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int x = chunkX * 16;
        int z = chunkZ * 16;
        int height = world.getHeight(new BlockPos(x, 0, z)).getY();

        MinecraftServer server = world.getMinecraftServer();
        TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
        Template amethyst_geode = manager.get(server, new ResourceLocation(MoMoFramework.MODID, "amethyst_geode"));
        Template citrine_geode = manager.get(server, new ResourceLocation(MoMoFramework.MODID, "citrine_geode"));
        Template rubace_geode = manager.get(server, new ResourceLocation(MoMoFramework.MODID, "rubace_geode"));
        Template prasiolite_geode = manager.get(server, new ResourceLocation(MoMoFramework.MODID, "prasiolite_geode"));
        Template crystal_geode = manager.get(server, new ResourceLocation(MoMoFramework.MODID, "crystal_geode"));

        if(amethyst_geode == null || citrine_geode == null || rubace_geode == null || prasiolite_geode == null || crystal_geode == null)
        {
            MoMoFramework.Log("find a bad template %s", "geode");
            return;
        }

        if(world.provider.getDimension() == 0 && height > 30 && random.nextFloat() < 0.04167F)
        {
            Random posRandom = new Random();
            BlockPos pos = new BlockPos(x + 16, posRandom.nextInt(13) + 5, z + 16);

            switch (random.nextInt(5))
            {
                case 0:amethyst_geode.addBlocksToWorld(world, pos, new PlacementSettings(), 2|4|16);
                    genAmethystBudding(world, pos, ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_BUDDING, ModBlocks.AMETHYST_SMALL_BUD, ModBlocks.AMETHYST_MEDIUM_BUD, ModBlocks.AMETHYST_LARGE_BUD, ModBlocks.AMETHYST_CLUSTERS);
                    break;
                case 1:citrine_geode.addBlocksToWorld(world, pos, new PlacementSettings(), 2|4|16);
                    genAmethystBudding(world, pos, ModBlocks.CITRINE_BLOCK, ModBlocks.CITRINE_BUDDING, ModBlocks.CITRINE_SMALL_BUD, ModBlocks.CITRINE_MEDIUM_BUD, ModBlocks.CITRINE_LARGE_BUD, ModBlocks.CITRINE_CLUSTERS);
                    break;
                case 2:rubace_geode.addBlocksToWorld(world, pos, new PlacementSettings(), 2|4|16);
                    genAmethystBudding(world, pos, ModBlocks.RUBACE_BLOCK, ModBlocks.RUBACE_BUDDING, ModBlocks.RUBACE_SMALL_BUD, ModBlocks.RUBACE_MEDIUM_BUD, ModBlocks.RUBACE_LARGE_BUD, ModBlocks.RUBACE_CLUSTERS);
                    break;
                case 3:prasiolite_geode.addBlocksToWorld(world, pos, new PlacementSettings(), 2|4|16);
                    genAmethystBudding(world, pos, ModBlocks.PRASIOLITE_BLOCK, ModBlocks.PRASIOLITE_BUDDING, ModBlocks.PRASIOLITE_SMALL_BUD, ModBlocks.PRASIOLITE_MEDIUM_BUD, ModBlocks.PRASIOLITE_LARGE_BUD, ModBlocks.PRASIOLITE_CLUSTERS);
                    break;
                case 4:crystal_geode.addBlocksToWorld(world, pos, new PlacementSettings(), 2|4|16);
                    genAmethystBudding(world, pos, ModBlocks.CRYSTAL_BLOCK, ModBlocks.CRYSTAL_BUDDING, ModBlocks.CRYSTAL_SMALL_BUD, ModBlocks.CRYSTAL_MEDIUM_BUD, ModBlocks.CRYSTAL_LARGE_BUD, ModBlocks.CRYSTAL_CLUSTERS);
                    break;
            }
        }
    }

    public void genAmethystBudding(World world, BlockPos pos, Block block, Block budding, Block smallBud, Block mediumBud, Block largeBud, Block cluster)
    {
        for(int i = 0; i < 16; i++)
        {
            for(int j = 0; j < 16; j++)
            {
                for(int k = 0; k < 16; k++)
                {
                    BlockPos amethystPos = pos.add(i, j, k);
                    if(world.getBlockState(amethystPos).getBlock() == block && new Random().nextFloat() < 0.083F)
                    {
                        world.setBlockState(amethystPos, budding.getDefaultState(), 2|4|16);

                        Random r = new Random();

                        if(r.nextFloat() < 0.33F)
                        {
                            for (EnumFacing enumfacing : EnumFacing.values())
                            {
                                if(isBlockAir(world, amethystPos.offset(enumfacing.getOpposite())))
                                {
                                    IBlockState state = smallBud.getDefaultState().withProperty(FACING, enumfacing.getOpposite());

                                    switch (r.nextInt(5))
                                    {
                                        case 0: break;
                                        case 1: state = smallBud.getDefaultState().withProperty(FACING, enumfacing.getOpposite()); break;
                                        case 2: state = mediumBud.getDefaultState().withProperty(FACING, enumfacing.getOpposite()); break;
                                        case 3: state = largeBud.getDefaultState().withProperty(FACING, enumfacing.getOpposite()); break;
                                        case 4: state = cluster.getDefaultState().withProperty(FACING, enumfacing.getOpposite()); break;
                                    }
                                    world.setBlockState(amethystPos.offset(enumfacing.getOpposite()), state, 2|4|16);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isBlockAir(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getBlock().isAir(worldIn.getBlockState(pos), worldIn, pos);
    }
}

package com.momo.fd.world.gen;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockVariant.BlockVariantBase;
import com.momo.fd.blocks.blockVariant.EnumVariants;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ModWorldGenNew implements IWorldGenerator {

	//Please register this in preInitRegistries
	public ChunkGeneratorSettings chunkProviderSettings;

	private WorldGenerator
			copper_ore,
			nether_gold_ore,
			nether_iron_ore;

	public ModWorldGenNew() {
		copper_ore = new WorldGenMinable(ModBlocks.COPPER_ORE.getDefaultState(), 10, BlockMatcher.forBlock(Blocks.STONE));
		nether_gold_ore = new WorldGenMinable(ModBlocks.NETHER_ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block0), 10, BlockMatcher.forBlock(Blocks.NETHERRACK));
		nether_iron_ore = new WorldGenMinable(ModBlocks.NETHER_ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block1), 10, BlockMatcher.forBlock(Blocks.NETHERRACK));
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension())
		{
			case -1:
				//nether
				runGenOre(nether_gold_ore, world, random, chunkX, chunkZ, 10, 10, 117);
				runGenOre(nether_iron_ore, world, random, chunkX, chunkZ, 10, 10, 117);
				break;
			case 0:
				//overworld
				//MoMoFramework.Log("world gen running");

				runGenOre(copper_ore, world, random, chunkX, chunkZ, 13, 1, 63);

				break;
			case 1:
				//end
				break;
		}
	}

	//Utility

	//NetherOre
	void runGenOre(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chances, int minHeight, int maxHeight)
	{
		if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256)
		{
			throw  new IllegalArgumentException("NetherOre gen out of bounds");
		}

		int heightDiff = maxHeight - minHeight + 1;

		for (int i = 0; i < chances; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);

			gen.generate(world, rand, new BlockPos(x,y,z));
		}
	}

	//Trees
	private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));

		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGroundHeight(world, x, z, topBlock);
		BlockPos pos = new BlockPos(x,y,z);

		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(random.nextInt(chance) == 0)
				{
					generator.generate(world, random, pos);
				}
			}
		}
	}

	private static int calculateGroundHeight(World world, int x, int z, Block groundBlock)
	{
		int y = world.getHeight();
		boolean foundGround = false;

		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = block == groundBlock;
		}

		return y;
	}
}

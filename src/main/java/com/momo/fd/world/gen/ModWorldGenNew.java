package com.momo.fd.world.gen;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockVariant.baseVariant.BlockVariantBase;
import com.momo.fd.blocks.blockVariant.baseVariant.EnumVariants;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ModWorldGenNew implements IWorldGenerator {

	private WorldGenerator
			copper_ore,
			nether_gold_ore, nether_iron_ore,
			raw_iron, raw_gold, raw_copper;


	public ModWorldGenNew() {
		copper_ore = new WorldGenMinable(ModBlocks.COPPER_ORE.getDefaultState(), 10, BlockMatcher.forBlock(Blocks.STONE));
		nether_gold_ore = new WorldGenMinable(ModBlocks.NETHER_ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block0), 10, BlockMatcher.forBlock(Blocks.NETHERRACK));
		nether_iron_ore = new WorldGenMinable(ModBlocks.NETHER_ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block1), 10, BlockMatcher.forBlock(Blocks.NETHERRACK));
		raw_iron = new WorldGenMinable(ModBlocks.RAW_ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block0), 20, BlockMatcher.forBlock(Blocks.STONE));
		raw_gold = new WorldGenMinable(ModBlocks.RAW_ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block1), 20, BlockMatcher.forBlock(Blocks.STONE));
		raw_copper = new WorldGenMinable(ModBlocks.RAW_ORE.getDefaultState().withProperty(BlockVariantBase.VARIANT, EnumVariants.Block2), 20, BlockMatcher.forBlock(Blocks.STONE));
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

				switch (random.nextInt(3))
				{
					case 0: runGenOre(raw_iron, world, random, chunkX, chunkZ, 0.5F, 3, 1, 25); break;
					case 1: runGenOre(raw_gold, world, random, chunkX, chunkZ, 0.5F, 3, 1, 25); break;
					case 2: runGenOre(raw_copper, world, random, chunkX, chunkZ, 0.5F, 3, 1, 25); break;
				}

				break;
			case 1:
				//end
				break;
		}
	}

	//Utility

	void runGenOre(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, float chance, int times, int minHeight, int maxHeight)
	{
		if(rand.nextFloat() <  chance)
		{
			if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256)
			{
				throw  new IllegalArgumentException("NetherOre gen out of bounds");
			}

			int heightDiff = maxHeight - minHeight + 1;

			for (int i = 0; i < times; i++) {
				int x = chunkX * 16 + rand.nextInt(16);
				int y = minHeight + rand.nextInt(heightDiff);
				int z = chunkZ * 16 + rand.nextInt(16);

				gen.generate(world, rand, new BlockPos(x,y,z));
			}
		}
	}

	void runGenOre(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int times, int minHeight, int maxHeight)
	{
		runGenOre(gen, world, rand, chunkX, chunkZ, 1.0F, times, minHeight, maxHeight);
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

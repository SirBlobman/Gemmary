package com.SirBlobman.gemmary.world;

import java.util.Random;

import com.SirBlobman.gemmary.block.GBlocks;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGeneration implements IWorldGenerator
{
	private WorldGenerator amethystOre;
	private WorldGenerator corundumOre;
	private WorldGenerator talcOre;
	private WorldGenerator tanzaniteOre;
	private WorldGenerator topazOre;
	private WorldGenerator turquoiseOre;
	private WorldGenerator quartz_crystals;
	
	public OreGeneration()
	{
		amethystOre = new WorldGenMinable(GBlocks.amethystOre.getDefaultState(), 8);
		corundumOre = new WorldGenMinable(GBlocks.corundumOre.getDefaultState(), 8);
		talcOre = new WorldGenMinable(GBlocks.talcOre.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.SAND));
		tanzaniteOre = new WorldGenMinable(GBlocks.tanzaniteOre.getDefaultState(), 8);
		topazOre = new WorldGenMinable(GBlocks.topazOre.getDefaultState(), 8);
		turquoiseOre = new WorldGenMinable(GBlocks.turquoiseOre.getDefaultState(), 8);
		quartz_crystals = new WorldGenMinable(GBlocks.quartzCrystals.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.AIR));
	}
	
	@Override
	public void generate(Random r, int X, int Z, World w, IChunkGenerator icg, IChunkProvider icp)
	{
		switch(w.provider.getDimension())
		{
			case 0: //Overworld
				runGenerator(amethystOre, w, r, X, Z, 20, 0, 32);
				runGenerator(corundumOre, w, r, X, Z, 20, 0, 32);
				runGenerator(talcOre, w, r, X, Z, 20, 32, 128);
				runGenerator(tanzaniteOre, w, r, X, Z, 20, 0, 32);
				runGenerator(topazOre, w, r, X, Z, 20, 0, 32);
				runGenerator(turquoiseOre, w, r, X, Z, 20, 0, 32);
				runGenerator(quartz_crystals, w, r, X, Z, 20, 0, 32);
				break;
			case -1: //Nether
				break;
			case 1: //End
				break;
		}
	}
	
	private void runGenerator(WorldGenerator wg, World w, Random r, int X, int Z, int spawnChance, int minHeight, int maxHeight)
	{
		if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight){throw new IllegalArgumentException("Invalid height arguments for OreGeneration");}
		
		int heightDiff = maxHeight - minHeight + 1;
		
		for(int i = 0; i < spawnChance; i++)
		{
			int x = X * 16 + r.nextInt(16);
			int y = minHeight + r.nextInt(heightDiff);
			int z = Z * 16 + r.nextInt(16);
			
			wg.generate(w, r, new BlockPos(x,y,z));
		}
	}
}
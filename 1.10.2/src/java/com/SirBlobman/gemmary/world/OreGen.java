package com.SirBlobman.gemmary.world;

import java.util.Random;

import com.SirBlobman.gemmary.block.GBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGen implements IWorldGenerator
{
	private WorldGenerator amethyst = c(GBlocks.ameOre, 8, Blocks.STONE);
	private WorldGenerator corundum = c(GBlocks.corOre, 8, Blocks.STONE);
	private WorldGenerator talc = c(GBlocks.talOre, 8, Blocks.SAND);
	private WorldGenerator tanzanite = c(GBlocks.tanOre, 8, Blocks.STONE);
	private WorldGenerator topaz = c(GBlocks.topOre, 8, Blocks.STONE);
	private WorldGenerator turquoise = c(GBlocks.turOre, 8, Blocks.STONE);
	private WorldGenerator quartz = c(GBlocks.quartz, 8, Blocks.AIR);
	
	@Override
	public void generate(Random r, int x, int z, World w, IChunkGenerator ich, IChunkProvider icp)
	{
		WorldProvider wp = w.provider;
		int dimension = wp.getDimension();
		switch(dimension)
		{
		case 0:
			run(amethyst, w, r, x, z, 20, 0, 32);
			run(corundum, w, r, x, z, 20, 0, 32);
			run(talc, w, r, x, z, 20, 32, 128);
			run(tanzanite, w, r, x, z, 20, 0, 32);
			run(topaz, w, r, x, z, 20, 0, 32);
			run(turquoise, w, r, x, z, 20, 0, 32);
			run(quartz, w, r, x, z, 20, 0, 32);
			break;
		case 1: break;
		case -1: break;
		}
	}
	
	private WorldGenerator c(Block b, int i, Block... replace)
	{
		IBlockState ibs = b.getDefaultState();
		BlockMatcher bm = BlockMatcher.forBlock(b);
		WorldGenMinable wgm = new WorldGenMinable(ibs, i, bm);
		return wgm;
	}
	
	private void run(WorldGenerator wg, World w, Random r, int x, int z, int chance, int min, int max)
	{
		boolean b1 = min < 0;
		boolean b2 = min > 256;
		boolean b3 = min > max;
		if(b1 || b2 || b3)
		{
			String invalid = "Invalid Height Arguments for OreGen.run";
			IllegalArgumentException ex = new IllegalArgumentException(invalid);
			throw ex;
		}
		
		int height = max - min + 1;
		for(int i = 0; i < chance; i++)
		{
			int X = x * 16 + r.nextInt(16);
			int Y = min + r.nextInt(height);
			int Z = z * 16 + r.nextInt(16);
			BlockPos bp = new BlockPos(X, Y, Z);
			wg.generate(w, r, bp);
		}
	}
}
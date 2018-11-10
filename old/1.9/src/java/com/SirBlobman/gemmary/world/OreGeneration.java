package com.SirBlobman.gemmary.world;

import java.util.Random;

import com.SirBlobman.gemmary.block.GBlocks;
import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

@SuppressWarnings({"unused"})
public class OreGeneration implements IWorldGenerator
{
	
	private WorldGenerator amethyst_ore;
	private WorldGenerator corundum_ore;
	private WorldGenerator ruby_ore;
	private WorldGenerator sapphire_ore;
	private WorldGenerator talc_ore;
	private WorldGenerator tanzanite_ore;
	private WorldGenerator topaz_ore;
	private WorldGenerator turquoise_ore;
	private WorldGenerator quartz_crystals;
	
	public OreGeneration()
	{
		amethyst_ore = new WorldGenMinable(GBlocks.amethyst_ore.getDefaultState(), 8);
		ruby_ore = new WorldGenMinable(GBlocks.ruby_ore.getDefaultState(), 8);
		corundum_ore = new WorldGenMinable(GBlocks.corundum_ore.getDefaultState(), 8);
		sapphire_ore = new WorldGenMinable(GBlocks.sapphire_ore.getDefaultState(), 8);
		talc_ore = new WorldGenMinable(GBlocks.talc_ore.getDefaultState(), 8);
		tanzanite_ore = new WorldGenMinable(GBlocks.tanzanite_ore.getDefaultState(), 8);
		topaz_ore = new WorldGenMinable(GBlocks.topaz_ore.getDefaultState(), 8);
		turquoise_ore = new WorldGenMinable(GBlocks.turquoise_ore.getDefaultState(), 8);
		quartz_crystals = new WorldGenMinable(GBlocks.quartzCrystals.getDefaultState(), 8);
	}
	
	@Override
	public void generate(Random r, int X, int Z, World w, IChunkGenerator icg, IChunkProvider icp)
	{
		switch(w.provider.getDimension())
		{
			case 0: //Overworld
				runGenerator(amethyst_ore, w, r, X, Z, 20, 0, 32);
				runGenerator(ruby_ore, w, r, X, Z, 20, 0, 32);
				runGenerator(sapphire_ore, w, r, X, Z, 20, 0, 32);
				runGenerator(talc_ore, w, r, X, Z, 20, 0, 32);
				runGenerator(tanzanite_ore, w, r, X, Z, 20, 0, 32);
				runGenerator(topaz_ore, w, r, X, Z, 20, 0, 32);
				runGenerator(turquoise_ore, w, r, X, Z, 20, 0, 32);
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
		if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
		{
			throw new IllegalArgumentException("Invalid height arguments for OreGeneration");
		}
		
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

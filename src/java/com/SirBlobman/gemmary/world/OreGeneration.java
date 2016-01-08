package com.SirBlobman.gemmary.world;

import java.util.Random;

import com.SirBlobman.gemmary.ores.*;

import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGeneration implements IWorldGenerator {
	//@formatter:off

	private WorldGenerator gen_amethyst_ore;
	private WorldGenerator gen_ruby_ore;
	private WorldGenerator gen_sapphire_ore;
	private WorldGenerator gen_talc_ore;
	private WorldGenerator gen_tanzanite_ore;
	private WorldGenerator gen_topaz_ore;
	private WorldGenerator gen_turquoise_ore;
	private WorldGenerator crystals;

	//@formatter:on

	public OreGeneration() {

		this.gen_amethyst_ore = new WorldGenMinable(GemmaryOres.amethystOre.getDefaultState(), 8);
		this.gen_ruby_ore = new WorldGenMinable(GemmaryOres.rubyOre.getDefaultState(), 8);
		this.gen_sapphire_ore = new WorldGenMinable(GemmaryOres.sapphireOre.getDefaultState(), 8);
		this.gen_talc_ore = new WorldGenMinable(GemmaryOres.talcOre.getDefaultState(), 8);
		this.gen_tanzanite_ore = new WorldGenMinable(GemmaryOres.tanzaniteOre.getDefaultState(), 8);
		this.gen_topaz_ore = new WorldGenMinable(GemmaryOres.topazOre.getDefaultState(), 8);
		this.gen_turquoise_ore = new WorldGenMinable(GemmaryOres.turquoiseOre.getDefaultState(), 8);
		this.crystals = new WorldGenMinable(GemmaryOres.quartzCrystal.getDefaultState(), 8, BlockHelper.forBlock(Blocks.air));
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimensionId()) {
		case 0: // Overworld
			this.runGenerator(this.gen_amethyst_ore, world, random, chunkX, chunkZ, 20, 0, 32);
			this.runGenerator(this.gen_ruby_ore, world, random, chunkX, chunkZ, 20, 0, 32);
			this.runGenerator(this.gen_sapphire_ore, world, random, chunkX, chunkZ, 20, 0, 32);
			this.runGenerator(this.gen_talc_ore, world, random, chunkX, chunkZ, 20, 0, 32);
			this.runGenerator(this.gen_tanzanite_ore, world, random, chunkX, chunkZ, 20, 0, 32);
			this.runGenerator(this.gen_topaz_ore, world, random, chunkX, chunkZ, 20, 0, 32);
			this.runGenerator(this.gen_turquoise_ore, world, random, chunkX, chunkZ, 20, 0, 32);
			this.runGenerator(this.crystals, world, random, chunkX, chunkZ, 20, 0, 32);
			break;
		case -1: // Nether
			break;
		case 1: // End
			break;
		}
	}

	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}

}
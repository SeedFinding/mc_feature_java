package com.seedfinding.mcfeature.structure;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.block.Block;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcterrain.TerrainGenerator;

public class NetherFossil extends UniformStructure<NetherFossil> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_16, new RegionStructure.Config(2, 1, 14357921));

	public NetherFossil(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public NetherFossil(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "nether_fossil";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.NETHER;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.SOUL_SAND_VALLEY;
	}

	@Override
	public boolean isValidTerrain(TerrainGenerator generator, int chunkX, int chunkZ) {
		if(generator == null) return true;
		ChunkRand rand = new ChunkRand();
		rand.setCarverSeed(generator.getWorldSeed(), chunkX, chunkZ, this.getVersion());
		int x = (chunkX << 4) + rand.nextInt(16);
		int z = (chunkZ << 4) + rand.nextInt(16);
		int seaLevel = generator.getSeaLevel();
		int y = seaLevel + rand.nextInt(generator.getWorldHeight() - 2 - seaLevel);
		Block[] column = generator.getColumnAt(x, z);
		for(; y > seaLevel; --y) {
			Block block = column[y];
			Block blockDown = column[y - 1];
			if(block == Blocks.AIR && blockDown == Blocks.NETHERRACK) {
				break;
			}
		}
		return y > seaLevel;
	}
}

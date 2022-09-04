package com.seedfinding.mcfeature.decorator;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcterrain.TerrainGenerator;

public class Dungeon extends Decorator<Decorator.Config, Dungeon.Data> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_16, new Config(0, 3)
			.add(1, 3, Biomes.DESERT)
			.add(1, 3, Biomes.SWAMP)
			.add(1, 3, Biomes.SWAMP_HILLS));

	public Dungeon(Config config, MCVersion version) {
		super(config, version);
	}

	public Dungeon(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return "monster_room";
	}


	@Override
	public boolean canStart(Data data, long structureSeed, ChunkRand rand) {
		return true;
	}

	@Override
	public boolean canGenerate(Data data, TerrainGenerator generator) {
		return true;
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	@Override
	public Data getData(long structureSeed, int chunkX, int chunkZ, Biome biome, ChunkRand rand) {
		return null;
	}

	public Dungeon.Data at(int blockX, int blockZ, Biome biome) {
		return new Dungeon.Data(this, blockX, blockZ, biome);
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome.getDimension() == Dimension.OVERWORLD;
	}


	public static class Data extends Decorator.Data<Dungeon> {

		public final int blockX;
		public final int blockZ;
		public final int offsetX;
		public final int offsetZ;

		public Data(Dungeon feature, int blockX, int blockZ, Biome biome) {
			super(feature, blockX >> 4, blockZ >> 4, biome);
			this.blockX = blockX;
			this.blockZ = blockZ;
			this.offsetX = blockX & 15;
			this.offsetZ = blockZ & 15;
		}
	}
}

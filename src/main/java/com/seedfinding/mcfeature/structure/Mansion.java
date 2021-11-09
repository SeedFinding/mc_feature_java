package com.seedfinding.mcfeature.structure;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mcbiome.source.BiomeSource;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;

public class Mansion extends TriangularStructure<Mansion> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_11, new RegionStructure.Config(80, 20, 10387319));

	public Mansion(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public Mansion(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "mansion";
	}

	@Override
	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		if(!super.canSpawn(chunkX, chunkZ, source)) return false;
		return source.iterateUniqueBiomes((chunkX << 4) + 9, (chunkZ << 4) + 9, 32, this::isValidBiome);
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.DARK_FOREST || biome == Biomes.DARK_FOREST_HILLS;
	}

}

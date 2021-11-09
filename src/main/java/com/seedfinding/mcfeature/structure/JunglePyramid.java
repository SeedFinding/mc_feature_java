package com.seedfinding.mcfeature.structure;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;

public class JunglePyramid extends OldStructure<JunglePyramid> {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
		.add(MCVersion.v1_8, new OldStructure.Config(14357617))
		.add(MCVersion.v1_13, new OldStructure.Config(14357619));

	public JunglePyramid(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public JunglePyramid(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "jungle_pyramid";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.JUNGLE || biome == Biomes.JUNGLE_HILLS || biome == Biomes.BAMBOO_JUNGLE
			|| biome == Biomes.BAMBOO_JUNGLE_HILLS;
	}

}

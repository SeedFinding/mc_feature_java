package com.seedfinding.mcfeature.structure;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.loot.ILoot;
import com.seedfinding.mcfeature.structure.generator.Generator;
import com.seedfinding.mcfeature.structure.generator.structure.DesertPyramidGenerator;

public class DesertPyramid extends OldStructure<DesertPyramid> implements ILoot {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
		.add(MCVersion.v1_8, new OldStructure.Config(14357617));

	public DesertPyramid(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public DesertPyramid(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "desert_pyramid";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.DESERT || biome == Biomes.DESERT_HILLS;
	}

	@Override
	public int getDecorationSalt() {
		return this.getVersion().isNewerOrEqualTo(MCVersion.v1_16) ? 40003 : 30002;
	}

	@Override
	public boolean isCorrectGenerator(Generator generator) {
		return generator instanceof DesertPyramidGenerator;
	}

	@Override
	public SpecificCalls getSpecificCalls() {
		return null;
	}

	@Override
	public boolean shouldAdvanceInChunks() {
		return false;
	}
}

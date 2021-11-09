package com.seedfinding.mcfeature.decorator.ore.overworld;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.SphereOreDecorator;

public class GravelDisk extends SphereOreDecorator<SphereOreDecorator.Config, SphereOreDecorator.Data<GravelDisk>> {
	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(13, 4, 2, 1, HeightProvider.spreadRange(2, 3), Blocks.GRAVEL, DIRT_GRASS))
		.add(MCVersion.v1_16, new Config(13, 6, 2, 1, HeightProvider.spreadRange(2, 3), Blocks.GRAVEL, DIRT_GRASS))
		.add(MCVersion.v1_17, new Config(16, 6, 2, 1, HeightProvider.spreadRange(2, 3), Blocks.GRAVEL, DIRT_GRASS));

	public GravelDisk(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return super.isValidBiome(biome) && biome.getCategory() != Biome.Category.SWAMP;
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "gravel_disk";
	}
}

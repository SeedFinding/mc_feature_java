package com.seedfinding.mcfeature.decorator.ore.overworld;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.SphereOreDecorator;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.VersionMap;

public class ClayDisk extends SphereOreDecorator<SphereOreDecorator.Config, SphereOreDecorator.Data<ClayDisk>> {
	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(12, 4, 2, 1, HeightProvider.spreadRange(2, 1), Blocks.CLAY, DIRT_CLAY)
			.add(11, 4, Biomes.SWAMP, Biomes.SWAMP_HILLS))
		.add(MCVersion.v1_16, new Config(12, 6, 2, 1, HeightProvider.spreadRange(2, 1), Blocks.CLAY, DIRT_CLAY)
			.add(11, 6, Biomes.SWAMP, Biomes.SWAMP_HILLS))
		.add(MCVersion.v1_17, new Config(15, 6, 2, 1, HeightProvider.spreadRange(2, 1), Blocks.CLAY, DIRT_CLAY)
			.add(14, 6, Biomes.SWAMP, Biomes.SWAMP_HILLS));

	public ClayDisk(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "clay_disk";
	}
}

package com.seedfinding.mcfeature.decorator.ore.nether;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.VersionMap;

public class QuartzOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<QuartzOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(7, 5, 14, 16, HeightProvider.range(10, 20, 128), Blocks.NETHER_QUARTZ_ORE, NETHERRACK))
		.add(MCVersion.v1_16, new Config(14, 7, 14, 16, HeightProvider.range(10, 20, 128), Blocks.NETHER_QUARTZ_ORE, NETHERRACK)
			.add(11, 7, Biomes.CRIMSON_FOREST)
			.add(12, 7, Biomes.WARPED_FOREST)
			.add(14, 7, 14, 32, Biomes.BASALT_DELTAS))
		.add(MCVersion.v1_17, new Config(14, 7, 14, 16, HeightProvider.uniformRange(10, 117), Blocks.NETHER_QUARTZ_ORE, NETHERRACK)
			.add(11, 7, Biomes.CRIMSON_FOREST)
			.add(12, 7, Biomes.WARPED_FOREST)
			.add(14, 7, 14, 32, Biomes.BASALT_DELTAS));

	public QuartzOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "quartz_ore";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.NETHER;
	}

}

package com.seedfinding.mcfeature.decorator.ore.nether;

import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;

public class NetherGoldOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<NetherGoldOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_16, new Config(13, 7, 10, 10, HeightProvider.range(10, 20, 128), Blocks.NETHER_GOLD_ORE, NETHERRACK)
			.add(10, 7, Biomes.CRIMSON_FOREST)
			.add(11, 7, Biomes.WARPED_FOREST)
			.add(13, 7, 10, 20, Biomes.BASALT_DELTAS))
		.add(MCVersion.v1_17, new Config(13, 7, 10, 10, HeightProvider.uniformRange(10, 117), Blocks.NETHER_GOLD_ORE, NETHERRACK)
			.add(10, 7, Biomes.CRIMSON_FOREST)
			.add(11, 7, Biomes.WARPED_FOREST)
			.add(13, 7, 10, 20, Biomes.BASALT_DELTAS));

	public NetherGoldOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "nether_gold_ore";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.NETHER;
	}

}

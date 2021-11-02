package com.seedfinding.mcfeature.decorator.ore.overworld;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.VersionMap;

public class DiamondOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<DiamondOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(9, 4, 8, 1, HeightProvider.range(0, 0, 16), Blocks.DIAMOND_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(9, 6, 8, 1, HeightProvider.range(0, 0, 16), Blocks.DIAMOND_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(11, 6, 8, 1, HeightProvider.uniformRange(0, 16), Blocks.DIAMOND_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17_1, new Config(11, 6, 8, 1, HeightProvider.uniformRange(0, 15), Blocks.DIAMOND_ORE, BASE_STONE_OVERWORLD));

	public DiamondOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "diamond_ore";
	}

}

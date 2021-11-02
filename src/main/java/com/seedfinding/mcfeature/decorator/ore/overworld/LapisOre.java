package com.seedfinding.mcfeature.decorator.ore.overworld;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.VersionMap;

public class LapisOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<LapisOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(10, 4, 7, 1, HeightProvider.depthAverage(16, 16), Blocks.LAPIS_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(10, 6, 7, 1, HeightProvider.depthAverage(16, 16), Blocks.LAPIS_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(12, 6, 7, 1, HeightProvider.triangleRange(0, 30), Blocks.LAPIS_ORE, BASE_STONE_OVERWORLD));

	public LapisOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "lapis_ore";
	}

}

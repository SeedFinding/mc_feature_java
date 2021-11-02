package com.seedfinding.mcfeature.decorator.ore.overworld;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.VersionMap;

public class GraniteOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<GraniteOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(2, 4, 33, 10, HeightProvider.range(0, 0, 80), Blocks.GRANITE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(2, 6, 33, 10, HeightProvider.range(0, 0, 80), Blocks.GRANITE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(2, 6, 33, 10, HeightProvider.uniformRange(0, 79), Blocks.GRANITE, BASE_STONE_OVERWORLD));

	public GraniteOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "granite_ore";
	}

}

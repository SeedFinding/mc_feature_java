package com.seedfinding.mcfeature.decorator.ore.overworld;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.VersionMap;

public class GravelOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<GravelOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(1, 4, 33, 8, HeightProvider.range(0, 0, 256), Blocks.GRAVEL, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(1, 6, 33, 8, HeightProvider.range(0, 0, 256), Blocks.GRAVEL, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(1, 6, 33, 8, HeightProvider.uniformRange(0, 255), Blocks.GRAVEL, BASE_STONE_OVERWORLD));

	public GravelOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "gravel_ore";
	}

}

package com.seedfinding.mcfeature.decorator.ore.overworld;

import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;

public class RedstoneOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<RedstoneOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(8, 4, 8, 8, HeightProvider.range(0, 0, 16), Blocks.REDSTONE_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(8, 6, 8, 8, HeightProvider.range(0, 0, 16), Blocks.REDSTONE_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(10, 6, 8, 8, HeightProvider.uniformRange(0, 15), Blocks.REDSTONE_ORE, BASE_STONE_OVERWORLD));

	public RedstoneOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "redstone_ore";
	}

}

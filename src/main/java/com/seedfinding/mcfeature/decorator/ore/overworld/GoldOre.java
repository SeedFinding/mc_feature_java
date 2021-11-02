package com.seedfinding.mcfeature.decorator.ore.overworld;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.VersionMap;

public class GoldOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<GoldOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(7, 4, 9, 2, HeightProvider.range(0, 0, 32), Blocks.GOLD_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(7, 6, 9, 2, HeightProvider.range(0, 0, 32), Blocks.GOLD_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(9, 6, 9, 2, HeightProvider.uniformRange(0, 31), Blocks.GOLD_ORE, BASE_STONE_OVERWORLD));

	public GoldOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "gold_ore";
	}

}

package com.seedfinding.mcfeature.decorator.ore.overworld;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.VersionMap;

public class DeepslateOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<DeepslateOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_17, new Config(6, 6, 64, 2, HeightProvider.uniformRange(0, 16), Blocks.DEEPSLATE, BASE_STONE_OVERWORLD));

	public DeepslateOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "deepslate_ore";
	}

}

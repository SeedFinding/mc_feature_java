package com.seedfinding.mcfeature.decorator.ore.overworld;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.VersionMap;

public class TuffOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<TuffOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_17, new Config(5, 6, 33, 1, HeightProvider.uniformRange(0, 16), Blocks.TUFF, BASE_STONE_OVERWORLD));

	public TuffOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "tuff_ore";
	}

}

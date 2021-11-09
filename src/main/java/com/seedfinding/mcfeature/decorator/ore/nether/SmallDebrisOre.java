package com.seedfinding.mcfeature.decorator.ore.nether;

import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.ScatterOreDecorator;

public class SmallDebrisOre extends ScatterOreDecorator<ScatterOreDecorator.Config, ScatterOreDecorator.Data<SmallDebrisOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_16, new Config(16, 7, 2, 1, HeightProvider.range(8, 16, 128), Blocks.ANCIENT_DEBRIS, BASE_STONE_NETHER)
			.add(13, 7, Biomes.CRIMSON_FOREST)
			.add(14, 7, Biomes.WARPED_FOREST))
		.add(MCVersion.v1_17, new Config(16, 7, 2, 1, HeightProvider.uniformRange(8, 119), Blocks.ANCIENT_DEBRIS, BASE_STONE_NETHER)
			.add(13, 7, Biomes.CRIMSON_FOREST)
			.add(14, 7, Biomes.WARPED_FOREST));

	public SmallDebrisOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "small_debris_ore";
	}

}

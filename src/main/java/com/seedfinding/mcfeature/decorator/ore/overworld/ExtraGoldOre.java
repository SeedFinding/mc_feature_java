package com.seedfinding.mcfeature.decorator.ore.overworld;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;

public class ExtraGoldOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<ExtraGoldOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_13, new Config(11, 4, 9, 20, HeightProvider.range(32, 32, 80), Blocks.GOLD_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_16, new Config(11, 6, 9, 20, HeightProvider.range(32, 32, 80), Blocks.GOLD_ORE, BASE_STONE_OVERWORLD))
		.add(MCVersion.v1_17, new Config(14, 6, 9, 20, HeightProvider.uniformRange(32, 79), Blocks.GOLD_ORE, BASE_STONE_OVERWORLD));

	public ExtraGoldOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "extra_gold_ore";
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.BADLANDS || biome == Biomes.WOODED_BADLANDS_PLATEAU || biome == Biomes.BADLANDS_PLATEAU || biome == Biomes.ERODED_BADLANDS || biome == Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU || biome == Biomes.MODIFIED_BADLANDS_PLATEAU;
	}

}

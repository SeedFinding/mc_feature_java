package com.seedfinding.mcfeature.structure;


import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.ILoot;
import com.seedfinding.mcfeature.structure.generator.Generator;
import com.seedfinding.mcfeature.structure.generator.structure.ShipwreckGenerator;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.VersionMap;

public class Shipwreck extends UniformStructure<Shipwreck> implements ILoot {
	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_13, new RegionStructure.Config(15, 8, 165745295))
		.add(MCVersion.v1_13_1, new RegionStructure.Config(16, 8, 165745295))
		.add(MCVersion.v1_16, new RegionStructure.Config(24, 4, 165745295));


	public Shipwreck(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public Shipwreck(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "shipwreck";
	}

	@Override
	public boolean canStart(Data<Shipwreck> data, long structureSeed, ChunkRand rand) {
		return super.canStart(data, structureSeed, rand);
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome.getCategory() == Biome.Category.OCEAN || biome == Biomes.BEACH || biome == Biomes.SNOWY_BEACH;
	}

	@Override
	public int getDecorationSalt() {
		return 40006;
	}

	@Override
	public boolean isCorrectGenerator(Generator generator) {
		return generator instanceof ShipwreckGenerator;
	}

	@Override
	public SpecificCalls getSpecificCalls() {
		return (generator, rand) -> {
			if(isCorrectGenerator(generator)) {
				if(((ShipwreckGenerator)generator).isBeached()) {
					rand.nextInt(3);
				}
			}
		};
	}
}

package com.seedfinding.mcfeature.structure;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.loot.ILoot;
import com.seedfinding.mcfeature.structure.generator.Generator;
import com.seedfinding.mcfeature.structure.generator.structure.RuinedPortalGenerator;
import com.seedfinding.mcterrain.TerrainGenerator;

public class RuinedPortal extends UniformStructure<RuinedPortal> implements ILoot {

	public static final VersionMap<RegionStructure.Config> OVERWORLD_CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_16, new RegionStructure.Config(40, 15, 34222645));

	public static final VersionMap<RegionStructure.Config> NETHER_CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_16, new RegionStructure.Config(25, 10, 34222645));

	private final Dimension dimension;

	public RuinedPortal(Dimension dimension, MCVersion version) {
		this(dimension, getConfigs(dimension).getAsOf(version), version);
	}

	public RuinedPortal(Dimension dimension, RegionStructure.Config config, MCVersion version) {
		super(config, version);
		this.dimension = dimension;
	}

	public static String name() {
		return "ruined_portal";
	}

	public static VersionMap<RegionStructure.Config> getConfigs(Dimension dimension) {
		switch(dimension) {
			case OVERWORLD:
				return OVERWORLD_CONFIGS;
			case NETHER:
				return NETHER_CONFIGS;
		}

		return new VersionMap<>();
	}

	@Override
	public Dimension getValidDimension() {
		return this.dimension;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome != Biomes.THE_VOID && biome.getCategory() != Biome.Category.THE_END;
	}

	@Override
	public boolean isValidTerrain(TerrainGenerator generator, int chunkX, int chunkZ) {
		RuinedPortalGenerator ruinedPortalGenerator = new RuinedPortalGenerator(this.getVersion());
		return ruinedPortalGenerator.generate(generator, chunkX, chunkZ);
	}

	@Override
	public int getDecorationSalt() {
		return 40005;
	}

	@Override
	public boolean isCorrectGenerator(Generator generator) {
		return generator instanceof RuinedPortalGenerator;
	}

	@Override
	public SpecificCalls getSpecificCalls() {
		return null;
	}

	@Override
	public boolean shouldAdvanceInChunks() {
		return false;
	}
}

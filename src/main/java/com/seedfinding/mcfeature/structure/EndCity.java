package com.seedfinding.mcfeature.structure;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mcbiome.source.BiomeSource;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.util.block.BlockRotation;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.loot.ILoot;
import com.seedfinding.mcfeature.structure.generator.Generator;
import com.seedfinding.mcfeature.structure.generator.structure.EndCityGenerator;
import com.seedfinding.mcterrain.TerrainGenerator;

public class EndCity extends TriangularStructure<EndCity> implements ILoot {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_9, new RegionStructure.Config(20, 11, 10387313));

	public EndCity(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public EndCity(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "end_city";
	}

	@Override
	public boolean canStart(Data<EndCity> data, long structureSeed, ChunkRand rand) {
		return super.canStart(data, structureSeed, rand);
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.END;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.END_MIDLANDS || biome == Biomes.END_HIGHLANDS;
	}

	@Override
	public boolean isValidTerrain(TerrainGenerator generator, int chunkX, int chunkZ) {
		return getAverageYPosition(generator, chunkX, chunkZ) >= 60;
	}

	@Override
	public boolean isCorrectGenerator(Generator generator) {
		return generator instanceof EndCityGenerator;
	}

	@Override
	public boolean canSpawn(CPos cPos, BiomeSource source) {
		return this.canSpawn(cPos.getX(), cPos.getZ(), source);
	}

	@Override
	public boolean canSpawn(int chunkX, int chunkZ, BiomeSource source) {
		if(this.getVersion().isOlderThan(MCVersion.v1_16)) {
			if(this.getVersion().isNewerOrEqualTo(MCVersion.v1_13)) {
				this.biome = source.getBiome((chunkX << 4) + 9, 0, (chunkZ << 4) + 9);
				return this.isValidBiome(this.getBiome());
			} else {
				// back then it was isIslandChunk(chunkX,chunkZ)
				// but since we do x>>=2 we need to counter that here
				this.biome = source.getBiomeForNoiseGen((chunkX << 2), 0, (chunkZ << 2));
				return this.isValidBiome(this.biome);
			}
		}
		this.biome = source.getBiomeForNoiseGen((chunkX << 2) + 2, 0, (chunkZ << 2) + 2);
		return this.isValidBiome(this.biome);
	}

	@Override
	public SpecificCalls getSpecificCalls() {
		return null;
	}

	public static int getAverageYPosition(TerrainGenerator generator, int chunkX, int chunkZ) {
		@SuppressWarnings("IntegerMultiplicationImplicitCastToLong")
		ChunkRand random = new ChunkRand(chunkX + chunkZ * 10387313);
		BlockRotation rotation = BlockRotation.getRandom(random);
		int xOffset = 5;
		int zOffset = 5;
		if(rotation == BlockRotation.CLOCKWISE_90) {
			xOffset = -5;
		} else if(rotation == BlockRotation.CLOCKWISE_180) {
			xOffset = -5;
			zOffset = -5;
		} else if(rotation == BlockRotation.COUNTERCLOCKWISE_90) {
			zOffset = -5;
		}

		int posX = (chunkX << 4) + 7;
		int posZ = (chunkZ << 4) + 7;
		int center = generator.getHeightInGround(posX, posZ);
		int s = generator.getHeightInGround(posX, posZ + zOffset); // SOUTH
		int e = generator.getHeightInGround(posX + xOffset, posZ); //  EAST
		int se = generator.getHeightInGround(posX + xOffset, posZ + zOffset); // SOUTH EAST
		return Math.min(Math.min(center, s), Math.min(e, se));
	}

	@Override
	public int getDecorationSalt() {
		return this.getVersion().isNewerOrEqualTo(MCVersion.v1_16) ? 40010 : (this.getBiome() == Biomes.THE_END ? 30001 : 30000);
	}
}

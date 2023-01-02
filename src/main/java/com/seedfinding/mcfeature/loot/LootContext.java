package com.seedfinding.mcfeature.loot;


import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.source.BiomeSource;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.version.MCVersion;

public class LootContext extends ChunkRand {
	public static final int DEFAULT_LUCK = 1;
	private final MCVersion version;
	private int luck = DEFAULT_LUCK;
	private Biome biome = null;

	public LootContext(long lootTableSeed) {
		super(lootTableSeed);
		this.version = MCVersion.latest();
	}

	public LootContext(long lootTableSeed, MCVersion version) {
		super(lootTableSeed);
		this.version = version;
	}

	public int getLuck() {
		return luck;
	}

	public Biome getBiome() {
		return biome;
	}

	public LootContext withLuck(int luck) {
		this.luck = luck;
		return this;
	}

	public LootContext withBiome(Biome biome) {
		this.biome = biome;
		return this;
	}

	public MCVersion getVersion() {
		return version;
	}
}

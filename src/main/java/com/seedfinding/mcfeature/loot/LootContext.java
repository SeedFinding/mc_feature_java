package com.seedfinding.mcfeature.loot;


import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.version.MCVersion;

public class LootContext extends ChunkRand {
	public static final int DEFAULT_LUCK = 1;
	private final MCVersion version;
	private int luck = DEFAULT_LUCK;
	private Biome biome = null;
	private boolean inOpenWater = false;

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

	public boolean isInOpenWater() {
		return inOpenWater;
	}

	public LootContext withLuck(int luck) {
		this.luck = luck;
		return this;
	}

	public LootContext withBiome(Biome biome) {
		this.biome = biome;
		return this;
	}

	public LootContext withOpenWater(boolean openWater) {
		this.inOpenWater = openWater;
		return this;
	}

	public MCVersion getVersion() {
		return version;
	}
}

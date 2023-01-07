package com.seedfinding.mcfeature.loot.condition;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcfeature.loot.LootContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BiomeCondition implements LootCondition {
	public final List<Biome> biomes;

	/**
	 * If any of the biomes match return the item else return invalid item
	 */
	public BiomeCondition(Biome... biomes) {
		this.biomes = new ArrayList<>();
		Collections.addAll(this.biomes, biomes);
	}

	@Override
	public boolean is_valid(LootContext context) {
		Biome biome = context.getBiome();
		return biome != null && this.biomes.contains(biome);
	}
}

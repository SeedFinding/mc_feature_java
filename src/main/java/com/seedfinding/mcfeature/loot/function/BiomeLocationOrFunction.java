package com.seedfinding.mcfeature.loot.function;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BiomeLocationOrFunction implements LootFunction {
	public final List<Biome> biomes;

	/**
	 * If any of the biomes match return the item else return invalid item
	 *
	 * @param biomes
	 */
	public BiomeLocationOrFunction(Biome... biomes) {
		this.biomes = new ArrayList<>();
		Collections.addAll(this.biomes, biomes);
	}

	@Override
	public ItemStack process(ItemStack baseStack, LootContext context) {
		Biome biome = context.getBiome();
		if(biome != null && this.biomes.contains(context.getBiome())) {
			return baseStack;

		}
		return ItemStack.INVALID;
	}

}

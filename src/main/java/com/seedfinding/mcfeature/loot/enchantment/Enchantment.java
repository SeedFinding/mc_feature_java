package com.seedfinding.mcfeature.loot.enchantment;

import com.seedfinding.mcfeature.misc.IntBiPredicate;

import java.util.Collections;
import java.util.HashSet;

public class Enchantment {
	private final String name;
	private final int rarity;
	private final HashSet<String> category;
	private final int minLevel;
	private final int maxLevel;
	private final IntBiPredicate isLowerThanMinCost;
	private final IntBiPredicate isHigherThanMaxCost;
	private final HashSet<String> incompatible;
	private final boolean isTreasure;
	private final boolean isDiscoverable;


	public Enchantment(String name, int rarity, HashSet<String> category, int minLevel, int maxLevel, HashSet<String> incompatible) {
		this(name, rarity, category, minLevel, maxLevel, (n, i) -> (n < 1 + (i * 10)), (n, i) -> (n > (6 + (i * 10))), incompatible);
	}

	public Enchantment(String name, int rarity, HashSet<String> category, int minLevel, int maxLevel, IntBiPredicate minCost, IntBiPredicate maxCost, HashSet<String> incompatible) {
		this(name, rarity, category, minLevel, maxLevel, minCost, maxCost, incompatible, false, true);
	}

	public Enchantment(String name, int rarity, HashSet<String> category, int minLevel, int maxLevel, IntBiPredicate minCost, IntBiPredicate maxCost, HashSet<String> incompatible, boolean isTreasure) {
		this(name, rarity, category, minLevel, maxLevel, minCost, maxCost, incompatible, isTreasure, true);
	}

	public Enchantment(String name, int rarity, HashSet<String> category, int minLevel, int maxLevel, IntBiPredicate minCost, IntBiPredicate maxCost, HashSet<String> incompatible, boolean isTreasure, boolean isDiscoverable) {
		this.name = name;
		this.rarity = rarity;
		this.category = category;
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		this.isLowerThanMinCost = minCost;
		this.isHigherThanMaxCost = maxCost;
		this.incompatible = incompatible == null ? new HashSet<>(Collections.singletonList(name)) : incompatible;
		this.isTreasure = isTreasure;
		this.isDiscoverable = isDiscoverable;
	}

	public String getName() {
		return name;
	}

	public int getRarity() {
		return rarity;
	}

	public HashSet<String> getCategory() {
		return category;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public boolean isTreasure() {
		return isTreasure;
	}

	public IntBiPredicate getIsLowerThanMinCost() {
		return isLowerThanMinCost;
	}

	public IntBiPredicate getIsHigherThanMaxCost() {
		return isHigherThanMaxCost;
	}

	public boolean isDiscoverable() {
		return isDiscoverable;
	}

	public HashSet<String> getIncompatible() {
		return incompatible;
	}
}

package com.seedfinding.mcfeature.loot.function;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.enchantment.Enchantment;
import com.seedfinding.mcfeature.loot.enchantment.Enchantments;
import com.seedfinding.mcfeature.loot.item.Item;

import java.util.List;

public abstract class EnchantmentFunction implements LootFunction {
	protected boolean isTreasure;
	protected boolean isDiscoverable;
	protected final Item item;

	public EnchantmentFunction(Item item) {
		this(item, true, true);
	}

	public EnchantmentFunction(Item item, boolean isTreasure) {
		this(item, isTreasure, true);
	}

	public EnchantmentFunction(Item item, boolean isTreasure, boolean isDiscoverable) {
		this.isTreasure = isTreasure;
		this.isDiscoverable = isDiscoverable;
		this.item = item;
	}

	public boolean isTreasure() {
		return isTreasure;
	}

	public boolean isDiscoverable() {
		return isDiscoverable;
	}

	public EnchantmentFunction apply(MCVersion version) {
		List<Enchantment> enchantments = Enchantments.getFor(version);
		return applyEnchantment(enchantments);
	}


	public abstract EnchantmentFunction applyEnchantment(List<Enchantment> enchantments);


}

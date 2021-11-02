package com.seedfinding.mcfeature.loot.function;

import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.item.ItemStack;

public class ApplyDamageFunction implements LootFunction {
	public ApplyDamageFunction() {}

	@Override
	public ItemStack process(ItemStack baseStack, LootContext context) {
		context.advance(1);
		return baseStack;
	}
}

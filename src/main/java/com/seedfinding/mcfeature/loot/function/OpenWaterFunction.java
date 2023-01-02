package com.seedfinding.mcfeature.loot.function;

import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.item.ItemStack;

public class OpenWaterFunction implements LootFunction {
	private final boolean inOpenwater;

	/**
	 * If match the openWater predicate
	 *
	 * @param inOpenwater
	 */
	public OpenWaterFunction(boolean inOpenwater) {
		this.inOpenwater = inOpenwater;
	}

	@Override
	public ItemStack process(ItemStack baseStack, LootContext context) {
		if(context.isInOpenWater() == inOpenwater) {
			return baseStack;
		}
		return ItemStack.INVALID;
	}

}

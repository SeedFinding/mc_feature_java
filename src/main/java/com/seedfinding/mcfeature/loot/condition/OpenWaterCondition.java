package com.seedfinding.mcfeature.loot.condition;

import com.seedfinding.mcfeature.loot.LootContext;

public class OpenWaterCondition implements LootCondition {
	public final boolean inOpenwater;

	public OpenWaterCondition(boolean inOpenwater) {
		this.inOpenwater = inOpenwater;
	}


	@Override
	public boolean is_valid(LootContext context) {
		return context.isInOpenWater() == this.inOpenwater;
	}
}

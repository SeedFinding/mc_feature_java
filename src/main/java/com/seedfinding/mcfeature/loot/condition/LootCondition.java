package com.seedfinding.mcfeature.loot.condition;

import com.seedfinding.mcfeature.loot.LootContext;

public interface LootCondition {
	static LootCondition combine(LootCondition[] lootConditions) {
		return (context) -> {
			boolean condition = true;
			for(LootCondition lootFunction : lootConditions) {
				condition &= lootFunction.is_valid(context);
			}
			return condition;
		};
	}

	boolean is_valid(LootContext context);
}

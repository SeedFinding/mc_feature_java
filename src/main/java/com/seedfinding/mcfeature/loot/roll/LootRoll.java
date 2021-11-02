package com.seedfinding.mcfeature.loot.roll;

import com.seedfinding.mcfeature.loot.LootContext;

public abstract class LootRoll {

	public abstract int getCount(LootContext context);

	public float getFloat(LootContext context) {
		return 0.0F;
	}

}

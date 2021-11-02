package com.seedfinding.mcfeature.loot.entry;

import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.item.ItemStack;

import java.util.function.Consumer;

public class EmptyEntry extends LootEntry {

	public EmptyEntry(int weight) {
		super(weight);
	}

	@Override
	public void generate(LootContext context, Consumer<ItemStack> stackConsumer) {

	}
}

package com.seedfinding.mcfeature.loot.entry;

import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.LootTable;
import com.seedfinding.mcfeature.loot.item.ItemStack;

import java.util.function.Consumer;

public class TableEntry extends LootEntry {

	final LootTable table;

	public TableEntry(LootTable table) {
		this(table, 1);
	}

	public TableEntry(LootTable table, int weight) {
		super(weight);
		this.table = table;
	}

	// FIXME we don't check for circular references, this could end up in an infinite loop
	@Override
	public void generate(LootContext context, Consumer<ItemStack> stackConsumer) {
		this.table.generate(context, stackConsumer);
	}
}

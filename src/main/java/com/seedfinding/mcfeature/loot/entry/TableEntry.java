package com.seedfinding.mcfeature.loot.entry;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.LootGenerator;
import com.seedfinding.mcfeature.loot.LootTable;
import com.seedfinding.mcfeature.loot.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TableEntry extends LootEntry {

	public final Supplier<LootTable> table;

	public TableEntry(Supplier<LootTable> table) {
		this(table, 1);
	}

	public TableEntry(Supplier<LootTable> table, int weight) {
		super(weight);
		this.table = table;
	}

	// FIXME we don't check for circular references, this could end up in an infinite loop
	@Override
	public void generate(LootContext context, Consumer<ItemStack> stackConsumer) {
		this.table.get().apply(context.getVersion(),context.getLuck()).generate(context, stackConsumer);
	}
}

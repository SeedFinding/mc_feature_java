package com.seedfinding.mcfeature.loot;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.condition.LootCondition;
import com.seedfinding.mcfeature.loot.function.LootFunction;
import com.seedfinding.mcfeature.loot.item.ItemStack;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class LootGenerator {

	public Function<MCVersion, LootFunction>[] supplierLootFunctions = null;
	public LootFunction[] lootFunctions;
	public LootFunction combinedLootFunction;

	public Function<MCVersion, LootCondition>[] supplierLootConditions = null;
	public LootCondition[] lootConditions;
	public LootCondition combinedLootCondition;

	public LootGenerator() {
		this.apply((Collection<Function<MCVersion, LootFunction>>)null);
	}

	@SuppressWarnings("unchecked")
	public LootGenerator apply(Collection<Function<MCVersion, LootFunction>> lootFunctions) {
		if(lootFunctions != null) {
			this.supplierLootFunctions = lootFunctions.toArray(new Function[0]);
		} else {
			this.lootFunctions = new LootFunction[0];
			this.combinedLootFunction = (baseStack, context) -> baseStack;
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public LootGenerator when(Collection<Function<MCVersion, LootCondition>> lootConditions) {
		if(lootFunctions != null) {
			this.supplierLootConditions = lootConditions.toArray(new Function[0]);
		} else {
			this.lootConditions = new LootCondition[0];
			this.combinedLootCondition = (context) -> true;
		}
		return this;
	}


	public LootGenerator apply(MCVersion version) {
		if(supplierLootFunctions != null) {
			this.lootFunctions = new LootFunction[supplierLootFunctions.length];
			int i = 0;
			for(Function<MCVersion, LootFunction> function : supplierLootFunctions) {
				this.lootFunctions[i++] = function.apply(version);
			}
			this.combinedLootFunction = LootFunction.combine(this.lootFunctions);
		}

		if(supplierLootConditions != null) {
			this.lootConditions = new LootCondition[supplierLootConditions.length];
			int i = 0;
			for(Function<MCVersion, LootCondition> function : supplierLootConditions) {
				this.lootConditions[i++] = function.apply(version);
			}
			this.combinedLootCondition = LootCondition.combine(this.lootConditions);
		}
		return this;
	}

	public abstract void generate(LootContext context, Consumer<ItemStack> stackConsumer);
}

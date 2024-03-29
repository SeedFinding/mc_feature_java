package com.seedfinding.mcfeature.loot;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.entry.LootEntry;
import com.seedfinding.mcfeature.loot.function.LootFunction;
import com.seedfinding.mcfeature.loot.item.ItemStack;
import com.seedfinding.mcfeature.loot.roll.LootRoll;
import com.seedfinding.mcfeature.loot.roll.UniformRoll;
import com.seedfinding.mcnoise.utils.MathHelper;

import java.util.Arrays;
import java.util.function.Consumer;

public class LootPool extends LootGenerator {

	public final LootRoll rolls;
	public LootEntry[] lootEntries;
	public int totalWeight;
	public LootEntry[] precomputedWeights;
	public final LootRoll bonusRolls = new UniformRoll(0.0F, 0.0F);

	public LootPool(LootRoll rolls, LootEntry... lootEntries) {
		this.rolls = rolls;
		this.lootEntries = lootEntries;
	}

	public LootPool apply(MCVersion version) {
		this.lootEntries = Arrays.stream(lootEntries)
			.filter(lootEntry -> lootEntry.existsIn(version))
			.peek(lootEntry -> lootEntry.apply(version))
			.toArray(LootEntry[]::new);
		return this;
	}

	public LootPool processWeights(int luck, LootContext context) {
		this.totalWeight = 0;

		for(LootEntry entry : this.lootEntries) {
			// skip all the impossible entry with a specific context
			// Note context is null most of the time, only fishing needs it for now
			if(entry.combinedLootCondition != null && context != null) {
				if(!entry.combinedLootCondition.is_valid(context)) {
					continue;
				}
			}
			this.totalWeight += entry.getEffectiveWeight(luck);
		}

		this.precomputedWeights = new LootEntry[totalWeight];

		int k = 0;
		for(LootEntry entry : this.lootEntries) {
			// skip all the impossible entry with a specific context
			// Note context is null most of the time, only fishing needs it for now
			if(entry.combinedLootCondition != null && context != null) {
				if(!entry.combinedLootCondition.is_valid(context)) {
					continue;
				}
			}
			int weight = entry.getEffectiveWeight(luck);
			for(int i = 0; i < weight; i++) {
				this.precomputedWeights[k + i] = entry;
			}
			k += weight;
		}
		return this;
	}

	@Override
	public void generate(LootContext context, Consumer<ItemStack> stackConsumer) {
		stackConsumer = LootFunction.stack(stackConsumer, this.combinedLootFunction, context);

		int rolls = this.rolls.getCount(context) + MathHelper.floor(this.bonusRolls.getFloat(context) * context.getLuck());

		for(int i = 0; i < rolls; i++) {
			this.generatePool(context, stackConsumer);
		}
	}

	private void generatePool(LootContext context, Consumer<ItemStack> stackConsumer) {
		if(context.getVersion().isNewerOrEqualTo(MCVersion.v1_14)) {
			// unchecked for 1.14 and 1.15 (1.16 seems right)
			generatePool14(context, stackConsumer);
		} else {
			generatePool13(context, stackConsumer);
		}
	}

	private void generatePool13(LootContext context, Consumer<ItemStack> stackConsumer) {
		this.precomputedWeights[context.nextInt(totalWeight)].generate(context, stackConsumer);
	}

	private void generatePool14(LootContext context, Consumer<ItemStack> stackConsumer) {
		if(this.lootEntries.length == 1) {
			this.lootEntries[0].generate(context, stackConsumer);
		} else {
			int weight = context.nextInt(this.totalWeight);
			this.precomputedWeights[weight].generate(context, stackConsumer);
		}
	}

}

package com.seedfinding.mcfeature.loot.function;

import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.effect.Effect;
import com.seedfinding.mcfeature.loot.item.Item;
import com.seedfinding.mcfeature.loot.item.ItemStack;
import com.seedfinding.mcfeature.loot.item.Items;
import com.seedfinding.mcfeature.loot.roll.LootRoll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SetStewEffectFunction implements LootFunction {

	private final List<SetStewEffectFunction.EffectEntry> effects;

	public SetStewEffectFunction(EffectEntry... effects) {
		this.effects = Arrays.asList(effects);
	}

	@Override
	public ItemStack process(ItemStack baseStack, LootContext context) {
		if (!baseStack.getItem().equals(Items.SUSPICIOUS_STEW) || this.effects.isEmpty()) {
			return baseStack;
		}

		Item newItem = baseStack.getItem();

		ArrayList<Pair<Effect, Integer>> effects = new ArrayList<>();
		EffectEntry effectEntry = context.getRandom(this.effects);
		Effect effect = effectEntry.effect();
		int duration = effectEntry.duration().getCount(context);
		if (!effect.isInstantenous()) {
			duration *= 20;
		}
		effects.add(new Pair<>(effect, duration));
		newItem.setEffects(effects);

		return new ItemStack(newItem, baseStack.getCount());
	}

	public static final class EffectEntry {
		private final Effect effect;
		private final LootRoll duration;

		public EffectEntry(Effect effect, LootRoll duration) {
			this.effect = effect;
			this.duration = duration;
		}

		public Effect effect() {
			return this.effect;
		}

		public LootRoll duration() {
			return this.duration;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) return true;
			if (obj == null || obj.getClass() != this.getClass()) return false;
			EffectEntry that = (EffectEntry) obj;
			return Objects.equals(this.effect, that.effect) && Objects.equals(this.duration, that.duration);
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.effect, this.duration);
		}

		@Override
		public String toString() {
			return "EffectEntry[" + "effect=" + this.effect + ", " + "duration=" + this.duration + ']';
		}
	}
}

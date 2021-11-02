package com.seedfinding.mcfeature.loot.function;


import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.effect.Effect;
import com.seedfinding.mcfeature.loot.item.Item;
import com.seedfinding.mcfeature.loot.item.ItemStack;
import com.seedfinding.mcfeature.loot.item.Items;
import com.seedfinding.mcfeature.loot.roll.UniformRoll;
import com.seedfinding.mccore.util.data.Pair;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EffectFunction implements LootFunction {
	private final LinkedHashMap<Effect, UniformRoll> effects = new LinkedHashMap<>();
	private final HashMap<Effect, UniformRoll> randomEffects = new HashMap<>(); // this is in case minecraft change it

	public EffectFunction() {

	}

	@SafeVarargs
	public EffectFunction(Pair<Effect, UniformRoll>... effects) {
		for(Pair<Effect, UniformRoll> effect : effects) {
			this.apply(effect);
		}
	}

	public static EffectFunction builder() {
		return new EffectFunction();
	}

	public static <T> T nthElement(Iterable<T> data, int n) {
		int index = 0;
		for(T element : data) {
			if(index == n) {
				return element;
			}
			index++;
		}
		return null;
	}

	public EffectFunction apply(Effect effect, float min, float max) {
		return this.apply(new Pair<>(effect, new UniformRoll(min, max)));
	}

	public EffectFunction apply(Pair<Effect, UniformRoll> effect) {
		this.randomEffects.put(effect.getFirst(), effect.getSecond());
		this.effects.put(effect.getFirst(), effect.getSecond());
		return this;
	}

	public Map<Effect, UniformRoll> getEffects() {
		return effects;
	}

	@Override
	public ItemStack process(ItemStack baseStack, LootContext context) {
		Item newItem = new Item(baseStack.getItem().getName());
		if(newItem.equalsName(Items.SUSPICIOUS_STEW) && !this.getEffects().isEmpty()) {
			int i = context.nextInt(this.getEffects().size());
			Map.Entry<Effect, UniformRoll> entry = nthElement(this.getEffects().entrySet(), i); // mojang why? (this is not ordered so we hardcoded the order...
			assert entry != null;
			Effect effect = entry.getKey();
			int duration = entry.getValue().getCount(context);
			if(!effect.isInstantenous()) {
				duration *= 20;
			}
			newItem.addEffect(new Pair<>(effect, duration));
		}
		return new ItemStack(newItem, baseStack.getCount());
	}
}

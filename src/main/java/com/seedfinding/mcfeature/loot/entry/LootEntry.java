package com.seedfinding.mcfeature.loot.entry;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.LootGenerator;
import com.seedfinding.mcfeature.loot.function.LootFunction;
import com.seedfinding.mcnoise.utils.MathHelper;

import java.util.Arrays;
import java.util.function.Function;

public abstract class LootEntry extends LootGenerator {

	public final int weight;
	public final int quality;
	public MCVersion introducedVersion = null;
	public MCVersion deprecatedVersion = null;

	public LootEntry() {
		this(1);
	}

	public LootEntry(int weight) {
		this(weight, 0);
	}

	public LootEntry(int weight, int quality) {
		this.weight = weight;
		this.quality = quality;
	}

	public LootEntry introducedVersion(MCVersion version) {
		introducedVersion = version;
		return this;
	}

	public LootEntry deprecatedVersion(MCVersion version) {
		deprecatedVersion = version;
		return this;
	}

	public int getWeight(LootContext context) {
		return this.weight;
	}

	public int getEffectiveWeight(int luck) {
		return Math.max(MathHelper.floor((float)this.weight + (float)this.quality * luck), 0);
	}

	@SafeVarargs public final LootEntry apply(Function<MCVersion, LootFunction>... lootFunctions) {
		this.apply(Arrays.asList(lootFunctions));
		return this;
	}

}

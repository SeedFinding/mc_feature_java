package com.seedfinding.mcfeature.loot.entry;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.LootGenerator;
import com.seedfinding.mcfeature.loot.condition.LootCondition;
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

	public boolean existsIn(MCVersion version){
		// remove the entry if it was not yet introduced (so older and not equal to the introduced version)
		if(this.introducedVersion != null) {
			if(version.isOlderThan(this.introducedVersion)) {
				return false;
			}
		}
		// remove all newer version (or equal) to the deprecation
		if(this.deprecatedVersion != null) {
			return !version.isNewerOrEqualTo(this.deprecatedVersion);
		}
		return true;
	}

	public int getWeight(LootContext context) {
		return this.weight;
	}

	public int getEffectiveWeight(int luck) {
		return Math.max(MathHelper.floor((float)this.weight + (float)this.quality * luck), 0);
	}

	@SafeVarargs public final LootEntry apply(Function<MCVersion, LootFunction>... lootFunctions) {
		super.apply(Arrays.asList(lootFunctions));
		return this;
	}

	@SafeVarargs public final LootEntry when(Function<MCVersion, LootCondition>... lootConditions) {
		super.when(Arrays.asList(lootConditions));
		return this;
	}

}

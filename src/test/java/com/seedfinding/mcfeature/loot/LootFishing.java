package com.seedfinding.mcfeature.loot;

import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.item.ItemStack;
import com.seedfinding.mcfeature.loot.item.Items;
import com.seedfinding.mcseed.lcg.LCG;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LootFishing {

	@Test
	public void testCorrectChest1() {
		LootContext lootContext = new LootContext(249497827793450L ^ LCG.JAVA.multiplier, MCVersion.v1_16_1)
			.withBiome(Biomes.PLAINS)
			.withLuck(0)
			.withOpenWater(true);
		List<ItemStack> items = MCLootTables.FISHING.get().generate(lootContext);
		assertEquals(items.size(), 1);
		assertEquals(items.get(0), new ItemStack(Items.NAUTILUS_SHELL, 1));
	}
}

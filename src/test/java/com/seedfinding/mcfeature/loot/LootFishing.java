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

	@Test
	public void testCorrectChest2() {
		LootContext lootContext = new LootContext(266709016567238L ^ LCG.JAVA.multiplier, MCVersion.v1_19)
			.withBiome(Biomes.PLAINS)
			.withLuck(0)
			.withOpenWater(true);
		List<ItemStack> items = MCLootTables.FISHING.get().generate(lootContext);
		assertEquals(items.size(), 1);
		assertEquals(items.get(0), new ItemStack(Items.SALMON, 1));
	}


	@Test
	public void testCorrectChest3() {
		LootContext lootContext = new LootContext(232401166258803L ^ LCG.JAVA.multiplier, MCVersion.v1_19)
			.withBiome(Biomes.PLAINS)
			.withLuck(0)
			.withOpenWater(true);
		List<ItemStack> items = MCLootTables.FISHING.get().generate(lootContext);
		assertEquals(items.size(), 1);
		assertEquals(items.get(0), new ItemStack(Items.COD, 1));
	}

	@Test
	public void testCorrectChest4() {
		LootContext lootContext = new LootContext(77151274244052L ^ LCG.JAVA.multiplier, MCVersion.v1_19)
			.withBiome(Biomes.PLAINS)
			.withLuck(0)
			.withOpenWater(true);
		List<ItemStack> items = MCLootTables.FISHING.get().generate(lootContext);
		assertEquals(items.size(), 1);
		assertEquals(items.get(0), new ItemStack(Items.COD, 1));
	}

	@Test
	public void testCorrectChest5() {
		LootContext lootContext = new LootContext(25638188104002L ^ LCG.JAVA.multiplier, MCVersion.v1_19)
			.withBiome(Biomes.PLAINS)
			.withLuck(0)
			.withOpenWater(true);
		List<ItemStack> items = MCLootTables.FISHING.get().generate(lootContext);
		assertEquals(items.size(), 1);
		assertEquals(items.get(0), new ItemStack(Items.LILY_PAD, 1));
	}

	@Test
	public void testCorrectChest6() {
		LootContext lootContext = new LootContext(76235462220828L ^ LCG.JAVA.multiplier, MCVersion.v1_19)
			.withBiome(Biomes.PLAINS)
			.withLuck(0)
			.withOpenWater(true);
		List<ItemStack> items = MCLootTables.FISHING.get().generate(lootContext);
		assertEquals(items.size(), 1);
		assertEquals(items.get(0), new ItemStack(Items.PUFFERFISH, 1));
	}

	@Test
	public void testCorrectChest7() {
		LootContext lootContext = new LootContext(49297163305791L ^ LCG.JAVA.multiplier, MCVersion.v1_19)
			.withBiome(Biomes.PLAINS)
			.withLuck(0)
			.withOpenWater(true);
		List<ItemStack> items = MCLootTables.FISHING.get().generate(lootContext);
		assertEquals(items.size(), 1);
		assertEquals(items.get(0), new ItemStack(Items.ROTTEN_FLESH, 1));
	}


	@Test
	public void testCorrectChest8() {
		LootContext lootContext = new LootContext(213410720167157L ^ LCG.JAVA.multiplier, MCVersion.v1_19)
			.withBiome(Biomes.PLAINS)
			.withLuck(0)
			.withOpenWater(false);
		List<ItemStack> items = MCLootTables.FISHING.get().generate(lootContext);
		assertEquals(items.size(), 1);
		assertEquals(items.get(0), new ItemStack(Items.LEATHER_BOOTS, 1));
	}

	@Test
	public void testCorrectChest9() {
		LootContext lootContext = new LootContext(96550566301014L ^ LCG.JAVA.multiplier, MCVersion.v1_19)
			.withBiome(Biomes.PLAINS)
			.withLuck(0)
			.withOpenWater(false);
		List<ItemStack> items = MCLootTables.FISHING.get().generate(lootContext);
		assertEquals(items.size(), 1);
		assertEquals(items.get(0), new ItemStack(Items.COD, 1));
	}

	@Test
	public void testCorrectChest10() {
		LootContext lootContext = new LootContext(5564698933948L ^ LCG.JAVA.multiplier, MCVersion.v1_19)
			.withBiome(Biomes.PLAINS)
			.withLuck(0)
			.withOpenWater(false);
		List<ItemStack> items = MCLootTables.FISHING.get().generate(lootContext);
		assertEquals(items.size(), 1);
		assertEquals(items.get(0), new ItemStack(Items.LILY_PAD, 1));
	}
}

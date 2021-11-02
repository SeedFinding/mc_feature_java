package com.seedfinding.mcfeature.loot;

import com.seedfinding.mcfeature.loot.item.ItemStack;
import com.seedfinding.mcbiome.source.BiomeSource;
import com.seedfinding.mcfeature.structure.BuriedTreasure;
import com.seedfinding.mcfeature.structure.generator.Generator;
import com.seedfinding.mcfeature.structure.generator.structure.BuriedTreasureGenerator;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcterrain.TerrainGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LootTestBuriedTreasure {
	private List<Pair<Generator.ILootType, BPos>> loots;
	private Generator structureGenerator;
	private BiomeSource biomeSource;
	private TerrainGenerator generator;

	public void setup(long worldseed, CPos cPos, MCVersion version) {
		biomeSource = BiomeSource.of(Dimension.OVERWORLD, version, worldseed);
		generator = TerrainGenerator.of(Dimension.OVERWORLD, biomeSource);
		structureGenerator = new BuriedTreasureGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		structureGenerator.generate(generator, cPos, rand);
		loots = structureGenerator.getChestsPos();
	}

	@Test
	public void testCorrectChest1() {
		setup(123L, new BPos(905, 0, -1671).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<BuriedTreasureGenerator.LootType, BPos>> checks = new ArrayList<Pair<BuriedTreasureGenerator.LootType, BPos>>() {{
			add(new Pair<>(BuriedTreasureGenerator.LootType.BURIED_CHEST, new BPos(905, 90, -1671)));
		}};
		for(Pair<BuriedTreasureGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}


	@Test
	public void testChestLoot() {
		setup(123L, new BPos(905, 0, -1671).toChunkPos(), MCVersion.v1_16_5);
		BuriedTreasure buriedTreasure = new BuriedTreasure(MCVersion.v1_16_5);
		List<ChestContent> chests = buriedTreasure.getLoot(123L, structureGenerator, new ChunkRand(), false);
		long hash = 0;
		for(ChestContent chest : chests) {
			for(ItemStack stack : chest.getItems()) hash += stack.hashCode();
		}
		assertEquals(-1551810289L, hash, "Items changed maybe?");
	}

	@Test
	public void testChestLoot2() {
		setup(2000007L, new BPos(-279, 0, 121).toChunkPos(), MCVersion.v1_13_2);
		BuriedTreasure buriedTreasure = new BuriedTreasure(MCVersion.v1_13_2);
		List<ChestContent> chests = buriedTreasure.getLoot(2000007L, structureGenerator, new ChunkRand(), false);
		long hash = 0;
		for(ChestContent chest : chests) {
			for(ItemStack stack : chest.getItems()) hash += stack.hashCode();

		}
		assertEquals(2214247448L, hash, "Items changed maybe?");
	}
}

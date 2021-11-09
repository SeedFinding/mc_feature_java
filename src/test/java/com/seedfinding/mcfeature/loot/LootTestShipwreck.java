package com.seedfinding.mcfeature.loot;

import com.seedfinding.mcbiome.source.BiomeSource;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.util.pos.RPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.item.ItemStack;
import com.seedfinding.mcfeature.structure.Shipwreck;
import com.seedfinding.mcfeature.structure.generator.Generator;
import com.seedfinding.mcfeature.structure.generator.structure.ShipwreckGenerator;
import com.seedfinding.mcterrain.TerrainGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.seedfinding.mcfeature.structure.generator.structure.ShipwreckGenerator.LootType.SUPPLY_CHEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LootTestShipwreck {

	private List<Pair<Generator.ILootType, BPos>> loots;
	private Generator structureGenerator;
	private BiomeSource biomeSource;
	private TerrainGenerator generator;

	public void setup(long worldseed, CPos cPos, MCVersion version) {
		biomeSource = BiomeSource.of(Dimension.OVERWORLD, version, worldseed);
		generator = TerrainGenerator.of(Dimension.OVERWORLD, biomeSource);
		structureGenerator = new ShipwreckGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		structureGenerator.generate(generator, cPos, rand);
		loots = structureGenerator.getChestsPos();
	}

	@Test
	public void testCorrectChest1() {
		setup(2276366175191987160L, new BPos(-2535, 10, -3015).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<ShipwreckGenerator.LootType, BPos>> checks = new ArrayList<Pair<ShipwreckGenerator.LootType, BPos>>() {{
			add(new Pair<>(SUPPLY_CHEST, new BPos(-2533, 94, -3008)));
		}};
		for(Pair<ShipwreckGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}

	@Test
	public void testCorrectChest2() {
		setup(2276366175191987160L, new BPos(-2535, 10, -3015).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<ShipwreckGenerator.LootType, BPos>> checks = new ArrayList<Pair<ShipwreckGenerator.LootType, BPos>>() {{
			add(new Pair<>(SUPPLY_CHEST, new BPos(-2533, 94, -3008)));
		}};
		for(Pair<ShipwreckGenerator.LootType, BPos> check : checks) {
			assertTrue(loots.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}


	@Test
	public void testChestLoot() {
		setup(2276366175191987160L, new BPos(-2535, 10, -3015).toChunkPos(), MCVersion.v1_16_5);
		Shipwreck shipwreck = new Shipwreck(MCVersion.v1_16_5);
		List<ChestContent> chests = shipwreck.getLoot(2276366175191987160L, structureGenerator, new ChunkRand(), false);
		long hash = 0;
		for(ChestContent chest : chests) {
			for(ItemStack stack : chest.getItems()) {
				hash += stack.hashCode();
			}
		}
		assertEquals(716600595L, hash, "Items changed maybe?");
	}

	@Test
	public void testChestLoot2() {
		ChunkRand rand = new ChunkRand();
		long hash = 0;
		long worldSeed = 2276366175191987160L;
		MCVersion version = MCVersion.v1_16_5;
		Shipwreck shipwreck = new Shipwreck(version);
		RPos rPos = new RPos(1, 1, shipwreck.getSpacing());
		setup(worldSeed, rPos.toChunkPos(), MCVersion.v1_16_5);
		for(int i = 0; i < 100; i++) {
			RPos rPos1 = rPos.add(1, 1);
			CPos start = shipwreck.getInRegion(worldSeed, rPos1.getX(), rPos1.getZ(), rand);
			if(!shipwreck.canSpawn(start.getX(), start.getZ(), biomeSource)) continue;
			structureGenerator.generate(generator, start, rand);
			List<ChestContent> chests = shipwreck.getLoot(2276366175191987160L, structureGenerator, rand, false);

			for(ChestContent chest : chests) {
				for(ItemStack stack : chest.getItems()) {
					hash += stack.hashCode();
				}
			}
		}
		assertEquals(12552439300L, hash, "Items changed maybe?");
	}

}

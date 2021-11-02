package com.seedfinding.mcfeature.loot;

import com.seedfinding.mcfeature.loot.item.ItemStack;
import com.seedfinding.mcbiome.source.BiomeSource;
import com.seedfinding.mcfeature.structure.EndCity;
import com.seedfinding.mcfeature.structure.generator.Generator;
import com.seedfinding.mcfeature.structure.generator.structure.EndCityGenerator;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.data.ThreadPool;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcterrain.TerrainGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.seedfinding.mcfeature.structure.generator.structure.EndCityGenerator.LootType.FAT_TOWER_TOP_CHEST_1;
import static com.seedfinding.mcfeature.structure.generator.structure.EndCityGenerator.LootType.FAT_TOWER_TOP_CHEST_2;
import static com.seedfinding.mcfeature.structure.generator.structure.EndCityGenerator.LootType.SHIP_CHEST_1;
import static com.seedfinding.mcfeature.structure.generator.structure.EndCityGenerator.LootType.SHIP_CHEST_2;
import static com.seedfinding.mcfeature.structure.generator.structure.EndCityGenerator.LootType.SHIP_ELYTRA;
import static com.seedfinding.mcfeature.structure.generator.structure.EndCityGenerator.LootType.THIRD_FLOOR_CHEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LootTestEndCity {
	private List<Pair<Generator.ILootType, BPos>> chestPos;
	private EndCityGenerator structureGenerator;

	public void setup(long worldseed, CPos cPos, MCVersion version) {
		BiomeSource biomeSource = BiomeSource.of(Dimension.END, version, worldseed);
		TerrainGenerator generator = TerrainGenerator.of(Dimension.END, biomeSource);
		structureGenerator = new EndCityGenerator(version);
		ChunkRand rand = new ChunkRand().asChunkRandDebugger();
		structureGenerator.generate(generator, cPos, rand);
		chestPos = structureGenerator.getChestsPos();
	}

	@Test
	public void testCorrectChest1() {
		setup(1L, new BPos(-1232, 0, -25280).toChunkPos(), MCVersion.v1_16_5);
		assertTrue(structureGenerator.hasShip());
		List<Pair<EndCityGenerator.LootType, BPos>> checks = new ArrayList<Pair<EndCityGenerator.LootType, BPos>>() {{
			add(new Pair<>(SHIP_CHEST_1, new BPos(-1222, 100, -25202)));
			add(new Pair<>(SHIP_CHEST_2, new BPos(-1224, 100, -25202)));
			add(new Pair<>(SHIP_ELYTRA, new BPos(-1223, 100, -25202)));
		}};
		for(Pair<EndCityGenerator.LootType, BPos> check : checks) {
			assertTrue(chestPos.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}

	@Test
	public void testCorrectChest2() {
		// TODO see why this one generated 5 too high
		setup(1L, new BPos(-12752, 0, -30976).toChunkPos(), MCVersion.v1_16_5);
		List<Pair<EndCityGenerator.LootType, BPos>> checks = new ArrayList<Pair<EndCityGenerator.LootType, BPos>>() {{
			add(new Pair<>(THIRD_FLOOR_CHEST, new BPos(-12711, 103, -30973)));
		}};
		for(Pair<EndCityGenerator.LootType, BPos> check : checks) {
			assertTrue(chestPos.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}

	@Test
	public void testCorrectChest3() {
		setup(1L, new BPos(-127280, 0, -30944).toChunkPos(), MCVersion.v1_16_5);
		assertTrue(structureGenerator.hasShip());
		List<Pair<EndCityGenerator.LootType, BPos>> checks = new ArrayList<Pair<EndCityGenerator.LootType, BPos>>() {{
			add(new Pair<>(FAT_TOWER_TOP_CHEST_1, new BPos(-127270, 123, -30943)));
			add(new Pair<>(FAT_TOWER_TOP_CHEST_2, new BPos(-127272, 123, -30945)));
			// those are also 5 too high
			add(new Pair<>(SHIP_CHEST_1, new BPos(-127272, 145, -30907)));
			add(new Pair<>(SHIP_ELYTRA, new BPos(-127273, 145, -30907)));
			add(new Pair<>(SHIP_CHEST_2, new BPos(-127274, 145, -30907)));
			add(new Pair<>(THIRD_FLOOR_CHEST, new BPos(-127276, 127, -30989)));
		}};
		for(Pair<EndCityGenerator.LootType, BPos> check : checks) {
			assertTrue(chestPos.contains(check), String.format("Missing loot %s at pos %s", check.getFirst(), check.getSecond()));
		}
	}

	@Test
	public void testChestLoot() {
		setup(1L, new BPos(-127280, 0, -30944).toChunkPos(), MCVersion.v1_16_5);
		EndCity endCity = new EndCity(MCVersion.v1_16_5);
		List<ChestContent> chests = endCity.getLoot(1L, structureGenerator, new ChunkRand(), false);
		long hash = 0;
		for(ChestContent chest : chests) {
			for(ItemStack stack : chest.getItems()) hash += stack.hashCode();
		}
		assertEquals(-1486925666L, hash, "Items changed maybe?");
	}

	@Test
	public void testLargeEndcity() {
		// /tp @p 1952 150 -1840
		long worldSeed = -4425006226675986357L;
		setup(worldSeed, new BPos(-2880, 0, -320).toChunkPos(), MCVersion.v1_16_5);
		assertEquals(155, structureGenerator.getGlobalPieces().size(), "The end city doesn't have the proper size");
		assertTrue(structureGenerator.hasShip());
		EndCity endCity = new EndCity(MCVersion.v1_16_5);
		List<ChestContent> chests = endCity.getLoot(worldSeed, structureGenerator, new ChunkRand(), false);
		long diamondCount = 0;
		for(ChestContent chest : chests) {
			diamondCount += chest.getCount(f -> f.getName().contains("diamond"));
		}
		assertEquals(68, diamondCount, "Diamond count doesn't match");
	}

	public static void main(String[] args) {
		new ThreadPool(Runtime.getRuntime().availableProcessors()).run(LootTestEndCity::tryFindDiamond);
	}

	public static void tryFindDiamond() {
		MCVersion version = MCVersion.v1_16_5;
		EndCity endCity = new EndCity(version);
		ChunkRand rand = new ChunkRand();

		EndCityGenerator endCityGenerator = new EndCityGenerator(version);

		for(int i = 0; i < 1000000000; i++) {
			long worldseed = rand.nextLong();
			BiomeSource biomeSource = BiomeSource.of(Dimension.END, version, worldseed);
			TerrainGenerator generator = TerrainGenerator.of(Dimension.END, biomeSource);
			for(int regionX = -3000 / 16 / endCity.getSpacing(); regionX < 3000 / 16 / endCity.getSpacing(); regionX++) {
				for(int regionZ = -3000 / 16 / endCity.getSpacing(); regionZ < 3000 / 16 / endCity.getSpacing(); regionZ++) {
					CPos pos = endCity.getInRegion(worldseed, regionX, regionZ, rand);
					if(endCity.canSpawn(pos, biomeSource)) {
						if(endCity.canGenerate(pos, generator)) {
							endCityGenerator.generate(generator, pos.getX(), pos.getZ(), rand);
							List<ChestContent> chests = endCity.getLoot(worldseed, endCityGenerator, rand, false);
							int diamond = 0;
							for(ChestContent chest : chests) {
								diamond += chest.getCount(e -> e.getName().contains("diamond"));
							}
							if(diamond > 50) {
								System.out.printf("Diamond: %d, seed: %d, tp: /tp @p %d ~ %d%n", diamond, worldseed, pos.getX() * 16, pos.getZ() * 16);
							}
							endCityGenerator.reset();
						}
					}
				}
			}
		}
	}
}



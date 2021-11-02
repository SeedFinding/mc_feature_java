package com.seedfinding.mcfeature.examples.loot.explanation;

import com.seedfinding.mcfeature.loot.LootChest;
import com.seedfinding.mcfeature.loot.MCLootTables;
import com.seedfinding.mcfeature.loot.item.Items;
import com.seedfinding.mcbiome.source.OverworldBiomeSource;
import com.seedfinding.mcfeature.structure.BuriedTreasure;
import com.seedfinding.mcfeature.structure.RegionStructure;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcseed.lcg.LCG;
import mjtb49.hashreversals.ChunkRandomReverser;

public class LootTest2 {

	public static final BuriedTreasure BURIED_TREASURE = new BuriedTreasure(MCVersion.v1_16);

	public static LootChest TREASURE_CHEST = new LootChest(
		LootChest.stack(Items.EMERALD, LootChest.EQUAL_TO, 24),
		LootChest.stack(Items.IRON_INGOT, LootChest.MORE_OR_EQUAL_TO, 26)
	);

	public static void main(String[] args) {
		ChunkRand rand = new ChunkRand();

		for(long seed = 0; seed < 1L << 48; seed++) {
			rand.setSeed(seed, false);
			if(!TREASURE_CHEST.testLoot(rand.nextLong(), MCLootTables.BURIED_TREASURE_CHEST)) continue;
			System.out.println("seed " + seed);

			for(int chunkX = 0; chunkX < 200; chunkX++) {
//				for(long structureSeed : ChunkRandomReverser.reversePopulationSeed((seed ^ LCG.JAVA.multiplier) - 30001L,
//					chunkX << 4, 0, MCVersion.v1_16)) {
//					RegionStructure.Data<BuriedTreasure> treasure = BURIED_TREASURE.at(chunkX, 0);
//					if(!treasure.testStart(structureSeed, rand)) continue;
//					System.out.println("structure seed " + structureSeed + " at (" + ((chunkX << 4) + 9) + ", 9)");
//
//					for(long upperBits = 0; upperBits < 1L << 16; upperBits++) {
//						long worldSeed = (upperBits << 48) | structureSeed;
//						OverworldBiomeSource source = new OverworldBiomeSource(MCVersion.v1_16, worldSeed);
//						if(!treasure.testBiome(source)) continue;
//						System.out.println("world seed " + worldSeed);
//					}
//				}
			}
		}
	}

}

package com.seedfinding.mcfeature.loot;

import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.GenerationContext;
import com.seedfinding.mcfeature.structure.BuriedTreasure;
import com.seedfinding.mcfeature.structure.DesertPyramid;
import com.seedfinding.mcfeature.structure.RuinedPortal;
import com.seedfinding.mcfeature.structure.Shipwreck;
import com.seedfinding.mcfeature.structure.generator.Generator;
import com.seedfinding.mcfeature.structure.generator.Generators;

public class BenchLoot {
	public static final MCVersion VERSION = MCVersion.v1_17;
	public static final Shipwreck SHIPWRECK = new Shipwreck(VERSION);
	public static final RuinedPortal RUINED_PORTAL = new RuinedPortal(Dimension.OVERWORLD, VERSION);
	public static final BuriedTreasure BURIED_TREASURE = new BuriedTreasure(VERSION);
	public static final DesertPyramid DESERT_PYRAMID = new DesertPyramid(VERSION);

	public static void main(String[] args) {
		benchLoot();
	}

	public static void benchLoot() {
		long seed = 2276366175191987160L;
		GenerationContext.Context context = GenerationContext.getContext(seed, Dimension.OVERWORLD, VERSION);
		// warmup
		for(int i = 0; i < 100; i++) {
			generateAll(context);
		}
		long time = System.nanoTime();
		// warmup
		for(int i = 0; i < 100000; i++) {
			generateAll(context);
		}
		System.out.println("Elapsed " + (System.nanoTime() - time) / 1e6 + " ms");
	}

	public static void generateAll(GenerationContext.Context context) {
		CPos cPos;
		Generator generator;
		cPos = new BPos(-2535, 10, -3015).toChunkPos();
		generator = Generators.get(SHIPWRECK.getClass()).create(VERSION);
		generator.generate(context.getGenerator(), cPos);
		SHIPWRECK.getLoot(context.getWorldSeed(), generator, new ChunkRand(), false);

		cPos = new BPos(9, 0, -263).toChunkPos();
		generator = Generators.get(RUINED_PORTAL.getClass()).create(VERSION);
		generator.generate(context.getGenerator(), cPos);
		RUINED_PORTAL.getLoot(context.getWorldSeed(), generator, new ChunkRand(), false);

		cPos = new BPos(1273, 0, 857).toChunkPos();
		generator = Generators.get(DESERT_PYRAMID.getClass()).create(VERSION);
		generator.generate(context.getGenerator(), cPos);
		DESERT_PYRAMID.getLoot(context.getWorldSeed(), generator, new ChunkRand(), false);

		cPos = new BPos(89, 0, 665).toChunkPos();
		generator = Generators.get(BURIED_TREASURE.getClass()).create(VERSION);
		generator.generate(context.getGenerator(), cPos);
		BURIED_TREASURE.getLoot(context.getWorldSeed(), generator, new ChunkRand(), false);
	}
}

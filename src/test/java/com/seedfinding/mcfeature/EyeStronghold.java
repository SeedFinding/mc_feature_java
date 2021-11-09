package com.seedfinding.mcfeature;

import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.structure.generator.structure.StrongholdGenerator;

import java.util.Arrays;

public class EyeStronghold {
	public static void main(String[] args) {
		StrongholdGenerator g = new StrongholdGenerator(MCVersion.v1_16);

		ChunkRand chunkRand = new ChunkRand().asChunkRandDebugger();
		g.populateStructure(42, -75, 61, chunkRand);
		System.out.println(Arrays.toString(g.getEyes()));
	}
}

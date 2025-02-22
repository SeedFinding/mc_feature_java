package com.seedfinding.mcfeature.structure.generator.structure;


import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.UnsupportedVersion;
import com.seedfinding.mcfeature.structure.generator.Generator;
import com.seedfinding.mcterrain.TerrainGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FortressGenerator extends Generator {

	private static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;

	private static final int START = 0;

	private static final int BRIDGE_FIRST = 1,
		BRIDGE_STRAIGHT = 1,
		BRIDGE_CROSSING = 2,
		BRIDGE_FORTIFIED_CROSSING = 3,
		BRIDGE_STAIRS = 4,
		BRIDGE_SPAWNER = 5,
		BRIDGE_CORRIDOR_ENTRANCE = 6,
		BRIDGE_PIECES_COUNT = 6;

	private static final int CORRIDOR_FIRST = 7,
		CORRIDOR_STRAIGHT = 7,
		CORRIDOR_CROSSING = 8,
		CORRIDOR_TURN_RIGHT = 9,
		CORRIDOR_TURN_LEFT = 10,
		CORRIDOR_STAIRS = 11,
		CORRIDOR_T_CROSSING = 12,
		CORRIDOR_NETHER_WART = 13,
		CORRIDOR_PIECES_COUNT = 7;

	private static final int END = 14;
	private static final int PIECES_COUNT = 15;

	private static final int[] BRIDGE_WEIGHTS = {30, 10, 10, 10, 5, 5};
	private static final int[] BRIDGE_MAXS = {0, 4, 4, 3, 2, 1};
	private static final boolean[] BRIDGE_ALLOW_CONSECUTIVE = {true, false, false, false, false, false};

	private static final int[] CORRIDOR_WEIGHTS = {25, 15, 5, 5, 10, 7, 5};
	private static final int[] CORRIDOR_MAXS = {0, 5, 10, 10, 3, 2, 2};
	private static final boolean[] CORRIDOR_ALLOW_CONSECUTIVE = {true, false, false, false, true, false, false};

	private static final Creator[] CREATORS = {
		null,
		FortressGenerator::createBridgeStraight,
		FortressGenerator::createBridgeCrossing,
		FortressGenerator::createBridgeFortifiedCrossing,
		FortressGenerator::createBridgeStairs,
		FortressGenerator::createBridgeSpawner,
		FortressGenerator::createBridgeCorridorEntrance,
		FortressGenerator::createCorridorStraight,
		FortressGenerator::createCorridorCrossing,
		FortressGenerator::createCorridorTurnRight,
		FortressGenerator::createCorridorTurnLeft,
		FortressGenerator::createCorridorStairs,
		FortressGenerator::createCorridorTCrossing,
		FortressGenerator::createCorridorNetherWart
	};
	private static final ArrayList<Consumer<ChunkRand>> POST_CREATORS = new ArrayList<Consumer<ChunkRand>>() {{
		add(rand -> {});
		add(rand -> {});
		add(rand -> {});
		add(rand -> {});
		add(rand -> {});
		add(rand -> {});
		add(rand -> {});
		add(rand -> {});
		add(rand -> {});
		add(rand -> rand.nextInt(3));
		add(rand -> rand.nextInt(3));
		add(rand -> {});
		add(rand -> {});
		add(rand -> {});
	}};
	@SuppressWarnings("unchecked")
	private final List<PieceInfo>[] placements = new List[PIECES_COUNT];
	private final List<PieceInfo> pieceQueue = new ArrayList<>();
	private PieceInfo start;
	private int lastPlaced;
	private final ArrayList<Function<FortressGenerator, Extender>> EXTENDERS = new ArrayList<Function<FortressGenerator, Extender>>() {{
		add(generator -> null);
		add(generator -> generator::extendBridgeStraight);
		add(generator -> generator::extendBridgeCrossing);
		add(generator -> generator::extendBridgeFortifiedCrossing);
		add(generator -> generator::extendBridgeStairs);
		add(generator -> generator::extendBridgeSpawner);
		add(generator -> generator::extendBridgeCorridorEntrance);
		add(generator -> generator::extendCorridorStraight);
		add(generator -> generator::extendCorridorCrossing);
		add(generator -> generator::extendCorridorTurnRight);
		add(generator -> generator::extendCorridorTurnLeft);
		add(generator -> generator::extendCorridorStairs);
		add(generator -> generator::extendCorridorTCrossing);
		add(generator -> generator::extendCorridorNetherWart);
		add(generator -> generator::extendEnd);
	}};

	public FortressGenerator(MCVersion version) {
		super(version);
		Arrays.setAll(placements, i -> new ArrayList<>());
	}

	private void genFortress(int chunkX, int chunkZ, ChunkRand rand) {
		if(version.isOlderThan(MCVersion.v1_12)) {
			throw new UnsupportedVersion(version, "fortress generator.");
		}
		start = createStart((chunkX << 4) + 2, 64, (chunkZ << 4) + 2, rand);
		placements[START].add(start);
		extendBridgeCrossing(start, rand);
		while(!pieceQueue.isEmpty()) {
			int i = rand.nextInt(pieceQueue.size());

			PieceInfo piece = pieceQueue.remove(i);
			assert EXTENDERS.get(piece.type) != null;
			EXTENDERS.get(piece.type).apply(this).extend(piece, rand);
		}
	}

	/**
	 * Get all the pieces placements of a fortress after generating it
	 *
	 * @return
	 */
	public List<PieceInfo>[] getPlacements() {
		return placements;
	}
	// ===== CREATORS ===== //

	private static PieceInfo createStart(int x, int y, int z, ChunkRand rand) {
		return new PieceInfo(START, 0, x, y, z, x + 19 - 1, 73, z + 19 - 1, rand.nextInt(4));
	}

	private static PieceInfo createBridgeStraight(int x, int y, int z, int depth, int facing) {
		return createRotated(BRIDGE_STRAIGHT, depth, x, y, z, -1, -3, 0, 5, 10, 19, facing);
	}

	private static PieceInfo createBridgeCrossing(int x, int y, int z, int depth, int facing) {
		return createRotated(BRIDGE_CROSSING, depth, x, y, z, -8, -3, 0, 19, 10, 19, facing);
	}

	private static PieceInfo createBridgeFortifiedCrossing(int x, int y, int z, int depth, int facing) {
		return createRotated(BRIDGE_FORTIFIED_CROSSING, depth, x, y, z, -2, 0, 0, 7, 9, 7, facing);
	}

	private static PieceInfo createBridgeStairs(int x, int y, int z, int depth, int facing) {
		return createRotated(BRIDGE_STAIRS, depth, x, y, z, -2, 0, 0, 7, 11, 7, facing);
	}

	private static PieceInfo createBridgeSpawner(int x, int y, int z, int depth, int facing) {
		return createRotated(BRIDGE_SPAWNER, depth, x, y, z, -2, 0, 0, 7, 8, 9, facing);
	}

	private static PieceInfo createBridgeCorridorEntrance(int x, int y, int z, int depth, int facing) {
		return createRotated(BRIDGE_CORRIDOR_ENTRANCE, depth, x, y, z, -5, -3, 0, 13, 14, 13, facing);
	}

	private static PieceInfo createCorridorStraight(int x, int y, int z, int depth, int facing) {
		return createRotated(CORRIDOR_STRAIGHT, depth, x, y, z, -1, 0, 0, 5, 7, 5, facing);
	}

	private static PieceInfo createCorridorCrossing(int x, int y, int z, int depth, int facing) {
		return createRotated(CORRIDOR_CROSSING, depth, x, y, z, -1, 0, 0, 5, 7, 5, facing);
	}

	private static PieceInfo createCorridorTurnRight(int x, int y, int z, int depth, int facing) {
		return createRotated(CORRIDOR_TURN_RIGHT, depth, x, y, z, -1, 0, 0, 5, 7, 5, facing);
	}

	private static PieceInfo createCorridorTurnLeft(int x, int y, int z, int depth, int facing) {
		return createRotated(CORRIDOR_TURN_LEFT, depth, x, y, z, -1, 0, 0, 5, 7, 5, facing);
	}

	private static PieceInfo createCorridorStairs(int x, int y, int z, int depth, int facing) {
		return createRotated(CORRIDOR_STAIRS, depth, x, y, z, -1, -7, 0, 5, 14, 10, facing);
	}

	private static PieceInfo createCorridorTCrossing(int x, int y, int z, int depth, int facing) {
		return createRotated(CORRIDOR_T_CROSSING, depth, x, y, z, -3, 0, 0, 9, 7, 9, facing);
	}

	private static PieceInfo createCorridorNetherWart(int x, int y, int z, int depth, int facing) {
		return createRotated(CORRIDOR_NETHER_WART, depth, x, y, z, -5, -3, 0, 13, 14, 13, facing);
	}

	private static PieceInfo createEnd(int x, int y, int z, int depth, int facing) {
		return createRotated(END, depth, x, y, z, -1, -3, 0, 5, 10, 8, facing);
	}

	private static PieceInfo createRotated(int type, int depth, int x, int y, int z, int relXMin, int relYMin, int relZMin, int relXMax, int relYMax, int relZMax, int facing) {
		int xMin, yMin, zMin, xMax, yMax, zMax;
		switch(facing) {
			case NORTH:
			case SOUTH:
				xMin = x + relXMin;
				xMax = x + relXMax - 1 + relXMin;
				break;
			case WEST:
				xMin = x - relZMax + 1 + relZMin;
				xMax = x + relZMin;
				break;
			case EAST:
				xMin = x + relZMin;
				xMax = x + relZMax - 1 + relZMin;
				break;
			default:
				throw new AssertionError();
		}
		yMin = y + relYMin;
		yMax = y + relYMax - 1 + relYMin;
		switch(facing) {
			case NORTH:
				zMin = z - relZMax + 1 + relZMin;
				zMax = z + relZMin;
				break;
			case SOUTH:
				zMin = z + relZMin;
				zMax = z + relZMax - 1 + relZMin;
				break;
			case WEST:
			case EAST:
				zMin = z + relXMin;
				zMax = z + relXMax - 1 + relXMin;
				break;
			default:
				throw new AssertionError();
		}
		return new PieceInfo(type, depth, xMin, yMin, zMin, xMax, yMax, zMax, facing);
	}


	// ===== EXTENDERS ===== //

	private void extendBridgeStraight(PieceInfo pieceInfo, ChunkRand rand) {
		extendForwards(pieceInfo, rand, 1, 3, false);
	}

	private void extendBridgeCrossing(PieceInfo pieceInfo, ChunkRand rand) {
		extendForwards(pieceInfo, rand, 8, 3, false);
		extendLeft(pieceInfo, rand, 8, 3, false);
		extendRight(pieceInfo, rand, 8, 3, false);
	}

	private void extendBridgeFortifiedCrossing(PieceInfo pieceInfo, ChunkRand rand) {
		extendForwards(pieceInfo, rand, 2, 0, false);
		extendLeft(pieceInfo, rand, 2, 0, false);
		extendRight(pieceInfo, rand, 2, 0, false);
	}

	private void extendBridgeStairs(PieceInfo pieceInfo, ChunkRand rand) {
		extendRight(pieceInfo, rand, 2, 6, false);
	}

	private void extendBridgeSpawner(PieceInfo pieceInfo, ChunkRand rand) {
	}

	private void extendEnd(PieceInfo pieceInfo, ChunkRand rand) {
	}

	private void extendBridgeCorridorEntrance(PieceInfo pieceInfo, ChunkRand rand) {
		extendForwards(pieceInfo, rand, 5, 3, true);
	}

	private void extendCorridorStraight(PieceInfo pieceInfo, ChunkRand rand) {
		extendForwards(pieceInfo, rand, 1, 0, true);
	}

	private void extendCorridorCrossing(PieceInfo pieceInfo, ChunkRand rand) {
		extendForwards(pieceInfo, rand, 1, 0, true);
		extendLeft(pieceInfo, rand, 1, 0, true);
		extendRight(pieceInfo, rand, 1, 0, true);
	}

	private void extendCorridorTurnRight(PieceInfo pieceInfo, ChunkRand rand) {
		extendRight(pieceInfo, rand, 1, 0, true);
	}

	private void extendCorridorTurnLeft(PieceInfo pieceInfo, ChunkRand rand) {
		extendLeft(pieceInfo, rand, 1, 0, true);
	}

	private void extendCorridorStairs(PieceInfo pieceInfo, ChunkRand rand) {
		extendForwards(pieceInfo, rand, 1, 0, true);
	}

	private void extendCorridorTCrossing(PieceInfo pieceInfo, ChunkRand rand) {
		int horOffset;
		if(pieceInfo.facing == WEST || pieceInfo.facing == NORTH)
			horOffset = 5;
		else
			horOffset = 1;
		extendLeft(pieceInfo, rand, horOffset, 0, rand.nextInt(8) > 0);
		extendRight(pieceInfo, rand, horOffset, 0, rand.nextInt(8) > 0);
	}

	private void extendCorridorNetherWart(PieceInfo pieceInfo, ChunkRand rand) {
		extendForwards(pieceInfo, rand, 5, 3, true);
		extendForwards(pieceInfo, rand, 5, 11, true);
	}

	private void extendForwards(PieceInfo pieceInfo, ChunkRand rand, int horOffset, int vertOffset, boolean inCorridor) {
		switch(pieceInfo.facing) {
			case NORTH:
				extend(rand, pieceInfo.xMin + horOffset, pieceInfo.yMin + vertOffset, pieceInfo.zMin - 1, pieceInfo.facing, pieceInfo.depth + 1, inCorridor);
				break;
			case SOUTH:
				extend(rand, pieceInfo.xMin + horOffset, pieceInfo.yMin + vertOffset, pieceInfo.zMax + 1, pieceInfo.facing, pieceInfo.depth + 1, inCorridor);
				break;
			case WEST:
				extend(rand, pieceInfo.xMin - 1, pieceInfo.yMin + vertOffset, pieceInfo.zMin + horOffset, pieceInfo.facing, pieceInfo.depth + 1, inCorridor);
				break;
			case EAST:
				extend(rand, pieceInfo.xMax + 1, pieceInfo.yMin + vertOffset, pieceInfo.zMin + horOffset, pieceInfo.facing, pieceInfo.depth + 1, inCorridor);
				break;
		}
	}

	private void extendLeft(PieceInfo pieceInfo, ChunkRand rand, int horOffset, int vertOffset, boolean inCorridor) {
		switch(pieceInfo.facing) {
			case NORTH:
			case SOUTH:
				extend(rand, pieceInfo.xMin - 1, pieceInfo.yMin + vertOffset, pieceInfo.zMin + horOffset, WEST, pieceInfo.depth + 1, inCorridor);
				break;
			case WEST:
			case EAST:
				extend(rand, pieceInfo.xMin + horOffset, pieceInfo.yMin + vertOffset, pieceInfo.zMin - 1, NORTH, pieceInfo.depth + 1, inCorridor);
				break;
		}
	}

	private void extendRight(PieceInfo pieceInfo, ChunkRand rand, int horOffset, int vertOffset, boolean inCorridor) {
		switch(pieceInfo.facing) {
			case NORTH:
			case SOUTH:
				extend(rand, pieceInfo.xMax + 1, pieceInfo.yMin + vertOffset, pieceInfo.zMin + horOffset, EAST, pieceInfo.depth + 1, inCorridor);
				break;
			case WEST:
			case EAST:
				extend(rand, pieceInfo.xMin + horOffset, pieceInfo.yMin + vertOffset, pieceInfo.zMax + 1, SOUTH, pieceInfo.depth + 1, inCorridor);
				break;
		}
	}

	private void extend(ChunkRand rand, int x, int y, int z, int facing, int depth, boolean inCorridor) {
		if(Math.abs(x - start.xMin) <= 112 && Math.abs(z - start.zMin) <= 112) {
			int first;
			int pieceCount;
			int[] weights;
			int[] maxs;
			boolean[] allowConsecutives;
			if(inCorridor) {
				first = CORRIDOR_FIRST;
				pieceCount = CORRIDOR_PIECES_COUNT;
				weights = CORRIDOR_WEIGHTS;
				maxs = CORRIDOR_MAXS;
				allowConsecutives = CORRIDOR_ALLOW_CONSECUTIVE;
			} else {
				first = BRIDGE_FIRST;
				pieceCount = BRIDGE_PIECES_COUNT;
				weights = BRIDGE_WEIGHTS;
				maxs = BRIDGE_MAXS;
				allowConsecutives = BRIDGE_ALLOW_CONSECUTIVE;
			}

			boolean anyValid = false;
			int totalWeight = 0;
			for(int i = 0; i < pieceCount; i++) {
				if(maxs[i] > 0 && placements[first + i].size() >= maxs[i])
					continue;
				if(maxs[i] > 0)
					anyValid = true;
				totalWeight += weights[i];
			}
			if(anyValid && totalWeight > 0 && depth <= 30) {

				int tries = 0;
				while(tries < 5) {
					tries++;
					int n = rand.nextInt(totalWeight);
					for(int i = 0; i < pieceCount; i++) {
						if(maxs[i] > 0 && placements[first + i].size() >= maxs[i])
							continue;
						n -= weights[i];
						if(n < 0) {
							if(lastPlaced == first + i && !allowConsecutives[i]) {

								break;


							}

							Creator creator = CREATORS[first + i];
							//System.out.println("Creating fortress piece " + (first + i) + " at (" + x + ", " + y + ", " + z + ") facing " + facing + " with depth " + depth + " queue size: " + pieceQueue.size() + " last placed: " + lastPlaced);
							PieceInfo pieceInfo = creator.create(x, y, z, depth, facing);
							if(!intersectsAny(pieceInfo.xMin, pieceInfo.yMin, pieceInfo.zMin, pieceInfo.xMax, pieceInfo.yMax, pieceInfo.zMax)) {
								POST_CREATORS.get(first + i).accept(rand);
								lastPlaced = first + i;

								placements[first + i].add(pieceInfo);
								pieceQueue.add(pieceInfo);
								return;
							}
						}
					}
				}

			}
		}
		PieceInfo pieceInfo = createEnd(x, y, z, depth, facing);
		if(!intersectsAny(pieceInfo.xMin, pieceInfo.yMin, pieceInfo.zMin, pieceInfo.xMax, pieceInfo.yMax, pieceInfo.zMax)) {
			rand.nextInt();
			placements[END].add(pieceInfo);
			pieceQueue.add(pieceInfo);
		}
	}

	private boolean intersectsAny(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax) {
		for(List<PieceInfo> pieceInfoList : placements) {
			for(PieceInfo pieceInfo : pieceInfoList) {
				if(pieceInfo.xMin <= xMax && pieceInfo.xMax >= xMin && pieceInfo.zMin <= zMax && pieceInfo.zMax >= zMin && pieceInfo.yMin <= yMax && pieceInfo.yMax >= yMin)
					return true;
			}
		}
		return false;
	}

	private void setSeed(ChunkRand rand, long worldSeed, int chunkX, int chunkZ) {
		if (version.isOlderOrEqualTo(MCVersion.v1_15)) {
			rand.setSeed((chunkX >> 4) ^ (chunkZ >> 4 << 4) ^ worldSeed);
			rand.nextInt();
			rand.nextInt(3);
			rand.nextInt(8);
			rand.nextInt(8);
		} else {
			rand.setCarverSeed(worldSeed, chunkX, chunkZ, version);
		}
	}

	public static void main(String[] args) {
		ChunkRand rand = new ChunkRand();
		FortressGenerator fortressGenerator = new FortressGenerator(MCVersion.v1_12);
		fortressGenerator.setSeed(rand, 5896870166552931055L, -21, -5);

		fortressGenerator.genFortress(-21, -5, rand);
	}

	@Override
	public boolean generate(TerrainGenerator generator, int chunkX, int chunkZ, ChunkRand rand) {
		// TODO: reset state here
		this.setSeed(rand, generator.getWorldSeed(), chunkX, chunkZ);
		this.genFortress(chunkX, chunkZ, rand);
		return true;
	}

	@Override
	public List<Pair<ILootType, BPos>> getChestsPos() {
		return null;
	}

	@Override
	public List<Pair<ILootType, BPos>> getLootPos() {
		return getChestsPos();
	}

	@Override
	public ILootType[] getLootTypes() {
		return new ILootType[0];
	}

	@FunctionalInterface
	private interface Creator {
		PieceInfo create(int x, int y, int z, int depth, int facing);
	}


	@FunctionalInterface
	private interface Extender {
		void extend(PieceInfo pieceInfo, ChunkRand rand);
	}

	public static class PieceInfo {
		public final int type;
		public final int depth;
		public final int xMin, yMin, zMin;
		public final int xMax, yMax, zMax;
		public final int facing;

		public PieceInfo(int type, int depth, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, int facing) {
			this.type = type;
			this.depth = depth;
			this.xMin = xMin;
			this.yMin = yMin;
			this.zMin = zMin;
			this.xMax = xMax;
			this.yMax = yMax;
			this.zMax = zMax;
			this.facing = facing;
		}
	}

}

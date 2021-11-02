package com.seedfinding.mcfeature.decorator.ore;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mccore.block.Block;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.util.math.DistanceMetric;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcseed.rand.JRand;
import com.seedfinding.mcterrain.TerrainGenerator;
import com.seedfinding.mccore.block.Block;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.util.math.DistanceMetric;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mcseed.rand.JRand;
import com.seedfinding.mcterrain.TerrainGenerator;

import java.util.ArrayList;
import java.util.List;

public abstract class SphereOreDecorator<C extends OreDecorator.Config, D extends OreDecorator.Data<?>> extends OreDecorator<C, D> {

	public SphereOreDecorator(C config, MCVersion version) {
		super(config, version);
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}


	@Override
	protected List<BPos> generateOrePositions(BPos bPos, Biome biome, TerrainGenerator generator, JRand rand) {
		// we abuse the getSize as the halfHeight (to avoid a weird definition in OreDecorator
		// and the heightProvider is used for the radius (basically transform it 90deg)
		List<BPos> poses = new ArrayList<>();
		int radius = this.getHeightProvider(biome).getY(rand);
		for(int x = bPos.getX() - radius; x <= bPos.getX() + radius; x++) {
			for(int z = bPos.getZ() - radius; z <= bPos.getZ() + radius; z++) {
				int distanceCenterX = x - bPos.getX();
				int distanceCenterZ = z - bPos.getZ();
				if(DistanceMetric.EUCLIDEAN_SQ.getDistance(distanceCenterX, 0, distanceCenterZ) <= radius * radius) {
					for(int y = bPos.getY() - this.getSize(biome); y <= bPos.getY() + this.getSize(biome); y++) {
						BPos currentPos = new BPos(x, y, z);
						Block current = generator.getBiomeColumnAt(x, z)[y];
						if(this.getReplaceBlocks(biome).contains(current)) {
							poses.add(currentPos);
						}
					}
				}
			}
		}
		return poses;
	}
}

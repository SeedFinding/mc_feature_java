package com.seedfinding.mcfeature.structure.generator.piece.stronghold;

import com.seedfinding.mccore.util.block.BlockBox;
import com.seedfinding.mccore.util.block.BlockDirection;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mcfeature.structure.Stronghold;
import com.seedfinding.mcseed.rand.JRand;

import java.util.List;

public class SmallCorridor extends Stronghold.Piece {

	public SmallCorridor(int pieceId) {
		super(pieceId);
	}

	public SmallCorridor(int pieceId, BlockBox boundingBox, BlockDirection facing) {
		super(pieceId);
		this.setOrientation(facing);
		this.boundingBox = boundingBox;
	}

	public static BlockBox createBox(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, BlockDirection facing) {
		BlockBox box = BlockBox.rotated(x, y, z, -1, -1, 0, 5, 5, 4, facing.getRotation());
		Stronghold.Piece piece = Stronghold.Piece.getNextIntersectingPiece(pieces, box);

		if(piece != null && piece.getBoundingBox().minY == box.minY) {
			for(int zz = 3; zz >= 1; --zz) {
				box = BlockBox.rotated(x, y, z, -1, -1, 0, 5, 5, zz - 1, facing.getRotation());
				if(!piece.getBoundingBox().intersects(box)) {
					return BlockBox.rotated(x, y, z, -1, -1, 0, 5, 5, zz, facing.getRotation());
				}
			}
		}

		return null;
	}

	public boolean process(JRand rand, BPos pos) {
		// not random
		return true;
	}

}

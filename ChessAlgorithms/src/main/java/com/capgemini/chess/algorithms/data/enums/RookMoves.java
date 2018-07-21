package com.capgemini.chess.algorithms.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public class RookMoves {

	public List<Coordinate> generateRookMoves(Board board, Coordinate from) {
		int xFrom = from.getX();
		int yFrom = from.getY();
		boolean isCleanPath = true;
		ArrayList<Coordinate> generatedMoves = new ArrayList<Coordinate>();
		for (int i = 0; i < board.SIZE; i++) {
			for (int j = 0; j < board.SIZE; j++) {
				if (i < 0 || j < 0 || i > 7 || j > 7)
					continue;
				if (board.getPieceAt(new Coordinate(i, j)) != null && (xFrom != i || yFrom != j)) {
					if (board.getPieceAt(new Coordinate(i, j)).getColor() == board.getPieceAt(from).getColor()) {
						isCleanPath = false;
						continue;
					} else if (board.getPieceAt(new Coordinate(i, j)).getColor() != board.getPieceAt(from).getColor()) {
						isCleanPath = false;
						generatedMoves.add(new Coordinate(i, j));
						continue;
					}
				}
				if ((xFrom != i || yFrom != j) && isCleanPath) {
					if (xFrom == i || yFrom == j) {
						generatedMoves.add(new Coordinate(i, j));
					}
				}
			}
			isCleanPath = true;
		}
		return generatedMoves;
	}

}

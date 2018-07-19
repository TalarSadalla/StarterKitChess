package com.capgemini.chess.algorithms.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public class KnightMoves {

	public List<Coordinate> generateKnightMoves(Board board, Coordinate from) {
		int xFrom = from.getX();
		int yFrom = from.getY();
		ArrayList<Coordinate> generatedMoves = new ArrayList<Coordinate>();
		for (int i = 0; i < board.SIZE; i++) {
			for (int j = 0; j < board.SIZE; j++) {
				if (i < 0 || j < 0 || i > 7 || j > 7)
					continue;
				if (board.getPieceAt(new Coordinate(i, j)) != null) {
					if (board.getPieceAt(new Coordinate(i, j)).getColor() == board.getPieceAt(from).getColor()) {
						continue;
					}
				}
				boolean condition = (i == xFrom + 1 && j == yFrom + 2) || (i == xFrom - 1 && j == yFrom + 2)
						|| (i == xFrom + 2 && j == yFrom + 1) || (i == xFrom - 2 && j == yFrom + 1)
						|| (i == xFrom + 1 && j == yFrom - 2) || (i == xFrom - 1 && j == yFrom - 2)
						|| (i == xFrom + 2 && j == yFrom - 1) || (i == xFrom - 2 && j == yFrom - 1);
				if (condition) {
					generatedMoves.add(new Coordinate(i, j));
				}
			}
		}
		return generatedMoves;
	}
}

package com.capgemini.chess.algorithms.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public class KingMoves {

	public List<Coordinate> generateKingMoves(Board board, Coordinate from) {
		int xFrom = from.getX();
		int yFrom = from.getY();
		ArrayList<Coordinate> generatedMoves = new ArrayList<Coordinate>();
		for (int i = xFrom - 1; i <= xFrom + 1; i++) {
			for (int j = yFrom - 1; j <= yFrom + 1; j++) {
				if (i < 0 || j < 0 || i > 7 || j > 7)
					continue;
				if (board.getPieceAt(new Coordinate(i, j)) != null) {
					if (board.getPieceAt(new Coordinate(i, j)).getColor() == board.getPieceAt(from).getColor()) {
						continue;
					}
				}
				if (xFrom != i || yFrom != j)
					generatedMoves.add(new Coordinate(i, j));
			}
		}
		return generatedMoves;
	}
}

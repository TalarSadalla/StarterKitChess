package com.capgemini.chess.algorithms.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public class PawnMoves {

	public List<Coordinate> generatePawnMoves(Board board, Coordinate from) {
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
				if (xFrom != i || yFrom != j) {
					if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getColor() == Color.WHITE) {
						if (yFrom == 1) {
							if (j == (yFrom + 2) && xFrom == i) {
								generatedMoves.add(new Coordinate(i, j));
							} else if (((i == xFrom - 1 && j == yFrom + 1) || (i == xFrom + 1 && j == yFrom + 1))
									&& board.getPieceAt(new Coordinate(i, j)) != null) {
								generatedMoves.add(new Coordinate(i, j));
							} else if (j == (yFrom + 1) && xFrom == i
									&& board.getPieceAt(new Coordinate(i, j)) == null) {
								generatedMoves.add(new Coordinate(i, j));
							}
						} else if (((i == xFrom - 1 && j == yFrom + 1) || (i == xFrom + 1 && j == yFrom + 1))
								&& board.getPieceAt(new Coordinate(i, j)) != null) {
							generatedMoves.add(new Coordinate(i, j));
						} else if (j == (yFrom + 1) && xFrom == i && board.getPieceAt(new Coordinate(i, j)) == null) {
							generatedMoves.add(new Coordinate(i, j));
						}
					} else {
						if (yFrom == 6) {
							if (j == (yFrom - 2) && xFrom == i) {
								generatedMoves.add(new Coordinate(i, j));
							} else if (((i == xFrom - 1 && j == yFrom - 1) || (i == xFrom + 1 && j == yFrom - 1))
									&& board.getPieceAt(new Coordinate(i, j)) != null) {
								generatedMoves.add(new Coordinate(i, j));
							} else if (j == (yFrom - 1) && xFrom == i
									&& board.getPieceAt(new Coordinate(i, j)) == null) {
								generatedMoves.add(new Coordinate(i, j));
							}
						} else if (((i == xFrom - 1 && j == yFrom - 1) || (i == xFrom + 1 && j == yFrom - 1))
								&& board.getPieceAt(new Coordinate(i, j)) != null) {
							generatedMoves.add(new Coordinate(i, j));
						} else if (j == (yFrom - 1) && xFrom == i && board.getPieceAt(new Coordinate(i, j)) == null) {
							generatedMoves.add(new Coordinate(i, j));
						}
					}
				}
			}

		}
		return generatedMoves;
	}

}

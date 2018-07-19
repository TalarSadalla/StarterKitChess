package com.capgemini.chess.algorithms.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public class QueenMoves {
	public List<Coordinate> generateQueenMoves(Board board, Coordinate from) {
		ArrayList<Coordinate> generatedMoves = new ArrayList<Coordinate>();
		RookMoves rookMoves = new RookMoves();
		generatedMoves.addAll(rookMoves.generateRookMoves(board, from));
		BishopMoves bishopMoves = new BishopMoves();
		generatedMoves.addAll(bishopMoves.generateBishopMoves(board, from));

		return generatedMoves;
	}
}

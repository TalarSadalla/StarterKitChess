package com.capgemini.chess.algorithms.data;

import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveOnPieceException;
import com.capgemini.chess.algorithms.implementation.exceptions.NullFieldExcepion;
import com.capgemini.chess.algorithms.implementation.exceptions.OutOfBoardMoveException;

public class MoveValidator {

	public boolean isValid(Board board, int xFrom, int yFrom, int xTo, int yTo) throws InvalidMoveException {
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)) == null) {
			throw new NullFieldExcepion();
		}
		if (xTo == xFrom && yTo == yFrom)
			throw new InvalidMoveException();
		if (xTo < 0 || xTo > 7 || xFrom < 0 || xFrom > 7 || yTo < 0 || yTo > 7 || yFrom < 0 || yFrom > 7) {
			throw new OutOfBoardMoveException();
		} else {
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.KING) {
				return possibleKingMoves(board, xFrom, yFrom, xTo, yTo);
			}
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.KNIGHT) {
				return possibleKnightMoves(board, xFrom, yFrom, xTo, yTo);
			}
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.QUEEN) {
				return possibleQueenMoves(board, xFrom, yFrom, xTo, yTo);
			}
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.BISHOP) {
				return possibleBishopMoves(board, xFrom, yFrom, xTo, yTo);
			}
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.ROOK) {
				return possibleRookMoves(board, xFrom, yFrom, xTo, yTo);
			}
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.PAWN) {
				return possiblePawnMoves(board, xFrom, yFrom, xTo, yTo);
			}
		}
		return true;
	}

	private boolean isBoardFieldNotNull(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		Piece pieceFrom = board.getPieceAt(new Coordinate(xFrom, yFrom));
		Piece pieceTo = board.getPieceAt(new Coordinate(xTo, yTo));

		for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
			for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
				if (pieceTo != null) {
					if (pieceFrom.getColor().equals(pieceTo.getColor())) {
						throw new InvalidMoveOnPieceException();
					} else if (!(pieceFrom.getColor().equals(pieceTo.getColor()))
							&& pieceFrom.getType() != PieceType.KNIGHT) {
						return true;
					}
				}
			}
		}
		return false;

	}

	private boolean possibleKingMoves(Board board, int xFrom, int yFrom, int xTo, int yTo) {

		int distance = (((xTo - xFrom) ^ 2) + ((yTo - yFrom) ^ 2));
		for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
			for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
				
			}
		}
		return (distance == 1 || distance == 2);

	}

	private boolean possibleKnightMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {

		if (xTo != xFrom - 1 && xTo != xFrom + 1 && xTo != xFrom + 2 && xTo != xFrom - 2)
			throw new InvalidMoveException();
		if (yTo != yFrom - 1 && yTo != yFrom + 1 && yTo != yFrom - 2 && yTo != yFrom + 2)
			throw new InvalidMoveException();
		return true;

	}

	private boolean possibleQueenMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		if (possibleBishopMoves(board, xFrom, yFrom, xTo, yTo) && possibleRookMoves(board, xFrom, yFrom, xTo, yTo))
			return true;
		throw new InvalidMoveException();

	}

	private boolean possibleRookMoves(Board board, int xFrom, int yFrom, int xTo, int yTo) throws InvalidMoveException {
		if (xFrom == xTo)
			return true;
		if (yFrom == yTo)
			return true;
		throw new InvalidMoveException();

	}

	private boolean possibleBishopMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		if (Math.abs(xFrom - xTo) == Math.abs(yFrom - yTo))
			return true;
		throw new InvalidMoveException();

	}

	private boolean possiblePawnMoves(Board board, int xFrom, int yFrom, int xTo, int yTo) throws InvalidMoveException {
		for (int x = 0; x < board.SIZE; x++) {
			if ((xFrom == x && yFrom == 1) || (xFrom == x && yFrom == 6)) {
				if ((yTo == yFrom + 2 || yTo == yFrom - 2) && xFrom == xTo) {
					return true;
				}
			} else if ((yTo == yFrom + 1 || yTo == yFrom - 1) && xFrom == xTo) {
				return true;
			}
		}
		throw new InvalidMoveException();
	}
}

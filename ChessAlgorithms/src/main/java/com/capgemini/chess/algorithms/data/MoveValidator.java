package com.capgemini.chess.algorithms.data;

import java.util.ArrayList;

import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveOnPieceException;
import com.capgemini.chess.algorithms.implementation.exceptions.NullFieldExcepion;
import com.capgemini.chess.algorithms.implementation.exceptions.OutOfBoardMoveException;

public class MoveValidator {

	public Move isValid(Board board, int xFrom, int yFrom, int xTo, int yTo) throws InvalidMoveException {
		Move move = new Move();
		if (xTo < 0 || xTo > 7 || xFrom < 0 || xFrom > 7 || yTo < 0 || yTo > 7 || yFrom < 0 || yFrom > 7) {
			throw new OutOfBoardMoveException();
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)) == null) {
			throw new NullFieldExcepion();
		}
		if (xTo == xFrom && yTo == yFrom)
			throw new InvalidMoveException();

		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.KING) {
			ArrayList<Coordinate> filteredMoves = possibleKingMoves(board, xFrom, yFrom, xTo, yTo);
			move = isBoardFieldNotNull(board, xFrom, yFrom, xTo, yTo, filteredMoves);
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.KNIGHT) {
			ArrayList<Coordinate> filteredMoves = possibleKnightMoves(board, xFrom, yFrom, xTo, yTo);
			move = isBoardFieldNotNull(board, xFrom, yFrom, xTo, yTo, filteredMoves);
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.QUEEN) {
			ArrayList<Coordinate> filteredMoves = possibleQueenMoves(board, xFrom, yFrom, xTo, yTo);
			move = isBoardFieldNotNull(board, xFrom, yFrom, xTo, yTo, filteredMoves);
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.BISHOP) {
			ArrayList<Coordinate> filteredMoves = possibleBishopMoves(board, xFrom, yFrom, xTo, yTo);
			move = isBoardFieldNotNull(board, xFrom, yFrom, xTo, yTo, filteredMoves);
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.ROOK) {
			ArrayList<Coordinate> filteredMoves = possibleRookMoves(board, xFrom, yFrom, xTo, yTo);
			move = isBoardFieldNotNull(board, xFrom, yFrom, xTo, yTo, filteredMoves);
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType() == PieceType.PAWN) {
			ArrayList<Coordinate> filteredMoves = possiblePawnMoves(board, xFrom, yFrom, xTo, yTo);
			move = isBoardFieldNotNull(board, xFrom, yFrom, xTo, yTo, filteredMoves);
		}

		return move;
	}

	private Move isBoardFieldNotNull(Board board, int xFrom, int yFrom, int xTo, int yTo,
			ArrayList<Coordinate> filteredMoves) throws InvalidMoveException {
		Move move = new Move();
		for (Coordinate coordinate : filteredMoves) {
			Piece pieceFrom = board.getPieceAt(new Coordinate(coordinate.getX(), coordinate.getY()));
			Piece pieceTo = board.getPieceAt(new Coordinate(xTo, yTo));
			if (pieceTo != null) {
				if (pieceFrom.getColor().equals(pieceTo.getColor())) {
					throw new InvalidMoveOnPieceException();
				} else if (!(pieceFrom.getColor().equals(pieceTo.getColor()))
						&& pieceFrom.getType() != PieceType.KNIGHT) {
					move.setType(MoveType.CAPTURE);
					move.setFrom(new Coordinate(xFrom, yFrom));
					move.setTo(new Coordinate(xTo, yTo));
					move.setMovedPiece(board.getPieceAt(new Coordinate(xTo, yTo)));
				}
			} else {
				move.setType(MoveType.ATTACK);
				move.setFrom(new Coordinate(xFrom, yFrom));
				move.setTo(new Coordinate(xTo, yTo));
				move.setMovedPiece(board.getPieceAt(new Coordinate(xTo, yTo)));
			}
		}
		return move;
	}

	private ArrayList<Coordinate> possibleKingMoves(Board board, int xFrom, int yFrom, int xTo, int yTo) {

		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();

		for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
			for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
				int distance = (((xTo - xAxis) ^ 2) + ((yTo - yAxis) ^ 2));
				if (distance == 1 || distance == 2) {
					filteredMoves.add(new Coordinate(xAxis, yAxis));
				}
			}
		}
		// return (distance == 1 || distance == 2);
		return filteredMoves;

	}

	private ArrayList<Coordinate> possibleKnightMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
			for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
				boolean xAxisCondition = xTo == xAxis - 1 && xTo == xAxis + 1 && xTo == xAxis + 2 && xTo == xAxis - 2;
				boolean yAxisCondition = yTo == yAxis - 1 && yTo == yAxis + 1 && yTo == yAxis - 2 && yTo == yAxis + 2;
				if (xAxisCondition && yAxisCondition) {
					filteredMoves.add(new Coordinate(xAxis, yAxis));
				} else {
					throw new InvalidMoveException();
				}
			}
		}

		return filteredMoves;

	}

	private ArrayList<Coordinate> possibleQueenMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
			for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
				if (xAxis == xTo || yAxis == yTo || Math.abs(xAxis - xTo) == Math.abs(yAxis - yTo)) {
					filteredMoves.add(new Coordinate(xAxis, yAxis));
				} else {
					throw new InvalidMoveException();
				}
			}
		}
		return filteredMoves;
	}

	private ArrayList<Coordinate> possibleRookMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
			for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
				if (xAxis == xTo || yAxis == yTo) {
					filteredMoves.add(new Coordinate(xAxis, yAxis));
				} else {
					throw new InvalidMoveException();
				}
			}
		}
		return filteredMoves;

	}

	private ArrayList<Coordinate> possibleBishopMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
			for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
				if (Math.abs(xAxis - xTo) == Math.abs(yAxis - yTo)) {
					filteredMoves.add(new Coordinate(xAxis, yAxis));
				} else {
					throw new InvalidMoveException();
				}
			}
		}
		return filteredMoves;

	}

	private ArrayList<Coordinate> possiblePawnMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
			for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
				for (int x = 0; x < board.SIZE; x++) {
					if ((xAxis == x && yAxis == 1) || (xAxis == x && yAxis == 6)) {
						if ((yTo == yAxis + 2 || yTo == yAxis + 2) && xAxis == xTo) {
							filteredMoves.add(new Coordinate(xAxis, yAxis));
						}
					} else if ((yTo == yAxis + 1 || yTo == yAxis + 1) && xAxis == xTo) {
						filteredMoves.add(new Coordinate(xAxis, yAxis));
					} else {
						throw new InvalidMoveException();
					}
				}

			}
		}
		return filteredMoves;
	}
}

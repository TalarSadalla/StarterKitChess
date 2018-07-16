package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveOnPieceException;
import com.capgemini.chess.algorithms.implementation.exceptions.NullFieldExcepion;
import com.capgemini.chess.algorithms.implementation.exceptions.OutOfBoardMoveException;

public class MoveValidator {

	public Move validate(Board board, int xFrom, int yFrom, int xTo, int yTo) throws InvalidMoveException {
		Move move = new Move();
		if (xTo < 0 || xTo > 7 || xFrom < 0 || xFrom > 7 || yTo < 0 || yTo > 7 || yFrom < 0 || yFrom > 7) {
			throw new OutOfBoardMoveException();
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)) == null) {
			throw new NullFieldExcepion();
		}
		if (xTo == xFrom && yTo == yFrom)
			throw new InvalidMoveException();

		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.KING)) {
			ArrayList<Coordinate> filteredMoves = possibleKingMoves(board, xFrom, yFrom, xTo, yTo);
			move = isBoardFieldNotNull(board, xFrom, yFrom, xTo, yTo, filteredMoves);
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.KNIGHT)) {
			ArrayList<Coordinate> filteredMoves = possibleKnightMoves(board, xFrom, yFrom, xTo, yTo);
			move = isBoardFieldNotNull(board, xFrom, yFrom, xTo, yTo, filteredMoves);
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.QUEEN)) {
			ArrayList<Coordinate> filteredMoves = possibleQueenMoves(board, xFrom, yFrom, xTo, yTo);
			move = isBoardFieldNotNull(board, xFrom, yFrom, xTo, yTo, filteredMoves);
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.BISHOP)) {
			ArrayList<Coordinate> filteredMoves = possibleBishopMoves(board, xFrom, yFrom, xTo, yTo);
			move = isBoardFieldNotNull(board, xFrom, yFrom, xTo, yTo, filteredMoves);
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.ROOK)) {
			ArrayList<Coordinate> filteredMoves = possibleRookMoves(board, xFrom, yFrom, xTo, yTo);
			move = isBoardFieldNotNull(board, xFrom, yFrom, xTo, yTo, filteredMoves);
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.PAWN)) {
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
			if (pieceTo != null && (coordinate.getX() != xFrom && coordinate.getY() != yFrom)) {
				throw new InvalidMoveException();
			} else if (pieceTo != null && board.getPieceAt(new Coordinate(xFrom, yFrom)).getColor()
					.equals(board.getPieceAt(new Coordinate(xTo, yTo)).getColor())) {
				throw new InvalidMoveOnPieceException();
			} else if (pieceTo != null && (coordinate.getX() != xFrom && coordinate.getY() != yFrom)
					&& !(pieceFrom.getColor().equals(pieceTo.getColor()))
					&& !(pieceFrom.getType().equals(PieceType.KNIGHT))
					&& !(pieceFrom.getType().equals(PieceType.PAWN))) {
				move.setType(MoveType.CAPTURE);
				move.setFrom(new Coordinate(xFrom, yFrom));
				move.setTo(new Coordinate(xTo, yTo));
				move.setMovedPiece(board.getPieceAt(new Coordinate(xFrom, yFrom)));
			} else if (pieceTo != null && (coordinate.getX() != xFrom && coordinate.getY() != yFrom)
					&& !(pieceFrom.getColor().equals(pieceTo.getColor())) && pieceFrom.getType().equals(PieceType.PAWN)
					&& ((xTo == coordinate.getX() - 1 && yTo == coordinate.getY() + 1)
							|| (xTo == coordinate.getX() + 1 && yTo == coordinate.getY() + 1)
							|| (xTo == coordinate.getX() - 1 && yTo == coordinate.getY() - 1)
							|| (xTo == coordinate.getX() + 1 && yTo == coordinate.getY() - 1))) {
				move.setType(MoveType.CAPTURE);
				move.setFrom(new Coordinate(xFrom, yFrom));
				move.setTo(new Coordinate(xTo, yTo));
				move.setMovedPiece(board.getPieceAt(new Coordinate(xFrom, yFrom)));
			} else if (pieceTo != null && (coordinate.getX() == xFrom && coordinate.getY() == yFrom)
					&& !(pieceFrom.getColor().equals(pieceTo.getColor()))
					&& pieceFrom.getType().equals(PieceType.KNIGHT)) {
				move.setType(MoveType.CAPTURE);
				move.setFrom(new Coordinate(xFrom, yFrom));
				move.setTo(new Coordinate(xTo, yTo));
				move.setMovedPiece(board.getPieceAt(new Coordinate(xFrom, yFrom)));
			} else if (pieceTo != null && pieceFrom.getType().equals(PieceType.PAWN)) {
				throw new InvalidMoveException();
			} else {
				move.setType(MoveType.ATTACK);
				move.setFrom(new Coordinate(xFrom, yFrom));
				move.setTo(new Coordinate(xTo, yTo));
				move.setMovedPiece(board.getPieceAt(new Coordinate(xFrom, yFrom)));
			}
		}
		return move;
	}

	private ArrayList<Coordinate> possibleKingMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {

		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		int distance = 0;
		if (yFrom < yTo) {
			if (yTo > yFrom + 1 || xTo > xFrom + 1) {
				throw new InvalidMoveException();
			}
			for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
				for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
					distance = (int) (Math.pow(xTo - xAxis, 2) + Math.pow(yTo - yAxis, 2));
					validateKingMoves(filteredMoves, distance, xAxis, yAxis);
				}
			}
		} else {
			if (yTo > yFrom - 1 || xTo > xFrom - 1) {
				throw new InvalidMoveException();
			}
			for (int xAxis = xTo; xAxis >= xFrom; xAxis--) {
				for (int yAxis = yTo; yAxis >= yFrom; yAxis--) {
					distance = (int) (Math.pow(xTo - xAxis, 2) + Math.pow(yTo - yAxis, 2));
					validateKingMoves(filteredMoves, distance, xAxis, yAxis);
				}
			}
		}
		if (filteredMoves.size() == 0) {
			throw new InvalidMoveException();
		}
		return filteredMoves;
	}

	private void validateKingMoves(ArrayList<Coordinate> filteredMoves, int distance, int xAxis, int yAxis) {
		if (distance == 1 || distance == 2) {
			filteredMoves.add(new Coordinate(xAxis, yAxis));
		}
	}

	private ArrayList<Coordinate> possibleKnightMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		if (yFrom < yTo) {
			for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
				for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
					validateKnightMoves(xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		} else {
			for (int xAxis = xTo; xAxis >= xFrom; xAxis--) {
				for (int yAxis = yTo; yAxis >= yFrom; yAxis--) {
					validateKnightMoves(xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		}
		if (filteredMoves.size() == 0) {
			throw new InvalidMoveException();
		}
		return filteredMoves;

	}

	private void validateKnightMoves(int xTo, int yTo, ArrayList<Coordinate> filteredMoves, int xAxis, int yAxis) {
		boolean xAxisCondition = xTo == xAxis - 1 && xTo == xAxis + 1 && xTo == xAxis + 2 && xTo == xAxis - 2;
		boolean yAxisCondition = yTo == yAxis - 1 && yTo == yAxis + 1 && yTo == yAxis - 2 && yTo == yAxis + 2;
		if (xAxisCondition && yAxisCondition) {
			filteredMoves.add(new Coordinate(xAxis, yAxis));
		}
	}

	private ArrayList<Coordinate> possibleQueenMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		if (yFrom < yTo) {
			for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
				for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
					validateQueenMoves(xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		} else {
			for (int xAxis = xTo; xAxis >= xFrom; xAxis--) {
				for (int yAxis = yTo; yAxis >= yFrom; yAxis--) {
					validateQueenMoves(xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		}
		if (filteredMoves.size() == 0) {
			throw new InvalidMoveException();
		}
		return filteredMoves;
	}

	private void validateQueenMoves(int xTo, int yTo, ArrayList<Coordinate> filteredMoves, int xAxis, int yAxis) {
		if (xAxis == xTo || yAxis == yTo || Math.abs(xAxis - xTo) == Math.abs(yAxis - yTo)) {
			filteredMoves.add(new Coordinate(xAxis, yAxis));
		}
	}

	private ArrayList<Coordinate> possibleRookMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		if (yFrom < yTo) {
			for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
				for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
					validateRookMoves(xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		} else {
			for (int xAxis = xTo; xAxis >= xFrom; xAxis--) {
				for (int yAxis = yTo; yAxis >= yFrom; yAxis--) {
					validateRookMoves(xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		}
		if (filteredMoves.size() == 0) {
			throw new InvalidMoveException();
		}
		return filteredMoves;

	}

	private void validateRookMoves(int xTo, int yTo, ArrayList<Coordinate> filteredMoves, int xAxis, int yAxis) {
		if (xAxis == xTo || yAxis == yTo) {
			filteredMoves.add(new Coordinate(xAxis, yAxis));
		}
	}

	private ArrayList<Coordinate> possibleBishopMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		if (yFrom < yTo) {
			for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
				for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
					validateBishopMoves(xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		} else {
			for (int xAxis = xTo; xAxis >= xFrom; xAxis--) {
				for (int yAxis = yTo; yAxis >= yFrom; yAxis--) {
					validateBishopMoves(xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		}
		if (filteredMoves.size() == 0) {
			throw new InvalidMoveException();
		}
		return filteredMoves;

	}

	private void validateBishopMoves(int xTo, int yTo, ArrayList<Coordinate> filteredMoves, int xAxis, int yAxis) {
		if (Math.abs(xAxis - xTo) == Math.abs(yAxis - yTo)) {
			filteredMoves.add(new Coordinate(xAxis, yAxis));
		}
	}

	private ArrayList<Coordinate> possiblePawnMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getColor() == Color.WHITE) {
			for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
				for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
					validatePawnMoves(board, xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		} else {
			for (int xAxis = xTo; xAxis >= xFrom; xAxis--) {
				for (int yAxis = yTo; yAxis >= yFrom; yAxis--) {
					validatePawnMoves(board, xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		}
		if (filteredMoves.size() == 0) {
			throw new InvalidMoveException();
		}
		return filteredMoves;
	}

	private void validatePawnMoves(Board board, int xTo, int yTo, ArrayList<Coordinate> filteredMoves, int xAxis,
			int yAxis) {
		for (int x = 0; x < board.SIZE; x++) {
			if ((xAxis == x && yAxis == 1) || (xAxis == x && yAxis == 6)) {
				if ((yTo == yAxis + 2 && xAxis == xTo) || (yAxis == yTo - 2 && xAxis == xTo)) {
					filteredMoves.add(new Coordinate(xAxis, yAxis));
				}
			} else if ((yTo == yAxis + 1 || yTo == yAxis + 1) && xAxis == xTo) {
				filteredMoves.add(new Coordinate(xAxis, yAxis));
			}
		}
	}
}

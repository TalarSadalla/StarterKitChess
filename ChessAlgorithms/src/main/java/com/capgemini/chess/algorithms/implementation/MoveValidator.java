package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidBishopMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidKingMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidKnightMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveOnPieceException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidPawnMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidQueenMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidRookMoveException;
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
		Piece pieceFrom = board.getPieceAt(new Coordinate(xFrom, yFrom));
		Piece pieceTo = board.getPieceAt(new Coordinate(xTo, yTo));
		for (int index = 0; index < filteredMoves.size(); index++) {
			Coordinate coordinate = filteredMoves.get(index);
			Piece movingPiece = board.getPieceAt(coordinate);
			if (pieceTo != null) {
				if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getColor()
						.equals(board.getPieceAt(new Coordinate(xTo, yTo)).getColor())) {
					throw new InvalidMoveOnPieceException();
				} else if (!(pieceFrom.equals(new Coordinate(xFrom, yFrom)))
						&& !(pieceFrom.getColor().equals(pieceTo.getColor()))
						&& pieceFrom.getType().equals(PieceType.KNIGHT)) {
					move.setType(MoveType.CAPTURE);
					move.setFrom(new Coordinate(xFrom, yFrom));
					move.setTo(new Coordinate(xTo, yTo));
					move.setMovedPiece(board.getPieceAt(new Coordinate(xFrom, yFrom)));

				} else if (movingPiece == null && movingPiece != pieceFrom) {
				} else {
					if (!(pieceFrom.getType().equals(PieceType.KNIGHT))
							&& !(pieceFrom.getType().equals(PieceType.PAWN))) {
						move.setType(MoveType.CAPTURE);
						move.setFrom(new Coordinate(xFrom, yFrom));
						move.setTo(new Coordinate(xTo, yTo));
						move.setMovedPiece(board.getPieceAt(new Coordinate(xFrom, yFrom)));
					} else if (pieceFrom.getType().equals(PieceType.PAWN)) {
						move.setType(MoveType.CAPTURE);
						move.setFrom(new Coordinate(xFrom, yFrom));
						move.setTo(new Coordinate(xTo, yTo));
						move.setMovedPiece(board.getPieceAt(new Coordinate(xFrom, yFrom)));
					} else if (pieceFrom.getType().equals(PieceType.PAWN) && (yTo == yFrom + 1 || yTo == yFrom - 1)) {
						throw new InvalidMoveException();
					}
				}
			} else if (movingPiece == null && pieceTo == null) {
				move.setType(MoveType.ATTACK);
				move.setFrom(new Coordinate(xFrom, yFrom));
				move.setTo(new Coordinate(xTo, yTo));
				move.setMovedPiece(board.getPieceAt(new Coordinate(xFrom, yFrom)));
			} else {
				throw new InvalidMoveException();
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
				throw new InvalidKingMoveException();
			}
			for (int xAxis = xFrom - 1; xAxis <= xTo; xAxis++) {
				for (int yAxis = yFrom; yAxis <= yTo; yAxis++) {
					if (xAxis != xFrom && yAxis != yFrom) {
						distance = (int) (Math.pow(xTo - xAxis, 2) + Math.pow(yTo - yAxis, 2));
						validateKingMoves(filteredMoves, distance, xAxis, yAxis);
					}
				}
			}
		} else {
			if (yTo > yFrom - 1 || xTo > xFrom - 1) {
				throw new InvalidKingMoveException();
			}
			for (int xAxis = xFrom - 1; xAxis >= xTo; xAxis--) {
				for (int yAxis = yFrom; yAxis <= yFrom; yAxis--) {
					if (xAxis != xFrom && yAxis != yFrom) {
						distance = (int) (Math.pow(xTo - xAxis, 2) + Math.pow(yTo - yAxis, 2));
						validateKingMoves(filteredMoves, distance, xAxis, yAxis);
					}
				}
			}
		}
		if (filteredMoves.size() == 0) {
			throw new InvalidKingMoveException();
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
		if (yTo > yFrom) {
			validateKnightMoves(xFrom, yFrom, xTo, yTo, filteredMoves);
		} else {
			validateKnightMoves(xTo, yTo, xFrom, yFrom, filteredMoves);
		}
		if (filteredMoves.size() == 0) {
			throw new InvalidKnightMoveException();
		}
		return filteredMoves;

	}

	private void validateKnightMoves(int xFrom, int yFrom, int xTo, int yTo, ArrayList<Coordinate> filteredMoves) {
		boolean condition = (xTo == xFrom + 1 && yTo == yFrom + 2) || (xTo == xFrom - 1 && yTo == yFrom + 2)
				|| (xTo == xFrom + 2 && yTo == yFrom + 1) || (xTo == xFrom - 2 && yTo == yFrom + 1)
				|| (xTo == xFrom + 1 && yTo == yFrom - 2) || (xTo == xFrom - 1 && yTo == yFrom - 2)
				|| (xTo == xFrom + 2 && yTo == yFrom - 1) || (xTo == xFrom - 2 && yTo == yFrom - 1);
		if (condition) {
			filteredMoves.add(new Coordinate(xTo, yTo));
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
			throw new InvalidQueenMoveException();
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
			throw new InvalidRookMoveException();
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
					if (xAxis != xFrom && yAxis != yFrom)
						validateBishopMoves(xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		} else {
			for (int xAxis = xFrom; xAxis <= xTo; xAxis++) {
				for (int yAxis = yFrom; yAxis >= yTo; yAxis--) {
					if (xAxis != xFrom && yAxis != yFrom)
						validateBishopMoves(xTo, yTo, filteredMoves, xAxis, yAxis);
				}
			}
		}
		if (filteredMoves.size() == 0) {
			throw new InvalidBishopMoveException();
		}
		return filteredMoves;

	}

	private void validateBishopMoves(int xTo, int yTo, ArrayList<Coordinate> filteredMoves, int xAxis, int yAxis) {
		if ((Math.abs(xAxis - xTo) == Math.abs(yAxis - yTo))) {
			filteredMoves.add(new Coordinate(xAxis, yAxis));
		}
	}

	private ArrayList<Coordinate> possiblePawnMoves(Board board, int xFrom, int yFrom, int xTo, int yTo)
			throws InvalidMoveException {
		ArrayList<Coordinate> filteredMoves = new ArrayList<Coordinate>();
		if (yFrom < yTo) {
			validatePawnMoves(board, xFrom, yFrom, xTo, yTo, filteredMoves);

		} else {
			validatePawnMoves(board, xFrom, yFrom, xTo, yTo, filteredMoves);

		}
		if (filteredMoves.size() == 0) {
			throw new InvalidPawnMoveException();
		}
		return filteredMoves;
	}

	private void validatePawnMoves(Board board, int xFrom, int yFrom, int xTo, int yTo,
			ArrayList<Coordinate> filteredMoves) {
		for (int x = 0; x < board.SIZE; x++) {
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getColor() == Color.WHITE) {
				if (yFrom == 1) {
					if (yTo == (yFrom + 2) && xFrom == x) {
						filteredMoves.add(new Coordinate(xFrom, yFrom));
					}
				} else if (((xTo == xFrom - 1 && yTo == yFrom + 1) || (xTo == xFrom + 1 && yTo == yFrom + 1))
						&& board.getPieceAt(new Coordinate(xTo, yTo)) != null) {
					filteredMoves.add(new Coordinate(xTo, yTo));
				}
			} else {
				if (yFrom == 6) {
					if (yTo == (yFrom - 2) && xFrom == x) {
						filteredMoves.add(new Coordinate(xTo, yTo));
					}
				} else if (((xTo == xFrom - 1 && yTo == yFrom - 1) || (xTo == xFrom + 1 && yTo == yFrom - 1))
						&& board.getPieceAt(new Coordinate(xTo, yTo)) != null) {
					filteredMoves.add(new Coordinate(xTo, yTo));
				}
			}

		}
	}
}

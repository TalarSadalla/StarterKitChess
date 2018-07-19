package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BishopMoves;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.KingMoves;
import com.capgemini.chess.algorithms.data.enums.KnightMoves;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.PawnMoves;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.enums.QueenMoves;
import com.capgemini.chess.algorithms.data.enums.RookMoves;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidColorException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.NullFieldExcepion;
import com.capgemini.chess.algorithms.implementation.exceptions.OutOfBoardMoveException;

public class MoveValidator {

	private KingMoves kingMoves;
	private KnightMoves knightMoves;
	private QueenMoves queenMoves;
	private BishopMoves bishopMoves;
	private RookMoves rookMoves;
	private PawnMoves pawnMoves;

	public boolean isKingInCheckValidation(Board board, Color kingColor, Coordinate kingCoordinates) {
		// if (board.getPieceAt(kingCoordinates).getType() != PieceType.KING) {
		// return false;
		// }
		List<Coordinate> possibleOppositeColorMoves = board.getAllPieces(Color.getOppositeColor(kingColor));
		for (int i = 0; i < possibleOppositeColorMoves.size(); i++) {
			Coordinate opositeColorPieceCoordinate = possibleOppositeColorMoves.get(i);
			try {
				validate(board, opositeColorPieceCoordinate, kingCoordinates);
			} catch (InvalidMoveException e) {
				continue;
			}
			return true;
		}
		return false;
	}

	public boolean isAnyMoveValid(Board board, Coordinate kingCoordinates,
			ArrayList<Coordinate> piecesInSpecifiedColorList) {
		{
			if (isKingInCheckValidation(board, board.getPieceAt(kingCoordinates).getColor(), kingCoordinates))
				return false;
			return true;
		}
	}

	public List<Move> getAllPossibleMoves(Board board, Coordinate kingCoordinates,
			ArrayList<Coordinate> piecesInSpecifiedColorList) {
		List<Move> allPossibleMoves = new ArrayList<>();
		Board copyOfBoard = new Board(board);

		for (int i = 0; i < piecesInSpecifiedColorList.size(); i++) {
			if (copyOfBoard.getPieceAt(piecesInSpecifiedColorList.get(i)).getType().equals(PieceType.KING)) {
				kingMoves = new KingMoves();
				List<Coordinate> filteredMoves = kingMoves.generateKingMoves(board, piecesInSpecifiedColorList.get(i));
				isAnyMovePossibleForSpecificPiece(piecesInSpecifiedColorList, copyOfBoard, i, filteredMoves,
						allPossibleMoves);
			}
			if (copyOfBoard.getPieceAt(piecesInSpecifiedColorList.get(i)).getType().equals(PieceType.KNIGHT)) {
				knightMoves = new KnightMoves();
				List<Coordinate> filteredMoves = knightMoves.generateKnightMoves(board,
						piecesInSpecifiedColorList.get(i));
				isAnyMovePossibleForSpecificPiece(piecesInSpecifiedColorList, copyOfBoard, i, filteredMoves,
						allPossibleMoves);
			}
			if (copyOfBoard.getPieceAt(piecesInSpecifiedColorList.get(i)).getType().equals(PieceType.QUEEN)) {
				queenMoves = new QueenMoves();
				List<Coordinate> filteredMoves = queenMoves.generateQueenMoves(board,
						piecesInSpecifiedColorList.get(i));
				isAnyMovePossibleForSpecificPiece(piecesInSpecifiedColorList, copyOfBoard, i, filteredMoves,
						allPossibleMoves);
			}
			if (copyOfBoard.getPieceAt(piecesInSpecifiedColorList.get(i)).getType().equals(PieceType.BISHOP)) {
				bishopMoves = new BishopMoves();
				List<Coordinate> filteredMoves = bishopMoves.generateBishopMoves(board,
						piecesInSpecifiedColorList.get(i));
				isAnyMovePossibleForSpecificPiece(piecesInSpecifiedColorList, copyOfBoard, i, filteredMoves,
						allPossibleMoves);
			}
			if (copyOfBoard.getPieceAt(piecesInSpecifiedColorList.get(i)).getType().equals(PieceType.ROOK)) {
				rookMoves = new RookMoves();
				List<Coordinate> filteredMoves = rookMoves.generateRookMoves(board, piecesInSpecifiedColorList.get(i));
				isAnyMovePossibleForSpecificPiece(piecesInSpecifiedColorList, copyOfBoard, i, filteredMoves,
						allPossibleMoves);
			}
			if (copyOfBoard.getPieceAt(piecesInSpecifiedColorList.get(i)).getType().equals(PieceType.PAWN)) {
				pawnMoves = new PawnMoves();
				List<Coordinate> filteredMoves = pawnMoves.generatePawnMoves(board, piecesInSpecifiedColorList.get(i));
				isAnyMovePossibleForSpecificPiece(piecesInSpecifiedColorList, copyOfBoard, i, filteredMoves,
						allPossibleMoves);
			}
		}
		return allPossibleMoves;
	}

	private boolean isAnyMovePossibleForSpecificPiece(ArrayList<Coordinate> piecesInSpecifiedColorList,
			Board copyOfBoard, int i, List<Coordinate> filteredMoves, List<Move> allPossibleMoves) {
		for (int j = 0; j < filteredMoves.size(); j++) {
			try {
				allPossibleMoves.add(validate(copyOfBoard, piecesInSpecifiedColorList.get(i), filteredMoves.get(j)));
			} catch (InvalidMoveException e) {
				continue;
			}
			return true;
		}
		return false;
	}

	public Move validate(Board board, Coordinate from, Coordinate to) throws InvalidMoveException {
		Move move = new Move();

		int xFrom = from.getX();
		int yFrom = from.getY();

		int xTo = to.getX();
		int yTo = to.getY();

		Color playerColor;
		if (board.getMoveHistory().size() % 2 == 0) {
			playerColor = Color.WHITE;
		} else {
			playerColor = Color.BLACK;
		}

		if (xTo < 0 || xTo > 7 || xFrom < 0 || xFrom > 7 || yTo < 0 || yTo > 7 || yFrom < 0 || yFrom > 7) {
			throw new OutOfBoardMoveException();
		}
		if (board.getPieceAt(new Coordinate(xFrom, yFrom)) == null) {
			throw new NullFieldExcepion();
		}
		if (xTo == xFrom && yTo == yFrom)
			throw new InvalidMoveException();
		if (playerColor == board.getPieceAt(from).getColor()) {
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.KING)) {
				KingMoves kingMoves = new KingMoves();
				List<Coordinate> filteredMoves = kingMoves.generateKingMoves(board, from);
				move = isMovePossible(board, from, to, filteredMoves);
			}
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.KNIGHT)) {
				KnightMoves knightMoves = new KnightMoves();
				List<Coordinate> filteredMoves = knightMoves.generateKnightMoves(board, from);
				move = isMovePossible(board, from, to, filteredMoves);
			}
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.QUEEN)) {
				QueenMoves queenMoves = new QueenMoves();
				List<Coordinate> filteredMoves = queenMoves.generateQueenMoves(board, from);
				move = isMovePossible(board, from, to, filteredMoves);
			}
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.BISHOP)) {
				BishopMoves bishopMoves = new BishopMoves();
				List<Coordinate> filteredMoves = bishopMoves.generateBishopMoves(board, from);
				move = isMovePossible(board, from, to, filteredMoves);
			}
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.ROOK)) {
				RookMoves rookMoves = new RookMoves();
				List<Coordinate> filteredMoves = rookMoves.generateRookMoves(board, from);
				move = isMovePossible(board, from, to, filteredMoves);
			}
			if (board.getPieceAt(new Coordinate(xFrom, yFrom)).getType().equals(PieceType.PAWN)) {
				PawnMoves pawnMoves = new PawnMoves();
				List<Coordinate> filteredMoves = pawnMoves.generatePawnMoves(board, from);
				move = isMovePossible(board, from, to, filteredMoves);
			}
		} else {
			throw new InvalidColorException();
		}
		return move;
	}

	private Move isMovePossible(Board board, Coordinate from, Coordinate to, List<Coordinate> filteredMoves)
			throws InvalidMoveException {
		Move move = new Move();
		if (filteredMoves.contains(to)) {
			if (board.getPieceAt(to) != null && board.getPieceAt(from).getColor() != board.getPieceAt(to).getColor()) {
				move.setType(MoveType.CAPTURE);
				move.setFrom(from);
				move.setTo(to);
				move.setMovedPiece(board.getPieceAt(from));
			} else if (board.getPieceAt(to) == null) {
				move.setType(MoveType.ATTACK);
				move.setFrom(from);
				move.setTo(to);
				move.setMovedPiece(board.getPieceAt(from));
			}
		} else {
			throw new InvalidMoveException();
		}

		return move;

	}
}

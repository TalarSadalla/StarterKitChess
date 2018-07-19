package com.capgemini.chess.algorithms.data.generated;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;

/**
 * Board representation. Board objects are generated based on move history.
 * 
 * @author Michal Bejm
 *
 */
public class Board {

	public static final int SIZE = 8;

	private Piece[][] pieces = new Piece[SIZE][SIZE];
	private List<Move> moveHistory = new ArrayList<>();
	private BoardState state;

	public Board() {
	}

	public Board(Board boardToCopy) {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.pieces[i][j] = boardToCopy.pieces[i][j];
			}
		}
	}

	public List<Move> getMoveHistory() {
		return moveHistory;
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public BoardState getState() {
		return state;
	}

	public void setState(BoardState state) {
		this.state = state;
	}

	/**
	 * Sets chess piece on board based on given coordinates
	 * 
	 * @param piece
	 *            chess piece
	 * @param board
	 *            chess board
	 * @param coordinate
	 *            given coordinates
	 */
	public void setPieceAt(Piece piece, Coordinate coordinate) {
		pieces[coordinate.getX()][coordinate.getY()] = piece;
	}

	/**
	 * Gets chess piece from board based on given coordinates
	 * 
	 * @param coordinate
	 *            given coordinates
	 * @return chess piece
	 */
	public Piece getPieceAt(Coordinate coordinate) {
		return pieces[coordinate.getX()][coordinate.getY()];
	}

	public Coordinate findKingInSpecifiedColor(Color kingColor) {
		int kingCoordinateX = 0;
		int kingCoordinateY = 0;
		Coordinate kingCoordinates = new Coordinate(kingCoordinateX, kingCoordinateY);
		for (int xAxis = 0; xAxis < SIZE; xAxis++) {
			for (int yAxis = 0; yAxis < SIZE; yAxis++) {
				Coordinate pieceCoordinates = new Coordinate(xAxis, yAxis);
				if (getPieceAt(pieceCoordinates) != null) {
					if (getPieceAt(pieceCoordinates).getType() == PieceType.KING
							&& (getPieceAt(pieceCoordinates).getColor() == kingColor)) {
						kingCoordinateX = xAxis;
						kingCoordinateY = yAxis;
						kingCoordinates = new Coordinate(kingCoordinateX, kingCoordinateY);
						break;
					}
				}
			}
		}
		return kingCoordinates;
	}

	public ArrayList<Coordinate> findPiecesInSpecifiedColor(Color nextMoveColor) {
		ArrayList<Coordinate> piecesInSpecifiedColorList = new ArrayList<Coordinate>();
		int pieceCoordinateX = 0;
		int pieceCoordinateY = 0;
		Coordinate pieceCoordinates = new Coordinate(pieceCoordinateX, pieceCoordinateY);
		for (int xAxis = 0; xAxis < SIZE; xAxis++) {
			for (int yAxis = 0; yAxis < SIZE; yAxis++) {
				pieceCoordinates = new Coordinate(xAxis, yAxis);
				if (getPieceAt(pieceCoordinates) != null) {
					if ((getPieceAt(pieceCoordinates).getColor() == nextMoveColor)) {
						piecesInSpecifiedColorList.add(new Coordinate(xAxis, yAxis));
					}
				}
			}
		}
		return piecesInSpecifiedColorList;
	}

	public List<Coordinate> getAllPieces(Color color) {
		ArrayList<Coordinate> piecesList = new ArrayList<>();
		for (int xAxis = 0; xAxis < SIZE; xAxis++) {
			for (int yAxis = 0; yAxis < SIZE; yAxis++) {
				Coordinate colorPieceCoordinate = new Coordinate(xAxis, yAxis);
				Piece colorPiece = getPieceAt(colorPieceCoordinate);
				if (getPieceAt(colorPieceCoordinate) != null) {
					if ((colorPiece.getColor().equals(color))) {
						piecesList.add(colorPieceCoordinate);
					}
				}
			}
		}
		return piecesList;
	}

}

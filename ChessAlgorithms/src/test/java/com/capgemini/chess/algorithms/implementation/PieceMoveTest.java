package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.BishopMoves;
import com.capgemini.chess.algorithms.data.enums.KingMoves;
import com.capgemini.chess.algorithms.data.enums.KnightMoves;
import com.capgemini.chess.algorithms.data.enums.PawnMoves;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.QueenMoves;
import com.capgemini.chess.algorithms.data.enums.RookMoves;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class PieceMoveTest {

	private PawnMoves pawnMoves;

	@Test
	public void testPossibleKingMoves() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(1, 1));
		KingMoves kingMoves = new KingMoves();

		// when
		List<Coordinate> pawnCoordinates = kingMoves.generateKingMoves(board, new Coordinate(1, 1));
		List<Coordinate> mockList = new ArrayList<>();
		mockList.add(new Coordinate(0, 0));
		mockList.add(new Coordinate(0, 1));
		mockList.add(new Coordinate(0, 2));
		mockList.add(new Coordinate(1, 0));
		mockList.add(new Coordinate(1, 2));
		mockList.add(new Coordinate(2, 0));
		mockList.add(new Coordinate(2, 1));
		mockList.add(new Coordinate(2, 2));
		// then
		assertEquals(mockList.get(0), pawnCoordinates.get(0));
		assertEquals(mockList.get(1), pawnCoordinates.get(1));
		assertEquals(mockList.get(2), pawnCoordinates.get(2));
		assertEquals(mockList.get(3), pawnCoordinates.get(3));
		assertEquals(mockList.get(4), pawnCoordinates.get(4));
		assertEquals(mockList.size(), pawnCoordinates.size());
	}

	@Test
	public void testPossibleKnightMoves() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 1));
		KnightMoves knightMoves = new KnightMoves();

		// when
		List<Coordinate> knightCoordinates = knightMoves.generateKnightMoves(board, new Coordinate(1, 1));
		List<Coordinate> mockList = new ArrayList<>();
		mockList.add(new Coordinate(0, 3));
		mockList.add(new Coordinate(2, 3));
		mockList.add(new Coordinate(3, 0));
		mockList.add(new Coordinate(3, 2));
		// then
		assertEquals(mockList.get(0), knightCoordinates.get(0));
		assertEquals(mockList.get(1), knightCoordinates.get(1));
		assertEquals(mockList.get(2), knightCoordinates.get(2));
		assertEquals(mockList.size(), knightCoordinates.size());
	}

	@Test
	public void testPossibleRookMoves() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 1));
		RookMoves rookMoves = new RookMoves();

		// when
		List<Coordinate> rookCoordinates = rookMoves.generateRookMoves(board, new Coordinate(1, 1));
		List<Coordinate> mockList = new ArrayList<>();
		mockList.add(new Coordinate(0, 1));
		mockList.add(new Coordinate(1, 0));
		mockList.add(new Coordinate(1, 2));
		mockList.add(new Coordinate(1, 3));
		mockList.add(new Coordinate(1, 4));
		mockList.add(new Coordinate(1, 5));
		mockList.add(new Coordinate(1, 6));
		mockList.add(new Coordinate(1, 7));
		mockList.add(new Coordinate(2, 1));
		mockList.add(new Coordinate(3, 1));
		mockList.add(new Coordinate(4, 1));
		mockList.add(new Coordinate(5, 1));
		mockList.add(new Coordinate(6, 1));
		mockList.add(new Coordinate(7, 1));

		// then

		assertEquals(mockList.size(), rookCoordinates.size());
	}

	@Test
	public void testPossibleBishopMoves() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 1));
		BishopMoves bishopMoves = new BishopMoves();

		// when
		List<Coordinate> bishopCoordinates = bishopMoves.generateBishopMoves(board, new Coordinate(1, 1));
		List<Coordinate> mockList = new ArrayList<>();
		mockList.add(new Coordinate(0, 1));
		mockList.add(new Coordinate(0, 2));
		mockList.add(new Coordinate(2, 0));
		mockList.add(new Coordinate(2, 2));
		mockList.add(new Coordinate(3, 3));
		mockList.add(new Coordinate(4, 4));
		mockList.add(new Coordinate(5, 5));
		mockList.add(new Coordinate(6, 6));
		mockList.add(new Coordinate(7, 7));

		// then
		assertEquals(mockList.get(1), bishopCoordinates.get(1));
		assertEquals(mockList.get(5), bishopCoordinates.get(5));
		assertEquals(mockList.get(8), bishopCoordinates.get(8));
		assertEquals(mockList.size(), bishopCoordinates.size());
	}

	@Test
	public void testPossibleQueenMoves() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 1));
		QueenMoves queenMoves = new QueenMoves();

		// when
		List<Coordinate> queenCoordinates = queenMoves.generateQueenMoves(board, new Coordinate(1, 1));
		List<Coordinate> mockList = new ArrayList<>();
		mockList.add(new Coordinate(0, 1));
		mockList.add(new Coordinate(1, 0));
		mockList.add(new Coordinate(1, 2));
		mockList.add(new Coordinate(1, 3));
		mockList.add(new Coordinate(1, 4));
		mockList.add(new Coordinate(1, 5));
		mockList.add(new Coordinate(1, 6));
		mockList.add(new Coordinate(1, 7));
		mockList.add(new Coordinate(2, 1));
		mockList.add(new Coordinate(3, 1));
		mockList.add(new Coordinate(4, 1));
		mockList.add(new Coordinate(5, 1));
		mockList.add(new Coordinate(6, 1));
		mockList.add(new Coordinate(7, 1));
		mockList.add(new Coordinate(0, 0));
		mockList.add(new Coordinate(0, 2));
		mockList.add(new Coordinate(2, 0));
		mockList.add(new Coordinate(2, 2));
		mockList.add(new Coordinate(3, 3));
		mockList.add(new Coordinate(4, 4));
		mockList.add(new Coordinate(5, 5));
		mockList.add(new Coordinate(6, 6));
		mockList.add(new Coordinate(7, 7));
		// then
		// assertEquals(mockList.get(3), queenCoordinates.get(3));
		// assertEquals(mockList.get(7), queenCoordinates.get(7));
		// assertEquals(mockList.get(12), queenCoordinates.get(12));
		assertEquals(mockList.size(), queenCoordinates.size());
	}

	@Test
	public void testPossiblePawnMoves() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(1, 1));
		pawnMoves = new PawnMoves();

		// when
		List<Coordinate> pawnCoordinates = pawnMoves.generatePawnMoves(board, new Coordinate(1, 1));
		List<Coordinate> mockList = new ArrayList<>();
		mockList.add(new Coordinate(1, 2));
		mockList.add(new Coordinate(1, 3));
		// then
		assertEquals(mockList, pawnCoordinates);
	}

	@Test
	public void testPossiblePawnMovesAttack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(1, 1));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(2, 2));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(0, 2));
		pawnMoves = new PawnMoves();

		// when
		List<Coordinate> pawnCoordinates = pawnMoves.generatePawnMoves(board, new Coordinate(1, 1));
		List<Coordinate> mockList = new ArrayList<>();
		mockList.add(new Coordinate(0, 2));
		mockList.add(new Coordinate(1, 2));
		mockList.add(new Coordinate(1, 3));
		mockList.add(new Coordinate(2, 2));

		// then
		assertEquals(mockList, pawnCoordinates);
	}

	@Test
	public void testPossibleBlackPawnMovesAttack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(1, 6));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(0, 5));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(2, 5));
		pawnMoves = new PawnMoves();

		// when
		List<Coordinate> pawnCoordinates = pawnMoves.generatePawnMoves(board, new Coordinate(1, 6));
		List<Coordinate> mockList = new ArrayList<>();
		mockList.add(new Coordinate(1, 4));
		mockList.add(new Coordinate(1, 5));
		// then
		assertEquals(mockList, pawnCoordinates);
	}

	@Test
	public void testPossibleBlackPawnMoves() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(1, 6));
		pawnMoves = new PawnMoves();

		// when
		List<Coordinate> pawnCoordinates = pawnMoves.generatePawnMoves(board, new Coordinate(1, 6));
		List<Coordinate> mockList = new ArrayList<>();

		mockList.add(new Coordinate(1, 4));
		mockList.add(new Coordinate(1, 5));
		// then
		assertEquals(mockList, pawnCoordinates);
	}
}

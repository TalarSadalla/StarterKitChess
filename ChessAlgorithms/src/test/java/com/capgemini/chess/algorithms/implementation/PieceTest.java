package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class PieceTest {

	@Test
	public void testPerformMovePawnAttack() throws InvalidMoveException {
		// given
		Board board = new Board();		
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(4, 1));
		//board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 1));
		
		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(4, 1), new Coordinate(4, 3));
		//Move moveWhitePawn = boardManager.performMove(new Coordinate(4, 1), new Coordinate(4, 3));

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_PAWN, move.getMovedPiece());
		//assertEquals(MoveType.ATTACK, moveWhitePawn.getType());
		//assertEquals(Piece.BLACK_PAWN, moveWhitePawn.getMovedPiece());
	}

	@Test
	public void testPerformMovePawnCapture() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(0, 1));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(1, 2));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		Move move = boardManager.performMove(new Coordinate(0, 1), new Coordinate(1, 2));

		// then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(Piece.WHITE_BISHOP, move.getMovedPiece());
	}

	@Test
	public void testPerformMoveBishopAttack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(0, 6));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(0, 6), new Coordinate(6, 0));
		

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_BISHOP, move.getMovedPiece());
	}

	@Test
	public void testPerformMoveBishopCapture() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(0, 6));
		board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(6, 0));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		Move move = boardManager.performMove(new Coordinate(0, 6), new Coordinate(6, 0));
		try {
			boardManager.performMove(new Coordinate(6, 6), new Coordinate(6, 1));
		} catch (InvalidMoveException e) {
			exceptionThrown = true;
		}

		// then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(Piece.WHITE_BISHOP, move.getMovedPiece());
		assertTrue(exceptionThrown);
	}

	@Test
	public void testPerformMoveRookAttack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(2, 2));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(2, 2), new Coordinate(2, 4));
		
		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_ROOK, move.getMovedPiece());
	}

	@Test
	public void testPerformMoveRookCapture() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(2, 2));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(5, 2));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(2, 2), new Coordinate(5, 2));

		// then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(Piece.WHITE_ROOK, move.getMovedPiece());
	}

	@Test
	public void testPerformMoveKnightAttack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(3, 4));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(3, 4), new Coordinate(4, 6));

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_KNIGHT, move.getMovedPiece());
	}

	@Test
	public void testPerformMoveKnightCapture() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(3, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(5, 5));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(3, 4), new Coordinate(5, 5));

		// then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(Piece.WHITE_KNIGHT, move.getMovedPiece());
	}

	@Test
	public void testPerformMoveKingAttack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(4, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(4, 0), new Coordinate(4, 1));
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(4, 7), new Coordinate(4, 5));
		} catch (InvalidMoveException e) {
			exceptionThrown = true;
		}

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_KING, move.getMovedPiece());
		assertTrue(exceptionThrown);
	}
	
	@Test
	public void testPerformMoveKingCapture() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 1));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(4, 0), new Coordinate(4, 1));		

		// then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(Piece.WHITE_KING, move.getMovedPiece());
	}
	
	@Test
	public void testPerformMoveQueenCapture() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(5, 0));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(2, 3));
		
		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(5, 0), new Coordinate(2, 3));
		
		// then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(Piece.WHITE_QUEEN, move.getMovedPiece());
	}
	
	@Test
	public void testPerformMoveQueenAttack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(5, 0));
		
		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(5, 0), new Coordinate(3, 0));
		
		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_QUEEN, move.getMovedPiece());
	}

}

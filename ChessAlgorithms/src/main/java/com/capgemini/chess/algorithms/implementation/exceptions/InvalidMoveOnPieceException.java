package com.capgemini.chess.algorithms.implementation.exceptions;

public class InvalidMoveOnPieceException extends InvalidMoveException {
	
	private static final long serialVersionUID = -7068022351491323049L;

	public InvalidMoveOnPieceException() {
		super("This field is occupied by Your Piece, invalid move!");
	}

}

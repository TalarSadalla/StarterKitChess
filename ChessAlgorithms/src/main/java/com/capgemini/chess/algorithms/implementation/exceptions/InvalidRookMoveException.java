package com.capgemini.chess.algorithms.implementation.exceptions;

public class InvalidRookMoveException extends InvalidMoveException {	

		private static final long serialVersionUID = 1992475617354998105L;

	public InvalidRookMoveException() {
		super("You can't move with Rook!");
	}

}

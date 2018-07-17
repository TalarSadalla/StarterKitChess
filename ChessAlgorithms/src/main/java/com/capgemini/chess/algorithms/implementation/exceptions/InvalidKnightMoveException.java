package com.capgemini.chess.algorithms.implementation.exceptions;

public class InvalidKnightMoveException extends InvalidMoveException {	

	private static final long serialVersionUID = 36592934024563569L;

	public InvalidKnightMoveException() {
		super("You can't move with Knight!");
	}
}

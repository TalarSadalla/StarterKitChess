package com.capgemini.chess.algorithms.implementation.exceptions;

public class InvalidColorException extends InvalidMoveException {
	
	private static final long serialVersionUID = 4921882562195976256L;

	public InvalidColorException() {
		super("Invalid color move!");
	}
}

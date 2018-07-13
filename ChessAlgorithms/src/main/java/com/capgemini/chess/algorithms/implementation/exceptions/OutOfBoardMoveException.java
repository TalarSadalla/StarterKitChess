package com.capgemini.chess.algorithms.implementation.exceptions;

public class OutOfBoardMoveException extends InvalidMoveException {
	
	private static final long serialVersionUID = -7052700378836555835L;
			
	public OutOfBoardMoveException() {
		super("Out of board move!");
	}
	
}

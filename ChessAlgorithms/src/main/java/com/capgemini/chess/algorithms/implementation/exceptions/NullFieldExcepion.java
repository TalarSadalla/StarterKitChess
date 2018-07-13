package com.capgemini.chess.algorithms.implementation.exceptions;

public class NullFieldExcepion extends InvalidMoveException {

	private static final long serialVersionUID = -519341059402582242L;
	
	public NullFieldExcepion() {
		super("No Piece on this field!");
	}
	
	

}

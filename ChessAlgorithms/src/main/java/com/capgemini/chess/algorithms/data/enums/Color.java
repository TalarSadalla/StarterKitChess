package com.capgemini.chess.algorithms.data.enums;

/**
 * Chess piece color
 * 
 * @author Michal Bejm
 *
 */
public enum Color {
	WHITE, BLACK;

	public static Color getOppositeColor(Color color) {
		if (color == Color.WHITE)
			return Color.BLACK;
		return Color.WHITE;
	}
}

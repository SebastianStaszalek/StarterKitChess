package com.capgemini.chess.algorithms.implementation.exceptions;

public class NoKingException extends InvalidMoveException {

	private static final long serialVersionUID = -5358675868207026938L;

	public NoKingException() {
		super("Oops! No King on the board!");
	}
	
	
}

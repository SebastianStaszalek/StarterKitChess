package com.capgemini.chess.algorithms.implementation.exceptions;

public class InvalidCoordinatesException extends InvalidMoveException {

	private static final long serialVersionUID = 3259554346460442134L;

	public InvalidCoordinatesException(String message) {
		super(message);
	}
}

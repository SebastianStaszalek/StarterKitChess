package com.capgemini.chess.algorithms.implementation.exceptions.moves;

import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class RookInvalidMoveException extends InvalidMoveException {

	private static final long serialVersionUID = 140081372592688991L;

	public RookInvalidMoveException(String message) {
		super(message);
	}

}

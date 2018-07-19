package com.capgemini.chess.algorithms.implementation.exceptions.moves;

import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class QueenInvalidMoveException extends InvalidMoveException {

	private static final long serialVersionUID = 8804573100664721629L;

	public QueenInvalidMoveException(String message) {
		super(message);
	}

}

package com.capgemini.chess.algorithms.implementation.exceptions.moves;

import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class KingInvalidMoveException extends InvalidMoveException {

	private static final long serialVersionUID = -2115220484734104570L;

	public KingInvalidMoveException(String message) {
		super(message);
	}

}

package com.capgemini.chess.algorithms.implementation.exceptions.moves;

import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class BishopInvalidMoveException extends InvalidMoveException {

	private static final long serialVersionUID = -2941275679182008649L;

		public BishopInvalidMoveException(String message) {
		super(message);
	}
}

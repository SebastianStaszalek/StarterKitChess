package com.capgemini.chess.algorithms.implementation.exceptions.moves;

import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class KnightInvalidMoveException extends InvalidMoveException{

	private static final long serialVersionUID = 4648164619394532706L;

	public KnightInvalidMoveException(String message) {
		super(message);
	}
}

package com.capgemini.chess.algorithms.implementation.exceptions.moves;

import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class PawnInvalidMoveException extends InvalidMoveException{

	private static final long serialVersionUID = -1540888042100567905L;

	public PawnInvalidMoveException(String message) {
		super(message);
	}
	
	

}

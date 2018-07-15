package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public interface PieceTypeMoveValidator {
	
	 void validateIfMoveIsValid(Coordinate from, Coordinate to) throws InvalidMoveException;

}

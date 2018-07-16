package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public interface PieceTypeMoveValidator {
	
	 void validateIfMoveIsValid(Coordinate from, Coordinate to) throws InvalidMoveException;
	 void validatePath(Board board, Coordinate from, Coordinate to) throws InvalidMoveException;
}

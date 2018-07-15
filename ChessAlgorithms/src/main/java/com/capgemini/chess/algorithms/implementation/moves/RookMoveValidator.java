package com.capgemini.chess.algorithms.implementation.moves;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.implementation.PieceTypeMoveValidator;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class RookMoveValidator implements PieceTypeMoveValidator {

	@Override
	public void validatePieceTypeMove(Coordinate from, Coordinate to) throws InvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		
		int xTo = to.getX();
		int yTo = to.getY();
		
		if(xFrom != xTo || yFrom != yTo)
			throw new InvalidMoveException();
		
	}

}

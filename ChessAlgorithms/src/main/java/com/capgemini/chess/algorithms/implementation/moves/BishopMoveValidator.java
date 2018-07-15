package com.capgemini.chess.algorithms.implementation.moves;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.implementation.PieceTypeMoveValidator;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class BishopMoveValidator implements PieceTypeMoveValidator {

	@Override
	public void validatePieceTypeMove(Coordinate from, Coordinate to) throws InvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		
		int xTo = to.getX();
		int yTo = to.getY();
		
		int xDiff = Math.abs(xFrom - xTo);
		int yDiff = Math.abs(yFrom - yTo);
		
		if (xDiff != yDiff) 
			throw new InvalidMoveException();
		
	}
	
}

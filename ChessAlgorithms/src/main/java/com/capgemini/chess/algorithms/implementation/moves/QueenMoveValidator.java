package com.capgemini.chess.algorithms.implementation.moves;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.PieceTypeMoveValidator;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.QueenInvalidMoveException;

public class QueenMoveValidator implements PieceTypeMoveValidator{

	@Override
	public void validateIfMoveIsValid(Coordinate from, Coordinate to) throws QueenInvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		
		int xTo = to.getX();
		int yTo = to.getY();
		
		int xDiff = Math.abs(xFrom - xTo);
		int yDiff = Math.abs(yFrom - yTo);
		
		if((xDiff != yDiff) || (xFrom != xTo) || (yFrom != yTo))
			throw new QueenInvalidMoveException("Invalid Queen move");
		
	}

	@Override
	public void validatePath(Board board, Coordinate from, Coordinate to) throws QueenInvalidMoveException {
		// TODO Auto-generated method stub
		
	}

}

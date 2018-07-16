package com.capgemini.chess.algorithms.implementation.moves;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.PieceTypeMoveValidator;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.RookInvalidMoveException;

public class RookMoveValidator implements PieceTypeMoveValidator {

	@Override
	public void validateIfMoveIsValid(Coordinate from, Coordinate to) throws RookInvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		
		int xTo = to.getX();
		int yTo = to.getY();
		
		if(xFrom != xTo || yFrom != yTo)
			throw new RookInvalidMoveException("Invalid Rook move");
		
	}

	@Override
	public void validatePath(Board board, Coordinate from, Coordinate to) throws RookInvalidMoveException {
		// TODO Auto-generated method stub
		
	}

}

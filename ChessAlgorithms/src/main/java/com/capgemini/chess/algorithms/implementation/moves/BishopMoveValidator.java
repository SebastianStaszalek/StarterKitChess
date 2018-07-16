package com.capgemini.chess.algorithms.implementation.moves;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.PieceTypeMoveValidator;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.BishopInvalidMoveException;

public class BishopMoveValidator implements PieceTypeMoveValidator {

	@Override
	public void validateIfMoveIsValid(Coordinate from, Coordinate to) throws BishopInvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		
		int xTo = to.getX();
		int yTo = to.getY();
		
		int xDiff = Math.abs(xFrom - xTo);
		int yDiff = Math.abs(yFrom - yTo);
		
		if (xDiff != yDiff) 
			throw new BishopInvalidMoveException("Invalid Bishop move");
		
	}

	@Override
	public void validatePath(Board board, Coordinate from, Coordinate to) throws BishopInvalidMoveException {
		
		
		int xFrom = from.getX();
		int yFrom = from.getY();
		
		int xTo = to.getX();
		int yTo = to.getY();
		
		int lengthOfPath = Math.abs(xTo - xFrom);
		
		int xDirection = 1;
		int yDirection = 1;
		if(xTo - xFrom < 0) {
			xDirection = -1;
		}
		if(yTo - yFrom < 0) {
			yDirection = -1;
		}
		
		if (lengthOfPath - 1 > 0) {
			for(int i = 1; i < lengthOfPath-1; i++) {
				if(board.getPieceAt(new Coordinate(1,1)) == null) {
					throw new BishopInvalidMoveException("The path is not clear for Bishop");
				}
			}
		}
		
		
		
		
	}
	
}

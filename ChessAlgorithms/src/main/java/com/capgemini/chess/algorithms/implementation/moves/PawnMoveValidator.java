package com.capgemini.chess.algorithms.implementation.moves;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.PieceTypeMoveValidator;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.PawnInvalidMoveException;

public class PawnMoveValidator implements PieceTypeMoveValidator {
	
	

	@Override
	public void validateIfMoveIsValid(Coordinate from, Coordinate to) {
		//method not implemented
		//all logic is contained in validatePath method
	}

	@Override
	public void validatePath(Board board, Coordinate from, Coordinate to) throws PawnInvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		
		int xTo = to.getX();
		int yTo = to.getY();
		
		Color pawnColor = board.getPieceAt(from).getColor();
		
		//check if Pawn is not moving backward
		if(pawnColor == Color.BLACK && yTo > yFrom) {
			throw new PawnInvalidMoveException("Pawn cannot move backward!");
		}
		if (pawnColor == Color.WHITE && yTo < yFrom) {
			throw new PawnInvalidMoveException("Pawn cannot move backward!");
		}
		
		//check possible moves	
		if ((pawnColor == Color.WHITE && yFrom == 1) || (pawnColor == Color.BLACK && yFrom == 6)) {
			checkFirstPawnMove(board, from, to);
		} else if (xFrom == xTo) {
			checkRegularPawnMove(from, to);
		} else {
			checkPawnCapture(board, from, to);
		}
	}


	private void checkFirstPawnMove(Board board, Coordinate from, Coordinate to) throws PawnInvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		
		int xTo = to.getX();
		int yTo = to.getY();
		
		int yDiff = Math.abs(yTo - yFrom);
		
		if((yDiff != 2 && yDiff != 1) && xFrom != xTo) {
			throw new PawnInvalidMoveException("Invalid Pawn move");
		}
		
		//check the path if pawn is moving 2 forward
		if(yDiff == 2) {
			checkForObstaclesOnThePath(board, xFrom, yFrom);
		}
	}


	private void checkForObstaclesOnThePath(Board board, int xFrom, int yFrom) throws PawnInvalidMoveException {
		Color pawnColor = board.getPieceAt(new Coordinate(xFrom, yFrom)).getColor();
		
		if(pawnColor == Color.BLACK && board.getPieceAt(new Coordinate(xFrom, yFrom-1)) != null) {
			throw new PawnInvalidMoveException("The path is not clear for Pawn");
		}
		if(pawnColor == Color.WHITE && board.getPieceAt(new Coordinate(xFrom, yFrom+1)) != null) {
			throw new PawnInvalidMoveException("The path is not clear for Pawn");
		}
		
	}

	private void checkRegularPawnMove(Coordinate from, Coordinate to) throws PawnInvalidMoveException {
		int yFrom = from.getY();
		int yTo = to.getY();
		
		int yDiff = Math.abs(yTo - yFrom);
		
		if(yDiff != 1) {
			throw new PawnInvalidMoveException("Pawn can move only one place forward!");
		}
	}
	
	//TODO: trzeba bedzie jeszcze sprawdzic kolor!
	private void checkPawnCapture(Board board, Coordinate from, Coordinate to) throws PawnInvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		
		int xTo = to.getX();
		int yTo = to.getY();
		
		int yDiff = Math.abs(yTo - yFrom);
		int xDiff = Math.abs(xTo - xFrom);
		
		if(board.getPieceAt(to) == null || xDiff != 1 || yDiff != 1 ) {
			throw new PawnInvalidMoveException("Invalid Pawn move");
		}
		
	}
}

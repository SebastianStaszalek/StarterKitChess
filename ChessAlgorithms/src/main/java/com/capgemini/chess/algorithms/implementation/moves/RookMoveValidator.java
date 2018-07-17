package com.capgemini.chess.algorithms.implementation.moves;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.PieceTypeMoveValidator;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.BishopInvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.RookInvalidMoveException;

public class RookMoveValidator implements PieceTypeMoveValidator {

	@Override
	public void validateIfMoveIsValid(Coordinate from, Coordinate to) throws RookInvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();

		int xTo = to.getX();
		int yTo = to.getY();

		if (!((xFrom == xTo) || (yFrom == yTo)))
			throw new RookInvalidMoveException("Invalid Rook move");

	}

	@Override
	public void validatePath(Board board, Coordinate from, Coordinate to) throws RookInvalidMoveException {

		int xFrom = from.getX();
		int yFrom = from.getY();

		int xTo = to.getX();
		int yTo = to.getY();

		int lengthOfPath = 0;
		int xDirection = 0;
		int yDirection = 0;

		// if Rook moves horizontally
		if (xTo - xFrom != 0 && yFrom == yTo) {
			lengthOfPath = Math.abs(xTo - xFrom);
			if (xTo - xFrom < 0) {
				xDirection = -1;
			} else
				xDirection = 1;
		// if Rook moves vertically
		} else {
			lengthOfPath = Math.abs(yTo - yFrom);
			if (yTo - yFrom < 0) {
				yDirection = -1;
			} else {
				yDirection = 1;
			}
		}
		

		if (lengthOfPath - 1 > 0) {

			int xToCheck = xFrom + (1 * xDirection);
			int yToCheck = yFrom + (1 * yDirection);

			for (int i = 0; i < lengthOfPath - 1; i++) {
				if (board.getPieceAt(new Coordinate(xToCheck, yToCheck)) != null) {
					throw new RookInvalidMoveException("The path is not clear for Rook");
				}
				xToCheck += 1 * xDirection;
				yToCheck += 1 * yDirection;
			}
		}
	}
}

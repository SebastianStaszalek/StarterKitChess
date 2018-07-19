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
		//checking direction
		int xDirection = 1;
		int yDirection = 1;
		if (xTo - xFrom < 0) {
			xDirection = -1;
		}
		if (yTo - yFrom < 0) {
			yDirection = -1;
		}
		//check the path
		if (lengthOfPath - 1 > 0) {

			int xToCheck = xFrom + (1 * xDirection);
			int yToCheck = yFrom + (1 * yDirection);

			for (int i = 0; i < lengthOfPath - 1; i++) {
				if (board.getPieceAt(new Coordinate(xToCheck, yToCheck)) != null) {
					throw new BishopInvalidMoveException("The path is not clear for Bishop");
				}
				xToCheck += 1 * xDirection;
				yToCheck += 1 * yDirection;
			}
		}

	}
}

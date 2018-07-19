package com.capgemini.chess.algorithms.implementation.moves;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.PieceTypeMoveValidator;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.KingInvalidMoveException;

public class KingMoveValidator implements PieceTypeMoveValidator {

	@Override
	public void validateIfMoveIsValid(Coordinate from, Coordinate to) throws KingInvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();

		int xTo = to.getX();
		int yTo = to.getY();

		int xDiff = Math.abs(xFrom - xTo);
		int yDiff = Math.abs(yFrom - yTo);

		if ((xDiff > 1) || (yDiff > 1))
			throw new KingInvalidMoveException("Invalid King move");
	}

	@Override
	public void validatePath(Board board, Coordinate from, Coordinate to) throws KingInvalidMoveException {
		// King can move only one place so we only need to check "to" coordinate
	}

}

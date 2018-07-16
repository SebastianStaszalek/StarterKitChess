package com.capgemini.chess.algorithms.implementation.moves;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.PieceTypeMoveValidator;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.PawnInvalidMoveException;

public class PawnMoveValidator implements PieceTypeMoveValidator {

	@Override
	public void validateIfMoveIsValid(Coordinate from, Coordinate to) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validatePath(Board board, Coordinate from, Coordinate to) throws PawnInvalidMoveException {
		// TODO Auto-generated method stub
	}

}

package com.capgemini.chess.algorithms.implementation.moves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.BoardManager;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.BishopInvalidMoveException;

public class BishopMoveValidatorTest {

	@Test
	public void testPerformMoveBishopLeft() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(6, 2));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(0, 0));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(6, 2), new Coordinate(3, 5));

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_BISHOP, move.getMovedPiece());
	}

	@Test
	public void testPerformUnclearPathForBishop() throws InvalidMoveException {
		// given
		Board board = new Board();

		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(5, 3));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 4));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(3, 2));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(7, 2));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(0, 1));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(5, 4), new Coordinate(1, 0));
		} catch (InvalidMoveException e) {
			exceptionThrown = e instanceof BishopInvalidMoveException;
		}
		// then
		assertTrue(exceptionThrown);
	}
}

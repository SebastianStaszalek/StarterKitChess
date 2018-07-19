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
import com.capgemini.chess.algorithms.implementation.exceptions.moves.QueenInvalidMoveException;

public class QueenMoveValidatorTest {

	@Test
	public void testPerformMoveQueenHorizontalyRight() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(5, 0));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(7, 2));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(0, 1));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(5, 0), new Coordinate(3, 0));

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_QUEEN, move.getMovedPiece());
	}

	@Test
	public void testPerformMoveQueenVerticalyUp() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(5, 0));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(7, 2));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(0, 1));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(5, 0), new Coordinate(5, 3));

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_QUEEN, move.getMovedPiece());
	}

	@Test
	public void testPerformMoveQueenVerticalyDown() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(5, 3));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(7, 2));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(0, 1));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(5, 3), new Coordinate(5, 1));

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_QUEEN, move.getMovedPiece());
	}

	@Test
	public void testPerformMoveQueenDiagonallyLeft() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(5, 3));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(7, 2));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(0, 1));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(5, 3), new Coordinate(3, 1));

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_QUEEN, move.getMovedPiece());
	}

	@Test
	public void testPerformUnclearPathForQueen() throws InvalidMoveException {
		// given
		Board board = new Board();

		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(5, 3));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(3, 1));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(7, 2));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(0, 1));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(5, 3), new Coordinate(2, 0));
		} catch (InvalidMoveException e) {
			exceptionThrown = e instanceof QueenInvalidMoveException;
		}
		// then
		assertTrue(exceptionThrown);
	}
	
	@Test
	public void testPerformMoveInvalidQueenDestination() {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(5, 2));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(5, 5));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(5, 2), new Coordinate(8, 5));
		} catch (InvalidMoveException e) {
			exceptionThrown = true;
		}
		// then
		assertTrue(exceptionThrown);
	}

}

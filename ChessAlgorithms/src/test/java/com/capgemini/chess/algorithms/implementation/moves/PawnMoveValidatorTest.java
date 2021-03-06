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
import com.capgemini.chess.algorithms.implementation.exceptions.moves.PawnInvalidMoveException;

public class PawnMoveValidatorTest {

	@Test
	public void testPerformInvalidPawnDestination() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(6, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 5));
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(5, 5));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(7, 6));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(3, 5));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(3, 4));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 6));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(4, 5));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(7, 6), new Coordinate(7, 0));
		} catch (InvalidMoveException e) {
			exceptionThrown = e instanceof PawnInvalidMoveException;
		}
		// then
		assertTrue(exceptionThrown);
	}

	@Test
	public void testPerformInvalidBlackPawnMoveBackward() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(3, 3));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(3, 3), new Coordinate(3, 4));
		} catch (InvalidMoveException e) {
			exceptionThrown = e instanceof PawnInvalidMoveException;
		}
		// then
		assertTrue(exceptionThrown);
	}

	@Test
	public void testPerformInvalidBlackPawnCaptureBackward() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(3, 7));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(2, 6));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(2, 6), new Coordinate(3, 7));
		} catch (InvalidMoveException e) {
			exceptionThrown = e instanceof PawnInvalidMoveException;
		}
		// then
		assertTrue(exceptionThrown);
	}

	@Test
	public void testPerformUnclearPathForBlackPawnMoving2Forward() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(3, 6));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(3, 5));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(3, 6), new Coordinate(3, 4));
		} catch (InvalidMoveException e) {
			exceptionThrown = e instanceof PawnInvalidMoveException;
		}
		// then
		assertTrue(exceptionThrown);
	}

	@Test
	public void testPerformUnclearDestinationForBlackPawnMoving2Forward() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(3, 6));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(3, 4));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(3, 6), new Coordinate(3, 4));
		} catch (InvalidMoveException e) {
			exceptionThrown = e instanceof PawnInvalidMoveException;
		}
		// then
		assertTrue(exceptionThrown);
	}

	@Test
	public void testPerformUnclearPathForWhitePawnMoving2Forward() throws InvalidMoveException {
		// given
		Board board = new Board();

		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(2, 2));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(2, 1));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(2, 1), new Coordinate(2, 3));
		} catch (InvalidMoveException e) {
			exceptionThrown = e instanceof PawnInvalidMoveException;
		}
		// then
		assertTrue(exceptionThrown);
	}

	@Test
	public void testPerformUnclearDestinationForWhitePawnMoving2Forward() throws InvalidMoveException {
		// given
		Board board = new Board();

		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(2, 3));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(2, 1));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(2, 1), new Coordinate(2, 3));
		} catch (InvalidMoveException e) {
			exceptionThrown = e instanceof PawnInvalidMoveException;
		}
		// then
		assertTrue(exceptionThrown);
	}

	@Test
	public void testPerformPawnCapture() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 5));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(5, 6));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(5, 4));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(4, 5), new Coordinate(5, 4));

		// then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(Piece.BLACK_PAWN, move.getMovedPiece());
	}

	@Test
	public void testPerformPawnMoveFromNonStartPosition() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 5));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(5, 6));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(4, 5), new Coordinate(4, 4));

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.BLACK_PAWN, move.getMovedPiece());
	}

	@Test
	public void testPerformPawnCaptureFromStartPosition() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 5));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(5, 6));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(6, 5));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(5, 6), new Coordinate(6, 5));

		// then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(Piece.BLACK_PAWN, move.getMovedPiece());
	}
	
	private Move createDummyMove(Board board) {

		Move move = new Move();

		if (board.getMoveHistory().size() % 2 == 0) {
			board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(0, 0));
			move.setMovedPiece(Piece.WHITE_ROOK);
		} else {
			board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 0));
			move.setMovedPiece(Piece.BLACK_ROOK);
		}
		move.setFrom(new Coordinate(0, 0));
		move.setTo(new Coordinate(0, 0));
		move.setType(MoveType.ATTACK);
		board.setPieceAt(null, new Coordinate(0, 0));
		return move;
	}
}

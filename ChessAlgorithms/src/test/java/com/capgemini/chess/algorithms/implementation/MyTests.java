package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.BishopInvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.PawnInvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.moves.QueenInvalidMoveException;

public class MyTests {
	
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
	public void testPerformMoveRookHorizontallyLeft() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(5, 6));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(5, 4));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(5, 6), new Coordinate(2, 6));

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.BLACK_ROOK, move.getMovedPiece());
	}
	
	@Test
	public void testPerformCaptureRookHorizontallyLeft() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(3, 7));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(2, 7));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(3, 7), new Coordinate(2, 7));

		// then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(Piece.BLACK_ROOK, move.getMovedPiece());
	}

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

	@Test
	public void testPerformMoveKnightOverPieces() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(6, 2));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(5, 3));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(5, 4));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(5, 5));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(0, 0));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(5, 3), new Coordinate(4, 5));

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_KNIGHT, move.getMovedPiece());
	}

	@Test
	public void testPerformKnightCaptureOverPieces() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(6, 2));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(5, 3));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(5, 4));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 5));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(5, 5));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(0, 0));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(5, 3), new Coordinate(4, 5));

		// then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(Piece.WHITE_KNIGHT, move.getMovedPiece());
	}

	@Test
	public void testPerformMoveInvalidYDestination() {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(2, 4));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(5, 5));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(2, 4), new Coordinate(2, -1));
		} catch (InvalidMoveException e) {
			exceptionThrown = true;
		}
		// then
		assertTrue(exceptionThrown);
	}

	@Test
	public void testPerformMoveInvalidDestination() {
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

	@Test
	public void testPerformWhiteQueenPromotion() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(5, 6));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(6, 6));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(5, 4));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 7));

		// when
		BoardManager boardManager = new BoardManager(board);
		Move move = boardManager.performMove(new Coordinate(6, 6), new Coordinate(6, 7));

		// then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(Piece.WHITE_QUEEN, boardManager.getBoard().getPieceAt(new Coordinate(6, 7)));
	}
	
	@Test
	public void testUpdateBoardCheckMate() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 7));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(2, 7));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(3, 7));
		board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(4, 7));
		board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(5, 7));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(7, 7));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(0, 6));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(1, 6));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(2, 6));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(5, 6));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(6, 6));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(7, 6));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(5, 5));
		board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(5, 4));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(1, 2));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(0, 1));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(1, 1));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(2, 1));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(6, 1));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(6, 0));

		// when
		BoardManager boardManager = new BoardManager(board);
		BoardState boardState = boardManager.updateBoardState();

		// then
		assertEquals(BoardState.CHECK_MATE, boardState);
	}

	@Test
	public void testUpdateBoardStaleMateAdvancedSituation() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(6, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 5));
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(5, 5));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(7, 6));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(3, 5));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(3, 4));

		// when
		BoardManager boardManager = new BoardManager(board);
		BoardState boardState = boardManager.updateBoardState();

		// then
		assertEquals(BoardState.STALE_MATE, boardState);
	}

	@Test
	public void testUpdateBoardRegularAdvancedSituation() throws InvalidMoveException {
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
		BoardState boardState = boardManager.updateBoardState();

		// then
		assertEquals(BoardState.REGULAR, boardState);
	}

	@Test
	public void testPerformInvalidMoveKingWouldBeInCheck() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 7));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(7, 7));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(0, 6));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(1, 6));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(3, 6));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(6, 6));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(7, 6));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(1, 5));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(2, 5));
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 5));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(7, 5));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(3, 4));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(3, 3));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(1, 2));
		board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(2, 2));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(0, 1));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(2, 1));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(6, 1));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(7, 1));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(5, 0));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(6, 0));

		// when
		BoardManager boardManager = new BoardManager(board);

		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(7, 7), new Coordinate(6, 7));
		} catch (KingInCheckException e) {
			exceptionThrown = true;
		}
		// then
		assertTrue(exceptionThrown);
	}

	@Test
	public void testPerformMoveInvalidMoveOrderWhenGivenCoupleOfMoves() {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(0, 7));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(0, 0));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(2, 2));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(5, 5));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(6, 5));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(7, 2));

		// when
		BoardManager boardManager = new BoardManager(board);
		boolean exceptionThrown = false;
		try {
			boardManager.performMove(new Coordinate(0, 0), new Coordinate(1, 0));
			boardManager.performMove(new Coordinate(7, 2), new Coordinate(6, 0));
			boardManager.performMove(new Coordinate(2, 2), new Coordinate(2, 1));
		} catch (InvalidMoveException e) {
			exceptionThrown = true;
		}
		// then
		assertTrue(exceptionThrown);
	}

	@Test
	public void testPerformCoupleOfMovesInARowWithBoardStateRegular() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(0, 0));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(1, 5));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(7, 5));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(3, 1));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(5, 3));

		// when
		BoardManager boardManager = new BoardManager(board);
		boardManager.performMove(new Coordinate(4, 0), new Coordinate(4, 1));
		boardManager.performMove(new Coordinate(1, 5), new Coordinate(4, 5));
		boardManager.performMove(new Coordinate(3, 1), new Coordinate(4, 2));

		BoardState boardState = boardManager.updateBoardState();

		// then
		assertEquals(BoardState.REGULAR, boardState);
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

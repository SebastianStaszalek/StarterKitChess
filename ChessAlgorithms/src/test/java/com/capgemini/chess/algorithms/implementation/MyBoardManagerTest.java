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

public class MyBoardManagerTest {
	
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

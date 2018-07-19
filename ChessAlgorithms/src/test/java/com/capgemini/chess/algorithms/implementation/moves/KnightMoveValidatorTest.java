package com.capgemini.chess.algorithms.implementation.moves;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.BoardManager;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class KnightMoveValidatorTest {

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

}

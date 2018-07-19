package com.capgemini.chess.algorithms.implementation;

import java.util.Arrays;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidCoordinatesException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;

/**
 * Class for managing of basic operations on the Chess Board.
 *
 * @author Michal Bejm
 *
 */
public class BoardManager {

	private Board board = new Board();

	public BoardManager() {
		initBoard();
	}

	public BoardManager(List<Move> moves) {
		initBoard();
		for (Move move : moves) {
			addMove(move);
		}
	}

	public BoardManager(Board board) {
		this.board = board;
	}

	/**
	 * Getter for generated board
	 *
	 * @return board
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * Performs move of the chess piece on the chess board from one field to
	 * another.
	 *
	 * @param from
	 *            coordinates of 'from' field
	 * @param to
	 *            coordinates of 'to' field
	 * @return move object which includes moved piece and move type
	 * @throws InvalidMoveException
	 *             in case move is not valid
	 */
	public Move performMove(Coordinate from, Coordinate to) throws InvalidMoveException {

		Move move = validateMove(from, to);

		addMove(move);

		return move;
	}

	/**
	 * Calculates state of the chess board.
	 *
	 * @return state of the chess board
	 */
	public BoardState updateBoardState() {

		Color nextMoveColor = calculateNextMoveColor();

		boolean isKingInCheck = isKingInCheck(nextMoveColor);
		boolean isAnyMoveValid = isAnyMoveValid(nextMoveColor);

		BoardState boardState;
		if (isKingInCheck) {
			if (isAnyMoveValid) {
				boardState = BoardState.CHECK;
			} else {
				boardState = BoardState.CHECK_MATE;
			}
		} else {
			if (isAnyMoveValid) {
				boardState = BoardState.REGULAR;
			} else {
				boardState = BoardState.STALE_MATE;
			}
		}
		this.board.setState(boardState);
		return boardState;
	}

	/**
	 * Checks threefold repetition rule (one of the conditions to end the chess
	 * game with a draw).
	 *
	 * @return true if current state repeated at list two times, false otherwise
	 */
	public boolean checkThreefoldRepetitionRule() {

		// there is no need to check moves that where before last capture/en
		// passant/castling
		int lastNonAttackMoveIndex = findLastNonAttackMoveIndex();
		List<Move> omittedMoves = this.board.getMoveHistory().subList(0, lastNonAttackMoveIndex);
		BoardManager simulatedBoardManager = new BoardManager(omittedMoves);

		int counter = 0;
		for (int i = lastNonAttackMoveIndex; i < this.board.getMoveHistory().size(); i++) {
			Move moveToAdd = this.board.getMoveHistory().get(i);
			simulatedBoardManager.addMove(moveToAdd);
			boolean areBoardsEqual = Arrays.deepEquals(this.board.getPieces(),
					simulatedBoardManager.getBoard().getPieces());
			if (areBoardsEqual) {
				counter++;
			}
		}

		return counter >= 2;
	}

	/**
	 * Checks 50-move rule (one of the conditions to end the chess game with a
	 * draw).
	 *
	 * @return true if no pawn was moved or not capture was performed during
	 *         last 50 moves, false otherwise
	 */
	public boolean checkFiftyMoveRule() {

		// for this purpose a "move" consists of a player completing his turn
		// followed by his opponent completing his turn
		if (this.board.getMoveHistory().size() < 100) {
			return false;
		}

		for (int i = this.board.getMoveHistory().size() - 1; i >= this.board.getMoveHistory().size() - 100; i--) {
			Move currentMove = this.board.getMoveHistory().get(i);
			PieceType currentPieceType = currentMove.getMovedPiece().getType();
			if (currentMove.getType() != MoveType.ATTACK || currentPieceType == PieceType.PAWN) {
				return false;
			}
		}

		return true;
	}

	// PRIVATE

	private void initBoard() {

		this.board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 7));
		this.board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(1, 7));
		this.board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(2, 7));
		this.board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(3, 7));
		this.board.setPieceAt(Piece.BLACK_KING, new Coordinate(4, 7));
		this.board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(5, 7));
		this.board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(6, 7));
		this.board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(7, 7));

		for (int x = 0; x < Board.SIZE; x++) {
			this.board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(x, 6));
		}

		this.board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(0, 0));
		this.board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 0));
		this.board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(2, 0));
		this.board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
		this.board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		this.board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		this.board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		this.board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE; x++) {
			this.board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}
	}

	private void addMove(Move move) {

		addRegularMove(move);

		if (move.getType() == MoveType.CASTLING) {
			addCastling(move);
		} else if (move.getType() == MoveType.EN_PASSANT) {
			addEnPassant(move);
		}

		this.board.getMoveHistory().add(move);
	}

	private void addRegularMove(Move move) {
		Piece movedPiece = this.board.getPieceAt(move.getFrom());
		this.board.setPieceAt(null, move.getFrom());
		this.board.setPieceAt(movedPiece, move.getTo());

		performPromotion(move, movedPiece);
	}

	private void performPromotion(Move move, Piece movedPiece) {
		if (movedPiece == Piece.WHITE_PAWN && move.getTo().getY() == (Board.SIZE - 1)) {
			this.board.setPieceAt(Piece.WHITE_QUEEN, move.getTo());
		}
		if (movedPiece == Piece.BLACK_PAWN && move.getTo().getY() == 0) {
			this.board.setPieceAt(Piece.BLACK_QUEEN, move.getTo());
		}
	}

	private void addCastling(Move move) {
		if (move.getFrom().getX() > move.getTo().getX()) {
			Piece rook = this.board.getPieceAt(new Coordinate(0, move.getFrom().getY()));
			this.board.setPieceAt(null, new Coordinate(0, move.getFrom().getY()));
			this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() + 1, move.getTo().getY()));
		} else {
			Piece rook = this.board.getPieceAt(new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
			this.board.setPieceAt(null, new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
			this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() - 1, move.getTo().getY()));
		}
	}

	private void addEnPassant(Move move) {
		Move lastMove = this.board.getMoveHistory().get(this.board.getMoveHistory().size() - 1);
		this.board.setPieceAt(null, lastMove.getTo());
	}

	private Move validateMove(Coordinate from, Coordinate to) throws InvalidMoveException, KingInCheckException {
		
		// FROM
		validateIfCoordinatesAreOnTheBoard(from, to);
		validateIfSpotIsNotEmpty(from);
		validateIfRightPlayerIsMoving(this.board, from);

		// FROM - TO
		validateIfMoveIsNotPerformedOnTheSameSpot(from, to);
		checkIfOnTheTargetSpotIsNotMyPiece(this.board, from, to);

		// MOVE TYPE VALIDATION
		PieceTypeMoveValidator validator = PieceTypeFactory.getPieceTypeValidator(this.board, from);
		validator.validateIfMoveIsValid(from, to);
		validator.validatePath(this.board, from, to);

		// KING IN CHECK VALIDATION
		Piece fromPiece = board.getPieceAt(from);
		Piece toPiece = board.getPieceAt(to);

		Color playerColor = this.board.getPieceAt(from).getColor();
		boolean myKingInCheck = false;

		this.board.setPieceAt(fromPiece, to);
		this.board.setPieceAt(null, from);

		myKingInCheck = isKingInCheck(playerColor);
		if (myKingInCheck) {
			this.board.setPieceAt(fromPiece, from);
			this.board.setPieceAt(toPiece, to);
			throw new KingInCheckException();
		} else {
			this.board.setPieceAt(fromPiece, from);
			this.board.setPieceAt(toPiece, to);
		}

		// TO
		Move performedMove = new Move();
		
		setMoveType(performedMove, from, to);
		performedMove.setFrom(from);
		performedMove.setTo(to);
		performedMove.setMovedPiece(this.board.getPieceAt(from));
		return performedMove;
	}

	private Coordinate getKingPosition(Board board, Color color) {
		Coordinate kingCoordinate = null;

		for (int x = 0; x < Board.SIZE; x++) {
			for (int y = 0; y < Board.SIZE; y++) {

				Coordinate coordinateToCheck = new Coordinate(x, y);
				Piece testedPiece = board.getPieceAt(coordinateToCheck);

				if (testedPiece != null) {
					if (PieceType.KING == testedPiece.getType() && color == testedPiece.getColor()) {
						kingCoordinate = coordinateToCheck;
					}
				}
			}
		}
		return kingCoordinate;
	}

	private void validateIfRightPlayerIsMoving(Board board, Coordinate from) throws InvalidMoveException {
		Color playerColor = calculateNextMoveColor();

		if (playerColor != board.getPieceAt(from).getColor()) {
			throw new InvalidMoveException("Wrong move order");
		}
	}

	private void setMoveType(Move performedMove, Coordinate from, Coordinate to) {
		Piece targetPiece = board.getPieceAt(to);
		if (targetPiece == null) {
			performedMove.setType(MoveType.ATTACK);
		} else {
			performedMove.setType(MoveType.CAPTURE);
		}
	}

	private void checkIfOnTheTargetSpotIsNotMyPiece(Board board, Coordinate from, Coordinate to)
			throws InvalidMoveException {

		Piece targetPiece = board.getPieceAt(to);
		if (targetPiece == null) {
			return;
		}

		Color playerColor = board.getPieceAt(from).getColor();
		Color targetColor = board.getPieceAt(to).getColor();

		if (playerColor == targetColor) {
			throw new InvalidMoveException("You cannot capture your own Piece!");
		}
	}

	private void validateIfCoordinatesAreOnTheBoard(Coordinate from, Coordinate to) throws InvalidMoveException {
		checkCoordinates(from, to);
		
		final int COORDINATE_BOTTOM = 0;
		final int COORDINATE_TOP = 7;

		int xFrom = from.getX();
		int yFrom = from.getY();

		int xTo = to.getX();
		int yTo = to.getY();

		if (!isBetween(COORDINATE_BOTTOM, xFrom, COORDINATE_TOP)
				|| !isBetween(COORDINATE_BOTTOM, yFrom, COORDINATE_TOP)) {
			throw new InvalidMoveException("Starting place is not on the board!");
		}
		if (!isBetween(COORDINATE_BOTTOM, xTo, COORDINATE_TOP) || !isBetween(COORDINATE_BOTTOM, yTo, COORDINATE_TOP)) {
			throw new InvalidMoveException("Final place is not on the board!");
		}
	}

	private void checkCoordinates(Coordinate from, Coordinate to) throws InvalidCoordinatesException {
		if (from == null || to == null) {
			throw new InvalidCoordinatesException("Wrong coordinates");
		}
	}

	private boolean isBetween(int bottom, int value, int top) {
		return bottom <= value && value <= top;
	}

	private void validateIfSpotIsNotEmpty(Coordinate from) throws InvalidMoveException {
		if (this.board.getPieceAt(from) == null)
			throw new InvalidMoveException("Place is empty!");
	}

	private void validateIfMoveIsNotPerformedOnTheSameSpot(Coordinate from, Coordinate to) throws InvalidMoveException {
		if (from.equals(to))
			throw new InvalidMoveException("Any move was performed!");
	}

	private boolean isKingInCheck(Color kingColor) {

		Coordinate kingPosition = getKingPosition(this.board, kingColor);

		for (int x = 0; x < Board.SIZE; x++) {
			for (int y = 0; y < Board.SIZE; y++) {

				Coordinate positionToCheck = new Coordinate(x, y);
				Piece pieceToCheck = this.board.getPieceAt(positionToCheck);

				if (pieceToCheck != null && !kingPosition.equals(positionToCheck)
						&& pieceToCheck.getColor() != kingColor) {

					PieceTypeMoveValidator validator = PieceTypeFactory.getPieceTypeValidator(this.board,
							positionToCheck);
					try {
						validator.validateIfMoveIsValid(positionToCheck, kingPosition);
						validator.validatePath(this.board, positionToCheck, kingPosition);
						return true;
					} catch (InvalidMoveException e) {
						// ........................
					}
				}
			}
		}
		return false;
	}

	private boolean isAnyMoveValid(Color nextMoveColor) {

		for (int x = 0; x < Board.SIZE; x++) {
			for (int y = 0; y < Board.SIZE; y++) {

				Coordinate positionFrom = new Coordinate(x, y);
				Piece pieceToCheck = this.board.getPieceAt(positionFrom);

				if (pieceToCheck != null && pieceToCheck.getColor() == nextMoveColor) {

					for (int i = 0; i < Board.SIZE; i++) {
						for (int j = 0; j < Board.SIZE; j++) {

							Coordinate positionToCheck = new Coordinate(i, j);

							if (!positionFrom.equals(positionToCheck)) {
								
								PieceTypeMoveValidator validator = PieceTypeFactory.getPieceTypeValidator(this.board,
										positionFrom);
								try {
									checkIfOnTheTargetSpotIsNotMyPiece(this.board, positionFrom, positionToCheck);
									validator.validateIfMoveIsValid(positionFrom, positionToCheck);
									validator.validatePath(this.board, positionFrom, positionToCheck);

									Piece fromPiece = this.board.getPieceAt(positionFrom);
									Piece toPiece = this.board.getPieceAt(positionToCheck);

									boolean kingInCheck = false;

									this.board.setPieceAt(fromPiece, positionToCheck);
									this.board.setPieceAt(null, positionFrom);

									kingInCheck = isKingInCheck(nextMoveColor);
									if (!kingInCheck) {
										this.board.setPieceAt(fromPiece, positionFrom);
										this.board.setPieceAt(toPiece, positionToCheck);
										return true;
									} else {
										this.board.setPieceAt(fromPiece, positionFrom);
										this.board.setPieceAt(toPiece, positionToCheck);
									}

								} catch (InvalidMoveException e) {
									// ........................
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private Color calculateNextMoveColor() {
		if (this.board.getMoveHistory().size() % 2 == 0) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}

	private int findLastNonAttackMoveIndex() {
		int counter = 0;
		int lastNonAttackMoveIndex = 0;

		for (Move move : this.board.getMoveHistory()) {
			if (move.getType() != MoveType.ATTACK) {
				lastNonAttackMoveIndex = counter;
			}
			counter++;
		}

		return lastNonAttackMoveIndex;
	}

}

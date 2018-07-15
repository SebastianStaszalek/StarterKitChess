package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.moves.BishopMoveValidator;
import com.capgemini.chess.algorithms.implementation.moves.KingMoveValidator;
import com.capgemini.chess.algorithms.implementation.moves.KnightMoveValidator;
import com.capgemini.chess.algorithms.implementation.moves.PawnMoveValidator;
import com.capgemini.chess.algorithms.implementation.moves.QueenMoveValidator;
import com.capgemini.chess.algorithms.implementation.moves.RookMoveValidator;

public class PieceTypeFactory {

	public PieceTypeMoveValidator getPieceTypeValidator(Board board, Coordinate from) {
		
		PieceType pieceType = board.getPieceAt(from).getType();
		
		PieceTypeMoveValidator result = null;
		
		//TODO: na Enumach == jest wlasciwe??
		if (pieceType == PieceType.PAWN) {
			result = new PawnMoveValidator();
		} else if (pieceType == PieceType.ROOK) {
			result = new RookMoveValidator();
		} else if (pieceType == PieceType.KNIGHT) {
			result = new KnightMoveValidator();
		} else if (pieceType == PieceType.BISHOP) {
			result = new BishopMoveValidator();
		} else if (pieceType == PieceType.QUEEN) {
			result = new QueenMoveValidator();
		} else if (pieceType == PieceType.KING) {
			result = new KingMoveValidator();
		} 
		
		return result;
	}
}

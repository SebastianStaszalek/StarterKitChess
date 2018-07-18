package com.capgemini.chess.algorithms.data.enums;

/**
 * Types of moves
 * 
 * @author Michal Bejm
 *
 */
public enum MoveType {
	ATTACK,
	CAPTURE, //zbicie
	CASTLING, //roszada
	EN_PASSANT; //bicie w przelocie
}

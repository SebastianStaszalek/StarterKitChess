package com.capgemini.chess.algorithms.data.enums;

/**
 * Board state
 * 
 * @author Michal Bejm
 *
 */
public enum BoardState {
	REGULAR,
	CHECK, //dac szacha
	CHECK_MATE, // szach-mat
	STALE_MATE; //dac pata / PAT
}

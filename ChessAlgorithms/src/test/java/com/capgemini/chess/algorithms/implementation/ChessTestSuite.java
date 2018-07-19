package com.capgemini.chess.algorithms.implementation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.capgemini.chess.algorithms.implementation.moves.BishopMoveValidatorTest;
import com.capgemini.chess.algorithms.implementation.moves.KnightMoveValidatorTest;
import com.capgemini.chess.algorithms.implementation.moves.PawnMoveValidatorTest;
import com.capgemini.chess.algorithms.implementation.moves.QueenMoveValidatorTest;
import com.capgemini.chess.algorithms.implementation.moves.RookMoveValidatorTest;

/**
 * Test suite containing all tests
 *
 * @author Michal Bejm
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ BoardManagerTest.class, MyBoardManagerTest.class, BishopMoveValidatorTest.class,
		KnightMoveValidatorTest.class, PawnMoveValidatorTest.class, QueenMoveValidatorTest.class,
		RookMoveValidatorTest.class })
public class ChessTestSuite {

}

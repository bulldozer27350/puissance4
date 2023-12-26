package com.moe.puissance4back.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.moe.puissance4back.core.model.Coordinate;
import com.moe.puissance4back.core.model.Board;
import com.moe.puissance4back.core.model.CoordinatesSeries;

public class WinCheckerTest {

	private final CoordinatesManagement management = new CoordinatesManagement();
	private final Board board = new Board();
	private final Board board2 = new Board();
	
	@Before
	public void before() {
		InternalJoueur j1 = new InternalJoueur(); 
		InternalJoueur j2 = new InternalJoueur();
		// Le tableau de jeu ressemble à ça ($ pour joueur 1 et # pour joueur 2)
		
		// 3. ####
		// 2. $$$$
		// 1. ####
		// 0. $$$$
		//    ....
		//    0123
		
		// Y
		// 3 | (x=0/y=3,j2) | (x=1/y=3,j2) | (x=2/y=3,j2) | (x=3/y=3,j2)
		// 2 | (x=0/y=2,j1) | (x=1/y=2,j2) | (x=2/y=2,j1) | (x=3/y=2,j1)
		// 1 | (x=0/y=1,j2) | (x=1/y=1,j2) | (x=2/y=1,j1) | (x=3/y=1,j2)
		// 0 | (x=0/y=0,j1) | (x=1/y=0,j1) | (x=2/y=0,j1) | (x=3/y=0,j2)
		//   °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
		//     ------0------|------1-------|------2-------|------3------ X
		
		
		// Y
		// 3 | J2 | J2 | J2 | J2
		// 2 | J1 | J2 | J1 | J1
		// 1 | J2 | J2 | J1 | J2
		// 0 | J1 | J1 | J1 | J2
		//   °°°°°°°°°°°°°°°°°°°°
		//      0    1    2    3 X 
		
		createCoordinate(board2, 0, 0, j1);
		createCoordinate(board2, 1, 0, j1);
		createCoordinate(board2, 2, 0, j1);
		createCoordinate(board2, 3, 0, j1);
		
		createCoordinate(board2, 0, 1, j2);
		createCoordinate(board2, 1, 1, j2);
		createCoordinate(board2, 2, 1, j1);
		createCoordinate(board2, 3, 1, j2);
		
		createCoordinate(board2, 0, 2, j1);
		createCoordinate(board2, 1, 2, j2);
		createCoordinate(board2, 2, 2, j1);
		createCoordinate(board2, 3, 2, j1);
		
		createCoordinate(board2, 0, 3, j2);
		createCoordinate(board2, 1, 3, j2);
		createCoordinate(board2, 2, 3, j2);
		createCoordinate(board2, 3, 3, j2);
	}
	
	private void createCoordinate(Board board, int x, int y, InternalJoueur joueur) {
		Coordinate coord = new Coordinate();
		coord.setX(x);
		coord.setY(y);
		coord.setOwner(joueur);
		board.getCoordinates().add(coord);
	}
	
	@Test
	public void testWinner() {
		CoordinatesSeries series = management.computeCoordinates(board2, board2.getCoordinate(3, 3));
		WinChecker checker = new WinCheckerImpl();
		Assert.assertTrue(checker.checkForWin(series, board2));
		
		series = management.computeCoordinates(board2, board2.getCoordinate(1, 3));
		checker = new WinCheckerImpl();
		Assert.assertTrue(checker.checkForWin(series, board2));
		
		series = management.computeCoordinates(board2, board2.getCoordinate(1, 2));
		checker = new WinCheckerImpl();
		Assert.assertFalse(checker.checkForWin(series, board2));
	}


	
}

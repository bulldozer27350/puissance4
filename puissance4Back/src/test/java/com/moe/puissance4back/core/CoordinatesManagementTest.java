package com.moe.puissance4back.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.moe.puissance4back.core.model.Coordinate;
import com.moe.puissance4back.core.model.Board;
import com.moe.puissance4back.core.model.CoordinatesSeries;

public class CoordinatesManagementTest {

	private final CoordinatesManagement management = new CoordinatesManagement();
	private final Board board = new Board();
	
	@Before
	public void before() {
		// Le tableau de jeu ressemble à ça ($ pour joueur 1 et # pour joueur 2)
		
		// 3. ####
		// 2. $$$$
		// 1. ####
		// 0. $$$$
		//    ....
		//    0123
		
		// Y
		// 3 | (x=0/y=3,j2) | (x=1/y=3,j2) | (x=2/y=3,j2) | (x=3/y=3,j2)
		// 2 | (x=0/y=2,j1) | (x=1/y=2,j1) | (x=2/y=2,j1) | (x=3/y=2,j1)
		// 1 | (x=0/y=1,j2) | (x=1/y=1,j2) | (x=2/y=1,j2) | (x=3/y=1,j2)
		// 0 | (x=0/y=0,j1) | (x=1/y=0,j1) | (x=2/y=0,j1) | (x=3/y=0,j1)
		//   °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
		//     ------0------|------1-------|------2-------|------3------ X 
		
		
		InternalJoueur j1 = new InternalJoueur(); 
		InternalJoueur j2 = new InternalJoueur();
		
		// Ligne après ligne 
		createCoordinate(board, 0, 0, j1);
		createCoordinate(board, 1, 0, j1);
		createCoordinate(board, 2, 0, j1);
		createCoordinate(board, 3, 0, j1);
		
		createCoordinate(board, 0, 1, j2);
		createCoordinate(board, 1, 1, j2);
		createCoordinate(board, 2, 1, j2);
		createCoordinate(board, 3, 1, j2);
		
		createCoordinate(board, 0, 2, j1);
		createCoordinate(board, 1, 2, j1);
		createCoordinate(board, 2, 2, j1);
		createCoordinate(board, 3, 2, j1);
		
		createCoordinate(board, 0, 3, j2);
		createCoordinate(board, 1, 3, j2);
		createCoordinate(board, 2, 3, j2);
		createCoordinate(board, 3, 3, j2);
	}
	
	private void createCoordinate(Board board, int x, int y, InternalJoueur joueur) {
		Coordinate coord = new Coordinate();
		coord.setX(x);
		coord.setY(y);
		coord.setOwner(joueur);
		board.getCoordinates().add(coord);
	}
	
	@Test
	public void testAntiSlashCoordinates() {
		CoordinatesSeries series = new CoordinatesSeries();
		
		management.computeAntiSlashCoordinates(series, board, board.getCoordinate(2, 2));
		Assert.assertTrue(series.getAntiSlashCoordinates().contains(board.getCoordinate(2, 2)));
		Assert.assertEquals(1, series.getAntiSlashCoordinates().size());
		
		management.computeAntiSlashCoordinates(series, board, board.getCoordinate(1, 3));
		Assert.assertTrue(series.getAntiSlashCoordinates().contains(board.getCoordinate(3, 1)));
		Assert.assertTrue(series.getAntiSlashCoordinates().contains(board.getCoordinate(3, 1)));
		Assert.assertEquals(2, series.getAntiSlashCoordinates().size());
		
		management.computeAntiSlashCoordinates(series, board, board.getCoordinate(3, 3));
		Assert.assertTrue(series.getAntiSlashCoordinates().contains(board.getCoordinate(3, 3)));
		Assert.assertEquals(1, series.getAntiSlashCoordinates().size());
	}
	
	@Test
	public void testSlashCoordinates() {
		CoordinatesSeries series = new CoordinatesSeries();
		
		management.computeSlashCoordinates(series, board, board.getCoordinate(2, 2));
		Assert.assertTrue(series.getSlashCoordinates().contains(board.getCoordinate(0, 0)));
		Assert.assertTrue(series.getSlashCoordinates().contains(board.getCoordinate(2, 2)));
		Assert.assertEquals(2, series.getSlashCoordinates().size());
		
		management.computeSlashCoordinates(series, board, board.getCoordinate(3, 3));
		Assert.assertTrue(series.getSlashCoordinates().contains(board.getCoordinate(1, 1)));
		Assert.assertTrue(series.getSlashCoordinates().contains(board.getCoordinate(3, 3)));
		Assert.assertEquals(2, series.getSlashCoordinates().size());
	}
	
	@Test
	public void testVerticalCoordinates() {
		CoordinatesSeries series = new CoordinatesSeries();
		
		management.computeVerticalCoordinates(series, board, board.getCoordinate(0, 0));
		Assert.assertTrue(series.getVerticalCoordinates().contains(board.getCoordinate(0, 0)));
		Assert.assertTrue(series.getVerticalCoordinates().contains(board.getCoordinate(0, 2)));
		Assert.assertEquals(2, series.getVerticalCoordinates().size());
	}

	@Test
	public void testHorizontalCoordinates() {
		CoordinatesSeries series = new CoordinatesSeries();
		
		management.computeHorizontalCoordinates(series, board, board.getCoordinate(0, 0));
		Assert.assertTrue(series.getHorizontalCoordinates().contains(board.getCoordinate(0, 0)));
		Assert.assertTrue(series.getHorizontalCoordinates().contains(board.getCoordinate(1, 0)));
		Assert.assertTrue(series.getHorizontalCoordinates().contains(board.getCoordinate(2, 0)));
		Assert.assertTrue(series.getHorizontalCoordinates().contains(board.getCoordinate(3, 0)));
		Assert.assertEquals(4, series.getHorizontalCoordinates().size());
	}


	
}

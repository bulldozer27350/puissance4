package com.moe.puissance4back.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.moe.puissance4back.core.model.Coordinate;
import com.moe.puissance4back.core.model.Board;
import com.moe.puissance4back.core.model.CoordinatesSeries;

public class CoordinatesManagement {

	public CoordinatesSeries computeCoordinates(Board board, Coordinate lastPlayedPosition) {
		CoordinatesSeries serie = new CoordinatesSeries();
		this.computeVerticalCoordinates(serie, board, lastPlayedPosition);
		this.computeHorizontalCoordinates(serie, board, lastPlayedPosition);
		this.computeSlashCoordinates(serie, board, lastPlayedPosition);
		this.computeAntiSlashCoordinates(serie, board, lastPlayedPosition);
		return serie;
	}

	void computeAntiSlashCoordinates(CoordinatesSeries serie, Board board,
			Coordinate lastPlayedPosition) {
		int currentX = lastPlayedPosition.getX();
		int currentY = lastPlayedPosition.getY();
		
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		
		int maxX = getMaxX(board);
		int maxY = getMaxY(board);
		
		boolean finish = false;
		int y = currentY;
		for (int x = currentX ; x <= maxX && ! finish ; x++) {
			if (y >= 0) {
				coordinates.add(board.getCoordinate(x,y));
			} else {
				finish = true;
			}
			y--;
		}
		finish = false;
		y = currentY+1;
		for (int x = currentX-1 ; x >= 0 && ! finish ; x--) {
			if (y <= maxY) {
				coordinates.add(board.getCoordinate(x,y));
			} else {
				finish = true;
			}
			y++;
		}
		serie.setAntiSlashCoordinates(
				coordinates.stream().filter(
						c->c.getOwner()!= null && c.getOwner().equals(lastPlayedPosition.getOwner())
						).collect(Collectors.toList())
				);
	}

	void computeSlashCoordinates(CoordinatesSeries serie, Board board,
			Coordinate lastPlayedPosition) {
		int currentX = lastPlayedPosition.getX();
		int currentY = lastPlayedPosition.getY();
		
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		
		int maxX = getMaxX(board);
		int maxY = getMaxY(board);
		
		boolean finish = false;
		int y = currentY;
		for (int x = currentX ; x <= maxX && ! finish ; x++) {
			if (y <= maxY) {
				coordinates.add(board.getCoordinate(x,y));
			} else {
				finish = true;
			}
			y++;
		}
		finish = false;
		y = currentY-1;
		for (int x = currentX-1 ; x >= 0 && ! finish ; x--) {
			if (y >= 0) {
				coordinates.add(board.getCoordinate(x,y));
			} else {
				finish = true;
			}
			y--;
		}
		serie.setSlashCoordinates(
				coordinates.stream().filter(
						c->c.getOwner()!= null && c.getOwner().equals(lastPlayedPosition.getOwner())
						).collect(Collectors.toList())
				);
	}

	void computeHorizontalCoordinates(CoordinatesSeries serie, Board board,
			Coordinate lastPlayedPosition) {
		List<Coordinate> horizontalCoordinates = board.getCoordinates().stream().filter(c -> c.getY() == lastPlayedPosition.getY())
				.filter(c -> lastPlayedPosition.getOwner() != null
						&& lastPlayedPosition.getOwner().equals(c.getOwner()))
				.collect(Collectors.toList());
		serie.setHorizontalCoordinates(horizontalCoordinates);
	}

	void computeVerticalCoordinates(CoordinatesSeries serie, Board board,
			Coordinate lastPlayedPosition) {
		List<Coordinate> verticalCoordinates = board.getCoordinates().stream().filter(c -> c.getX() == lastPlayedPosition.getX())
				.filter(c -> lastPlayedPosition.getOwner() != null
						&& lastPlayedPosition.getOwner().equals(c.getOwner()))
				.collect(Collectors.toList());
		serie.setVerticalCoordinates(verticalCoordinates);
	}
	
	private int getMaxX(Board board) {
		int maxX = 0;
		for (Coordinate coordinate : board.getCoordinates()) {
			if (coordinate.getX() > maxX) {
				maxX = coordinate.getX();
			}
		}
		return maxX;
	}
	
	private int getMaxY(Board board) {
		int maxY = 0;
		for (Coordinate coordinate : board.getCoordinates()) {
			if (coordinate.getY() > maxY) {
				maxY = coordinate.getX();
			}
		}
		return maxY;
	}

}

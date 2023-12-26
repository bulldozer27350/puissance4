package com.moe.puissance4back.core;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.moe.puissance4back.core.model.Board;
import com.moe.puissance4back.core.model.Coordinate;
import com.moe.puissance4back.core.model.CoordinatesSeries;

@Component
public class WinCheckerImpl implements WinChecker {

	private static final int WINNING_LENGTH = 4;

	@Override
	public boolean checkForWin(CoordinatesSeries series, Board board) {
		// Vérifie les lignes horizontales (Y stable et X différent)
		if (checkLine(series.getHorizontalCoordinates(), getMaxX(board))) {
			return true;
		}

		// Vérifie les colonnes verticales (X stable et Y différent)
		if (checkColumn(series.getVerticalCoordinates(), getMaxY(board))) {
			return true;
		}

		// Vérifie les diagonales (\)
		if (checkLine(series.getSlashCoordinates(), getMaxX(board))) {
			return true;
		}

		// Vérifie les diagonales (/)
		if (checkLine(series.getAntiSlashCoordinates(), getMaxX(board))) {
			return true;
		}

		return false;
	}

	private boolean checkColumn(List<Coordinate> verticalCoordinates, int maxY) {
		int maxSerieNumber = 0;
		List<Integer> yCoordinates = verticalCoordinates.stream().sorted(((c1, c2) -> c2.getY() - c1.getY()))
				.map(Coordinate::getY).collect(Collectors.toList());
		int serie = 0;
		for (int i = 0 ; i <= maxY; i++) {
			if (yCoordinates.contains(i)) {
				serie++;
				if (serie > maxSerieNumber) {
					maxSerieNumber = serie;
				}
			}
			else {
				serie = 0;
			}
		}
		return maxSerieNumber >= WINNING_LENGTH;
	}

	private boolean checkLine(List<Coordinate> horizontalCoordinates, int maxX) {
		int maxSerieNumber = 0;
		List<Integer> xCoordinates = horizontalCoordinates.stream().sorted(((c1, c2) -> c2.getX() - c1.getX()))
				.map(Coordinate::getX).collect(Collectors.toList());
		int serie = 0;
		for (int i = 0 ; i <= maxX; i++) {
			if (xCoordinates.contains(i)) {
				serie++;
				if (serie > maxSerieNumber) {
					maxSerieNumber = serie;
				}
			}
			else {
				serie = 0;
			}
		}
		return maxSerieNumber >= WINNING_LENGTH;
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
				maxY = coordinate.getY();
			}
		}
		return maxY;
	}
}

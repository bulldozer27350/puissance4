package com.moe.puissance4back.core.model;

import java.util.ArrayList;
import java.util.List;

import com.moe.puissance4back.core.InternalJoueur;

public class Board {

	private List<Coordinate> coordinates;
	
	public Board() {
		coordinates = new ArrayList<>();
	}
	
	public Coordinate getCoordinate(int x, int y) {
		return this.coordinates.stream().filter(c->c.getX() == x && c.getY() == y).findFirst().get();
	}

	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setOwner(InternalJoueur currentPlayer, int x, int y) {
		this.getCoordinate(x, y).setOwner(currentPlayer);
	}

	public void initialize(int xMax, int yMax) {
		for (int x = 0 ; x < xMax ; x++) {
			for (int y = 0 ; y < yMax ; y++) {
				Coordinate coord = new Coordinate();
				coord.setOwner(null);
				coord.setX(x);
				coord.setY(y);
				this.coordinates.add(coord);
			}
		}
	}
	
}

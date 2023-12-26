package com.moe.puissance4back.core.model;

import java.util.List;

public class CoordinatesSeries {

	private List<Coordinate> horizontalCoordinates;
	private List<Coordinate> verticalCoordinates;
	private List<Coordinate> slashCoordinates;
	private List<Coordinate> antiSlashCoordinates;
	
	public List<Coordinate> getHorizontalCoordinates() {
		return horizontalCoordinates;
	}

	public List<Coordinate> getVerticalCoordinates() {
		return verticalCoordinates;
	}

	public List<Coordinate> getSlashCoordinates() {
		return slashCoordinates;
	}

	public List<Coordinate> getAntiSlashCoordinates() {
		return antiSlashCoordinates;
	}

	public void setHorizontalCoordinates(List<Coordinate> horizontalCoordinates) {
		this.horizontalCoordinates = horizontalCoordinates;
	}

	public void setVerticalCoordinates(List<Coordinate> verticalCoordinates) {
		this.verticalCoordinates = verticalCoordinates;
	}

	public void setSlashCoordinates(List<Coordinate> slashCoordinates) {
		this.slashCoordinates = slashCoordinates;
	}

	public void setAntiSlashCoordinates(List<Coordinate> antiSlashCoordinates) {
		this.antiSlashCoordinates = antiSlashCoordinates;
	}
	

}

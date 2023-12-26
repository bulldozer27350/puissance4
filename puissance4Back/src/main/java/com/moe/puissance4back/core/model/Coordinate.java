package com.moe.puissance4back.core.model;

import com.moe.puissance4back.core.InternalJoueur;

public class Coordinate {

	private int x;
	private int y;
	private InternalJoueur owner;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public InternalJoueur getOwner() {
		return owner;
	}
	public void setOwner(InternalJoueur owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "x : " + x + " y : " + y + " belongs to " + owner;
	}
	
}

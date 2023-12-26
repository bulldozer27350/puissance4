package com.moe.puissance4back.core.model;

import com.moe.puissance4back.core.InternalJoueur;

public class InternalPlacement {

	public static final int GAME_ENDED = 1;
	public static final int INVALID_COLUMN = 2;
	public static final int FULL_COLUMN = 3;
	public static final int WIN = 4;
	public static final int DRAW = 5;
	public static final int ONGOING = 6;
	
	private Integer status;
	private InternalJoueur currentPlayer;
	
	private int x;
	private int y;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer error) {
		this.status = error;
	}

	public InternalJoueur getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(InternalJoueur currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

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
	
	
	
}

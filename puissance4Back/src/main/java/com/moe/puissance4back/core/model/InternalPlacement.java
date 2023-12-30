package com.moe.puissance4back.core.model;

import com.moe.puissance4back.core.InternalJoueur;

public class InternalPlacement {

	public static final int GAME_ENDED = 1;
	public static final int INVALID_COLUMN = 2;
	public static final int FULL_COLUMN = 3;
	public static final int WIN = 4;
	public static final int DRAW = 5;
	public static final int ONGOING = 6;
	public static final int NOT_INITIALIZED = 7;
	
	private Integer status;
	private InternalJoueur currentPlayer;
	private InternalJoueur player1;
	private InternalJoueur player2;
	
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
	
	public InternalJoueur getPlayer1() {
		return player1;
	}

	public void setPlayer1(InternalJoueur player1) {
		this.player1 = player1;
	}

	public InternalJoueur getPlayer2() {
		return player2;
	}

	public void setPlayer2(InternalJoueur player2) {
		this.player2 = player2;
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

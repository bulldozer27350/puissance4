package com.moe.puissance4Server.api.model;

public class Placement {
	public static final int GAME_ENDED = 1;
	public static final int INVALID_COLUMN = 2;
	public static final int FULL_COLUMN = 3;
	public static final int WIN = 4;
	public static final int DRAW = 5;
	public static final int ONGOING = 6;
	public static final int NOT_INITIALIZED = 7;

	private int x;
	private int y;

	private Integer status;
	private Joueur currentPlayer;
	private Joueur joueur1;
	private Joueur joueur2;

	private int partieId;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer error) {
		this.status = error;
	}

	public Joueur getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Joueur currentPlayer) {
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

	public void setPartieId(int partieId) {
		this.partieId = partieId;
	}
	
	public int getPartieId() {
		return this.partieId;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("status : ");
		str.append(status);
		str.append("\n");
		
		str.append("joueur1 : ");
		str.append(joueur1);
		str.append("\n");
		
		str.append("joueur2 : ");
		str.append(joueur2);
		str.append("\n");
		
		str.append("currentPlayer : ");
		str.append(currentPlayer);
		str.append("\n");
		
		str.append("x : ");
		str.append(x);
		str.append("\n");
		
		str.append("y : ");
		str.append(y);
		str.append("\n");
		
		str.append("partieId : ");
		str.append(partieId);
		str.append("\n");
		
		return str.toString();
	}
}

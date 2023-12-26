package com.moe.puissance4back.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.moe.puissance4back.api.GameService;
import com.moe.puissance4back.core.CoordinatesManagement;
import com.moe.puissance4back.core.InternalJoueur;
import com.moe.puissance4back.core.WinChecker;
import com.moe.puissance4back.core.model.Board;
import com.moe.puissance4back.core.model.Coordinate;
import com.moe.puissance4back.core.model.CoordinatesSeries;
import com.moe.puissance4back.core.model.InternalPlacement;

@Service
public class GameServiceImpl implements GameService {

	private Board board;
	private InternalJoueur joueur1;
	private InternalJoueur joueur2;
	private InternalJoueur currentPlayer;
	private boolean gameEnded;
	private WinChecker winChecker;

	public GameServiceImpl(WinChecker winChecker) {
		this.winChecker = winChecker;
	}

	@Override
	public InternalPlacement initializeGame(String nomJoueur1, String nomJoueur2, int nbCol, int nbRow) {
		board = new Board();
		board.initialize(nbCol, nbRow);
		joueur1 = new InternalJoueur(nomJoueur1);
		joueur2 = new InternalJoueur(nomJoueur2);
		currentPlayer = joueur1;
		gameEnded = false;
		InternalPlacement placement = new InternalPlacement();
		placement.setCurrentPlayer(joueur1);
		placement.setStatus(InternalPlacement.ONGOING);
		return placement;
	}

	@Override
	public InternalPlacement playPiece(int x) {
		InternalPlacement placement = new InternalPlacement();
		if (gameEnded) {
			placement.setStatus(InternalPlacement.GAME_ENDED);
			return placement;
		}

		if (x < 0 || x >= 7) {
			placement.setStatus(InternalPlacement.INVALID_COLUMN);
			return placement;
		}

		int y = findY(board, x);

		if (y == -1) {
			placement.setStatus(InternalPlacement.FULL_COLUMN);
			return placement;
		}

		
		// positionne la pièce à la coordonnée x;y
		// initialement, la valeur du propriétaire est nulle
		// le fait d'affecter un propriétaire instancie réèllement la pièce
		board.setOwner(currentPlayer, x, y);
		CoordinatesManagement management = new CoordinatesManagement();
		CoordinatesSeries series = management.computeCoordinates(board, board.getCoordinate(x, y));

		if (winChecker.checkForWin(series, board)) {
			gameEnded = true;
			placement.setStatus(InternalPlacement.WIN);
			return placement;
		} else if (checkForDraw()) {
			gameEnded = true;
			placement.setStatus(InternalPlacement.DRAW);
			return placement;
		} else {
			switchPlayer();
			placement.setCurrentPlayer(currentPlayer);
			placement.setStatus(InternalPlacement.ONGOING);
			placement.setX(x);
			placement.setY(y);
			return placement;
		}
	}

	@Override
	public boolean isGameEnded() {
		return gameEnded;
	}

	@Override
	public Optional<InternalJoueur> getWinner() {
		if (gameEnded) {
			// Logique pour déterminer le gagnant
			// (à adapter en fonction de votre logique de victoire)
			return Optional.of(currentPlayer);
		} else {
			return Optional.empty();
		}
	}

	private void switchPlayer() {
		currentPlayer = (currentPlayer.getId() == joueur1.getId()) ? joueur2 : joueur1;
	}

	private int findY(Board board, int x) {
		int minY = -1;
		for (Coordinate coord : board.getCoordinates().stream().filter(c -> c.getX() == x)
				.filter(c -> c.getOwner() == null).collect(Collectors.toList())) {
			if (coord.getY() > minY) {
				minY = coord.getY();
			}
		}
		return minY;
	}

	private boolean checkForDraw() {
		for (Coordinate coord : board.getCoordinates()) {
			if (coord.getOwner() == null) {
				return false;
			}
		}
		return true;
	}
}

package com.moe.puissance4back.api;

import java.util.Optional;

import com.moe.puissance4back.core.InternalJoueur;
import com.moe.puissance4back.core.model.InternalPlacement;

public interface GameService {

    InternalPlacement initializeGame(String nomJoueur1, String nomJoueur2, int nbCol, int nbRow);

    InternalPlacement playPiece(int column);

    // Nouvelle méthode pour vérifier l'état de la partie
    boolean isGameEnded();

    // Nouvelle méthode pour obtenir le gagnant (ou null si la partie n'est pas terminée)
    Optional<InternalJoueur> getWinner();

}

package com.moe.puissance4back.api;

import java.util.Optional;

import com.moe.puissance4back.core.InternalJoueur;
import com.moe.puissance4back.core.model.InternalPlacement;

/**
 * Service permettant de gérer une partie. Peut être géré par Spring pour
 * l'initialisation.
 */
public interface GameService {

	/**
	 * Initialise une partie complète avec deux joueurs.
	 * 
	 * @param nomJoueur1 Nom du premier joueur. C'est lui qui jouera en premier.
	 * @param nomJoueur2 Nom du second joueur.
	 * @param nbCol      Nombre de colonnes dans le jeu. Ne peut être négatif.
	 * @param nbRow      Nombre de lignes dans le jeu. Ne peut être négatif.
	 * @return l'objet contennant notamment l'état du jeu ainsi que le joueur en
	 *         cours.
	 */
	InternalPlacement initializeGame(String nomJoueur1, String nomJoueur2, int nbCol, int nbRow);

	/**
	 * Initialise une partie avec un seul joueur. Soit le joueur joue contre
	 * l'ordinateur, soit il attend un second joueur qui arrivera plus tard.
	 * 
	 * @param nomJoueur1 Nom du joueur qui initialise la partie. C'est ce joueur qui
	 *                   jouera en premier.
	 * @param nbCol      Nombre de colonnes dans le jeu. Ne peut être négatif.
	 * @param nbRow      Nombre de lignes dans le jeu. Ne peut être négatif.
	 * @return l'objet contennant notamment l'état du jeu ainsi que le joueur en
	 *         cours.
	 */
	InternalPlacement initializeGame(String nomJoueur1, int nbCol, int nbRow);

	/**
	 * Initialise une partie sans joueur (pour permettre d'être spectateur par
	 * exemple). Le premier joueur qui arrivera sera le joueur 1 et jouera le
	 * premier lorsque le second joueur arrivera.
	 * 
	 * @param nbCol Nombre de colonnes dans le jeu. Ne peut être négatif.
	 * @param nbRow Nombre de lignes dans le jeu. Ne peut être négatif.
	 * @return l'objet contennant notamment l'état du jeu ainsi que le joueur en
	 *         cours.
	 */
	InternalPlacement initializeGame(int nbCol, int nbRow);

	/**
	 * Permet de fixer le nom du premier joueur. Peut être utile si l'initialisation
	 * de la partie s'est faite sans joueur. Ne pas utiliser dans un contexte de
	 * mise à jour car cette méthode permet également de fixer le joueur en cours au
	 * premier joueur.
	 * 
	 * @param joueur1 le nom du premier joueur
	 * 
	 * @return l'objet contennant notamment l'état du jeu ainsi que le joueur en
	 *         cours.
	 */
	InternalPlacement setJoueur1(String joueur1);

	/**
	 * Permet de fixer le nom du premier joueur. Peut être utile si l'initialisation
	 * de la partie s'est faite sans joueur2 (avec ou sans joueur1). Ne pas utiliser
	 * pour renommer le nom d'un joueur car cette méthode réaffect le joueur en
	 * cours au premier joueur
	 * 
	 * @param joueur2 le nom du second joueur
	 * 
	 * @return l'objet contennant notamment l'état du jeu ainsi que le joueur en
	 *         cours.
	 */
	InternalPlacement setJoueur2(String joueur2);

	/**
	 * Permet de jouer un pion de la couleur du joueur en cours (variable tenue par
	 * l'instance en cours) dans la bonne colonne. Ce pion arrivera à la bonne ligne
	 * en fonction des pions déjà en place.
	 * 
	 * @param column Index de la colonne où jouer le jeton. Ne peut pas être négatif
	 *               et doit être inférieur à la valeur du nombre de colonne
	 *               renseigné lors de l'initialisation de la partie.
	 * @return l'objet contennant notamment l'état du jeu ainsi que le joueur en
	 *         cours. Il contient également les coordonnées du jeton joué (ligne
	 *         mise à jour, et colonne cohérent avec column)
	 */
	InternalPlacement playPiece(int column);

	/**
	 * Permet de savoir si la partie en cours est terminée ou non. Les différentes
	 * façons de terminer une partie sont une victoire ou un match nul.
	 * 
	 * @return vrai si la partie est terminée. Faux dans le cas contraire.
	 */
	boolean isGameEnded();

	/**
	 * Renvoie le joueur qui a gagné la partie. Optionnal.empty est renvoyé si la
	 * partie n'a pas de vainqueur (égalité ou partie encore en cours).
	 * 
	 * @return Le joueur ayant gagné la partie ou Optional.empty s'il n'existe pas.
	 */
	Optional<InternalJoueur> getWinner();

}

package com.moe.puissance4.front.internal.adapteur;

import org.openapitools.client.model.Joueur;

import com.moe.puissance4.front.internal.model.FrontJoueur;

public class FrontJoueurAdapteur {

	public FrontJoueur convert(Joueur joueur) {
		FrontJoueur fJoueur = new FrontJoueur();
		fJoueur.setName(joueur.getNom());
		fJoueur.setInternalId(joueur.getId());
		return fJoueur;
	}
	
	public Joueur convert(FrontJoueur joueur) {
		Joueur fJoueur = new Joueur();
		fJoueur.setNom(joueur.getName());
		fJoueur.setId(joueur.getInternalId());
		return fJoueur;
	}
	
}

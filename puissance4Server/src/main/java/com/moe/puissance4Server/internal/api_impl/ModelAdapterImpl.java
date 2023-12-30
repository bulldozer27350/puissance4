package com.moe.puissance4Server.internal.api_impl;

import org.springframework.stereotype.Service;

import com.moe.puissance4Server.api.model.Joueur;
import com.moe.puissance4Server.api.model.Placement;
import com.moe.puissance4back.core.InternalJoueur;
import com.moe.puissance4back.core.model.InternalPlacement;

@Service
public class ModelAdapterImpl implements ModelAdapter {

	@Override
	public Joueur convert(InternalJoueur joueur) {
		Joueur j = new Joueur();
		j.setId(joueur.getId());
		j.setNom(joueur.getNom());
		return j;
	}
	
	@Override
	public InternalJoueur convert(Joueur joueur) {
		InternalJoueur j = new InternalJoueur();
		j.setId(joueur.getId());
		j.setNom(joueur.getNom());
		return j;
	}

	@Override
	public Placement convert(InternalPlacement pl) {
		Placement p = new Placement();
		p.setStatus(pl.getStatus());
		if (pl.getCurrentPlayer() != null) {
			p.setCurrentPlayer(this.convert(pl.getCurrentPlayer()));
		}
		if (pl.getPlayer1() != null) {
			p.setJoueur1(this.convert(pl.getPlayer1()));
		}
		if (pl.getPlayer2() != null) {
			p.setJoueur2(this.convert(pl.getPlayer2()));
		}
		p.setX(pl.getX());
		p.setY(pl.getY());
		return p;
	}
	
}

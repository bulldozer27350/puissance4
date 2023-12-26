package com.moe.puissance4Server.internal.api_impl;

import com.moe.puissance4Server.api.model.Joueur;
import com.moe.puissance4Server.api.model.Placement;
import com.moe.puissance4back.core.InternalJoueur;
import com.moe.puissance4back.core.model.InternalPlacement;

public interface ModelAdapter {

	Joueur convert(InternalJoueur joueur);

	InternalJoueur convert(Joueur joueur);

	Placement convert(InternalPlacement initializeGame);

}

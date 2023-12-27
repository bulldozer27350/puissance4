package com.moe.puissance4Server.internal.api_impl.multi;

import com.moe.puissance4back.api.GameService;

public interface MultiGameServicesManagement {

	public int createGame();
	
	GameService retrieveGame(int id);
	
}

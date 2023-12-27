package com.moe.puissance4Server.internal.api_impl.multi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.moe.puissance4back.api.GameService;
import com.moe.puissance4back.core.WinChecker;
import com.moe.puissance4back.core.WinCheckerImpl;
import com.moe.puissance4back.service.GameServiceImpl;

@Service
public class MultiGameServicesManagementImpl implements MultiGameServicesManagement {

	private Map<Integer, GameService> gameServices;
	
	public MultiGameServicesManagementImpl() {
		gameServices = new HashMap<>();
	}
	
	@Override
	public int createGame() {
		WinChecker win = new WinCheckerImpl();
		GameService gameService = new GameServiceImpl(win);
		int max = 0;
		if (!gameServices.isEmpty()) {
			max = gameServices.keySet().stream().max(Integer::compareTo).get();
		}
		this.gameServices.put(max, gameService);
		return max;
	}

	@Override
	public GameService retrieveGame(int id) {
		return this.gameServices.getOrDefault(id, null);
	}

}

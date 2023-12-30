package com.moe.puissance4Server.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GameControllerTest {

	@Autowired
	private GameController gameController;
	
	@Test
	public void test() {
		Map<String, String> map = new HashMap<>();
		map.put("idPartie", "30");
		map.put("nomJoueur", "B");
		gameController.joinGame(map);
	}
}

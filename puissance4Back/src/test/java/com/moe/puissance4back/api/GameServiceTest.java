package com.moe.puissance4back.api;

import org.junit.Assert;
import org.junit.Test;

import com.moe.puissance4back.core.WinChecker;
import com.moe.puissance4back.core.WinCheckerImpl;
import com.moe.puissance4back.core.model.InternalPlacement;
import com.moe.puissance4back.service.GameServiceImpl;

public class GameServiceTest {
	
	@Test
	public void testGetLinesCoordinates() {
		WinChecker checker = new WinCheckerImpl();
		GameService service = new GameServiceImpl(checker);
		service.initializeGame("J1", "J2", 7, 6);
		
		InternalPlacement lastPiece = service.playPiece(0);
		Assert.assertEquals(InternalPlacement.ONGOING, lastPiece.getStatus().intValue());
		Assert.assertEquals(0, 0);
		
		lastPiece = service.playPiece(0);
		Assert.assertEquals(InternalPlacement.ONGOING, lastPiece.getStatus().intValue());
		
		lastPiece = service.playPiece(1);
		Assert.assertEquals(InternalPlacement.ONGOING, lastPiece.getStatus().intValue());
		
		lastPiece = service.playPiece(1);
		Assert.assertEquals(InternalPlacement.ONGOING, lastPiece.getStatus().intValue());
		
		lastPiece = service.playPiece(2);
		Assert.assertEquals(InternalPlacement.ONGOING, lastPiece.getStatus().intValue());
		
		lastPiece = service.playPiece(0);
		Assert.assertEquals(InternalPlacement.ONGOING, lastPiece.getStatus().intValue());
		
		lastPiece = service.playPiece(3);
		Assert.assertEquals(InternalPlacement.WIN, lastPiece.getStatus().intValue());
	}
}

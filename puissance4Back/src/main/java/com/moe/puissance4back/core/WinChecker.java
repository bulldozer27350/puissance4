package com.moe.puissance4back.core;

import com.moe.puissance4back.core.model.Board;
import com.moe.puissance4back.core.model.CoordinatesSeries;

public interface WinChecker {

	boolean checkForWin(CoordinatesSeries series, Board board);

}

package com.example.game.entity.board.interfaces;

import com.example.game.entity.TargetStatus;

public interface ShipHandlerBoard {
    TargetStatus handleShot(int row, int col);
    boolean hasAliveShips();
    boolean placeShip(int shipSize, int row, int col, boolean vertical);
}

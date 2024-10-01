package com.example.game.manager;

public interface ShipManager {
    void init(int rowsCount, int columnsCount);
    void placeShipsForPlayers(PlayerManager playerManager);
}

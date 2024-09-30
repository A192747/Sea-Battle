package com.example.game.manager;

import com.example.game.entity.Player;

public interface PlayerManager {
    void init(int fieldRowsNum, int fieldColsNum);
    Player getPlayer1();
    Player getPlayer2();
}

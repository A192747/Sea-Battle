package com.example.game.entity;

import com.example.game.entity.board.interfaces.OpponentBoard;
import com.example.game.entity.board.interfaces.PlayerBoard;

public interface BoardFactory {
    void init(int rowCount, int columnCount);
    PlayerBoard createPlayerBoard();
    OpponentBoard createOpponentBoard();
}

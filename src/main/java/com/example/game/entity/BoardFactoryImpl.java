package com.example.game.entity;

import com.example.game.entity.board.OpponentBoardImpl;
import com.example.game.entity.board.PlayerBoardImpl;
import com.example.game.entity.board.interfaces.OpponentBoard;
import com.example.game.entity.board.interfaces.PlayerBoard;

public class BoardFactoryImpl implements BoardFactory {

    private int rowCount;
    private int columnCount;

    @Override
    public void init(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    @Override
    public PlayerBoard createPlayerBoard() {
        return new PlayerBoardImpl(rowCount, columnCount);
    }

    @Override
    public OpponentBoard createOpponentBoard() {
        return new OpponentBoardImpl(rowCount, columnCount);
    }
}

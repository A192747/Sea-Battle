package com.example.game.entity.board;

import com.example.game.entity.TargetStatus;
import com.example.game.entity.board.interfaces.OpponentBoard;

public class OpponentBoardImpl extends Board implements OpponentBoard {
    public OpponentBoardImpl(int countOfRows, int countOfCols) {
        super(countOfRows, countOfCols);
    }

    public void saveAttackHistory(int row, int col, TargetStatus targetStatus) {
        switch(targetStatus) {
            case HIT_TARGET -> super.field[row][col] = '#';
            case MISSED -> super.field[row][col] = '*';
        }
    }
}

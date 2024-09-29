package com.example.game.board;

import com.example.game.TargetStatus;

public class OpponentBoard extends Board {
    public OpponentBoard(int countOfRows, int countOfCols) {
        super(countOfRows, countOfCols);
    }

    public void saveAttackHistory(int row, int col, TargetStatus targetStatus) {
        switch(targetStatus) {
            case HIT_TARGET -> {
                super.field[row][col] = '#';
            }
            case MISSED -> {
                super.field[row][col] = '*';
            }
        }
    }

}

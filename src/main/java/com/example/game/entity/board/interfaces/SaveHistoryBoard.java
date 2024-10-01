package com.example.game.entity.board.interfaces;

import com.example.game.entity.TargetStatus;

public interface SaveHistoryBoard {
    void saveAttackHistory(int row, int col, TargetStatus targetStatus);
}

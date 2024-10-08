package com.example.game.entity;

import com.example.game.entity.board.interfaces.OpponentBoard;
import com.example.game.entity.board.interfaces.PlayerBoard;

public class Player {
    private final String name;
    private final PlayerBoard playerBoard;
    private final OpponentBoard opponentBoard;
    public Player(String name, PlayerBoard playerBoard, OpponentBoard opponentBoard) {
        this.name = name;
        this.playerBoard = playerBoard;
        this.opponentBoard = opponentBoard;
    }
    public String getName() {
        return name;
    }

    public boolean placeShip(int shipSize, int row, int col, boolean inVerticalPosition) {
        return playerBoard.placeShip(shipSize, row, col, inVerticalPosition);
    }

    public TargetStatus handleShot(int row, int col) {
        return playerBoard.handleShot(row, col);
    }

    public void saveAttackHistory(int row, int col, TargetStatus targetStatus) {
        opponentBoard.saveAttackHistory(row, col, targetStatus);
    }

    public void printPlayerBoard() {
        System.out.println("Ваше поле: ");
        playerBoard.printBoard();
    }
    public void printOpponentBoard() {
        System.out.println("Поле вашего противника: ");
        opponentBoard.printBoard();
    }

    public boolean isPlayerLoseGame() {
        return !playerBoard.hasAliveShips();
    }
}

package com.example.game;

import com.example.game.board.OpponentBoard;
import com.example.game.board.PlayerBoard;

public class Player {
    private final String name;
    private final PlayerBoard playerBoard;
    private final OpponentBoard opponentBoard;
    public Player(String name, int countOfRows, int countOfCols) {
        this.name = name;
        playerBoard = new PlayerBoard(countOfRows, countOfCols);
        opponentBoard = new OpponentBoard(countOfRows, countOfCols);
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

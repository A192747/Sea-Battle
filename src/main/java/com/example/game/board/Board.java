package com.example.game.board;

public abstract class Board {
    protected final int fieldRowsCount;
    protected final int fieldColsCount;
    protected final char[][] field;

    public Board(int countOfRows, int countOfCols) {
        if(countOfRows < 0 || countOfCols < 0)
            throw new IllegalArgumentException("Размеры поля должны быть > 0");
        field = new char[countOfRows][countOfCols];
        fieldRowsCount = countOfRows;
        fieldColsCount = countOfCols;
        fillEmptyBoard();
    }
    private void fillEmptyBoard() {
        for(int i = 0; i < fieldRowsCount; i++) {
            for(int j = 0; j < fieldColsCount; j++) {
                field[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        for(int i = 0; i < fieldRowsCount; i++) {
            printLine(fieldColsCount);
            System.out.print("|");
            for(int j = 0; j < fieldColsCount; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.print("\n");
        }
        printLine(fieldColsCount);
    }
    private void printLine(int countOfColumns) {
        for(int j = 0; j < countOfColumns; j++) {
            System.out.print("--");
        }
        System.out.print("-\n");
    }


}

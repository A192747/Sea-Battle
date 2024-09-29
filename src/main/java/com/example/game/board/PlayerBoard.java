package com.example.game.board;

import com.example.game.TargetStatus;

public class PlayerBoard extends Board {
    private int countOfShipsDesks = 0;

    public PlayerBoard(int countOfRows, int countOfCols) {
        super(countOfRows, countOfCols);
    }

    public TargetStatus handleShot(int row, int col) {
        if (row >= 0 && row < fieldRowsCount && col >= 0 && col < fieldColsCount) {
            if(field[row][col] == '1') {
                field[row][col] = '#';
                countOfShipsDesks--;
                return TargetStatus.HIT_TARGET;
            }
            field[row][col] = '*';
            return TargetStatus.MISSED;
        } else {
            //TODO правильную обработку ошибок при неверных введенных значениях
            throw new IllegalArgumentException("");
        }
    }

    public boolean placeShip(int shipSize, int row, int col, boolean vertical) {
        if(isPossibleToPlaceShip(shipSize, row, col, vertical)) {
            addShipToField(shipSize, row, col, vertical);
            countOfShipsDesks += shipSize;
            return true;
        }
        return false;
    }

    public boolean hasAliveShips() {
        return countOfShipsDesks > 0;
    }

    private void addShipToField(int shipSize, int row, int col, boolean vertical) {
        int value1 = col;
        int value2 = row;
        if(!vertical) {
            int temp = value1;
            value1 = value2;
            value2 = temp;
        }
        addShipByDirection(shipSize, value1, value2, vertical);
    }

    private void addShipByDirection(int shipSize, int value1, int value2, boolean vertical) {
        for(int i = value2; i < value2 + shipSize; i++) {
            if(vertical) {
                field[i][value1] = '1';
            } else {
                field[value1][i] = '1';
            }
        }
    }

    private boolean isPossibleToPlaceShip(int shipSize, int row, int col, boolean vertical) {
        //если размеры корабля выходят за пределы доски
        if(row > super.fieldRowsCount
                || col > super.fieldColsCount
                || vertical && (row + shipSize - 1 >= super.fieldRowsCount)
                || !vertical && (col + shipSize - 1 >= super.fieldColsCount)) {
            return false;
        }

        //проверка не заняты ли окружающие и необходимые нам ячейки поля
        int value1 = col;
        int value2 = row;
        if(!vertical) {
            int temp = value1;
            value1 = value2;
            value2 = temp;
        }
        return isFreeArea(value1, value2, shipSize, vertical);
    }

    private boolean isFreeArea(int value1, int value2, int shipSize, boolean vertical) {
        int fieldSize = vertical ? fieldRowsCount : fieldColsCount;
        int lastValue2Index = Math.min(value2 + shipSize + 1, fieldSize);
        int i = value2 - 1 >= 0 ? value2 - 1 : value2;
        int firstValue1Index = value1 - 1 >= 0 ? value1 - 1 : value2;
        int lastValue1Index = Math.min(value1 + 2, fieldSize);
        for(; i < lastValue2Index; i++) {
            for(int j = firstValue1Index; j < lastValue1Index ; j++) {
                if((vertical && field[i][j] == '1')
                        || (!vertical && field[j][i] == '1')) {
                    return false;
                }
            }
        }
        return true;
    }
}

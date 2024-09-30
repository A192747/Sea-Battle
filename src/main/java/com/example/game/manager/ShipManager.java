package com.example.game.manager;

import com.example.game.entity.Player;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import static com.example.game.Game.fieldSize;

public class ShipManager {
    private final Map<Integer, Integer> shipsAndCount;
    private final InputHandler inputHandler;

    public ShipManager(InputHandler inputHandler) {
        shipsAndCount = new HashMap<>();
        this.inputHandler = inputHandler;
    }

    public void init() throws InputMismatchException, IllegalArgumentException{
        System.out.print("Введите максимальное кол-во палуб, которое будет у корабля в игре: ");

        int maxCount = inputHandler.readIntegerInput();
        if(maxCount < 1 || maxCount > fieldSize) {
            throw new IllegalArgumentException("Максимальное кол-во палуб у корабля должно быть <= " + fieldSize + " и > 0");
        }

        if(fillMapCountOfDesks(maxCount) == 0) {
            throw new IllegalArgumentException("Количество кораблей на поле должно быть больше 0!");
        }
    }
    private void placeShipsForPlayer(Player player) {
        System.out.println("Расположение кораблей для игрока " + player.getName());
        for(Integer countOfDesks : shipsAndCount.keySet()) {
            if(countOfDesks > 0) {
                int countOfShips = shipsAndCount.get(countOfDesks);
                placeShipsByCountOfShips(player, countOfDesks, countOfShips);
            }
        }
        System.out.println();
    }

    private void placeShipsByCountOfShips(Player player, Integer countOfDesks, int countOfShips) {
        for(int i = 0; i < countOfShips; i++) {
            if(placeShip(player, countOfDesks, i + 1, countOfShips, countOfDesks != 1)) {
                player.printPlayerBoard();
            } else {
                System.err.println("""
                        Не удалось разместить корабль с такими координатами!
                        Корабль должен быть на расстоянии в 1 клетку от других.
                        Координаты корабля не должны выходить за пределы поля!""");
                i--;
            }
        }
    }

    private boolean placeShip(Player player, int countOfDesks, int currentShipNumber, int countOfShips, boolean askShipDirection) {
        System.out.println("Расположение корабля " + currentShipNumber + " из " + countOfShips + " с количеством палуб = " + countOfDesks);
        System.out.print("Буква строки: ");
        int row = inputHandler.readLetterInput();
        System.out.print("Буква столбца: ");
        int col = inputHandler.readLetterInput();

        boolean isVertical = true;
        if(askShipDirection) {
            System.out.print("Будет ли корабль расположен вертикально (A - нет | [B:" + (char)('A' + fieldSize - 1) + "] - да)? ");

            int direction = inputHandler.readLetterInput();
            isVertical = direction != 0;

        }

        return player.placeShip(countOfDesks, row, col, isVertical);
    }

    public void placeShipsForPlayers(PlayerManager playerManager) {
        System.out.println("---- Расположение кораблей для игроков ----");
        placeShipsForPlayer(playerManager.getPlayer1());
        placeShipsForPlayer(playerManager.getPlayer2());
    }
    private int fillMapCountOfDesks(int maxCount) {
        int sumCountOfDesks = 0;
        for(int i = 1; i <= maxCount; i++) {
            System.out.print("Введите количество кораблей с количеством палуб " + i + ": ");
            int count = inputHandler.readIntegerInput();
            sumCountOfDesks += count;
            shipsAndCount.put(i, count);
        }
        return sumCountOfDesks;
    }
}

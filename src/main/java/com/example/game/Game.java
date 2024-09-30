package com.example.game;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private Scanner scanner;
    private Player player1;
    private Player player2;
    private Map<Integer, Integer> shipsAndCount;
    private boolean gameOver = false;
    private final int fieldSize = 10;

    public void start() {
        try {
            init();
            placeShipsForPlayers();

            System.out.println("\n---- Начало боя ----");

            Player winner = startGameLoop(player1, player2);

            System.out.println("---- Игра окончена ----\n" +
                    "Победил игрок под именем " + winner.getName());

        } catch (InputMismatchException mismatchException) {
            System.err.println("Произошла ошибка приведения типов! Ожидался другой тип данных");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private Player startGameLoop(Player currentPlayer, Player targetPlayer) {
        while (!gameOver) {
            System.out.println("Ход игрока " + currentPlayer.getName());
            currentPlayer.printPlayerBoard();
            currentPlayer.printOpponentBoard();

            handleShots(currentPlayer, targetPlayer);

            Player temp = currentPlayer;
            currentPlayer = targetPlayer;
            targetPlayer = temp;
        }
        return targetPlayer;
    }

    private void handleShots(Player currentPlayer, Player targetPlayer) {
        while(true) {
            if(handleShot(currentPlayer, targetPlayer) == TargetStatus.MISSED)
                break;
            if(targetPlayer.isPlayerLoseGame()) {
                gameOver = true;
                break;
            }
            currentPlayer.printOpponentBoard();
        }
    }

    private void init() {
        System.out.println("---- Настройка игры ----");
        scanner = new Scanner(System.in);
        shipsAndCount = new HashMap<>();
        initPlayers();
        initShipsCount();
    }

    private void initPlayers() {
        System.out.print("Введите имя первого игрока: ");
        String name1 = scanner.nextLine();
        System.out.print("Введите имя второго игрока: ");
        String name2 = scanner.nextLine();
        if(name1.isEmpty() || name2.isEmpty()) {
            throw new IllegalArgumentException("Имена игроков не должны быть пустыми!");
        }

        player1 = new Player(name1, fieldSize, fieldSize); //поле 10x10
        player2 = new Player(name2, fieldSize, fieldSize); //поле 10x10
    }

    private void initShipsCount() throws InputMismatchException, IllegalArgumentException{
        System.out.print("Введите максимальное кол-во палуб, которое будет у корабля в игре: ");

        int maxCount = readIntegerInput();
        if(maxCount < 1 || maxCount > fieldSize) {
            throw new IllegalArgumentException("Максимальное кол-во палуб у корабля должно быть <= " + fieldSize + " и > 0");
        }

        if(fillMapCountOfDesks(maxCount) == 0) {
            throw new IllegalArgumentException("Количество кораблей на поле должно быть больше 0!");
        }
    }

    private int fillMapCountOfDesks(int maxCount) {
        int sumCountOfDesks = 0;
        for(int i = 1; i <= maxCount; i++) {
            System.out.print("Введите количество кораблей с количеством палуб " + i + ": ");
            int count = readIntegerInput();
            sumCountOfDesks += count;
            shipsAndCount.put(i, count);
        }
        return sumCountOfDesks;
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
                System.err.println("Не удалось разместить корабль с такими координатами!\n" +
                        "Корабль должен быть на расстоянии в 1 клетку от других.\n" +
                        "Координаты корабля не должны выходить за пределы поля!");
                i--;
            }
        }
    }

    private int readLetterInput() {
        int result = 0;
        while (true) {
            try {
                String string = scanner.next();
                if(string.length() == 1) {
                    result = string.charAt(0) - 'A';
                    if(result > fieldSize - 1 || result < 0) {
                        throw new IllegalArgumentException("" +
                                "Разрешено использовать только буквы в диапазоне [A:" + (char)('A' + fieldSize - 1) +  "].\n" +
                                "Введите корректное значение: ");
                    }
                }
                break;
            } catch (IllegalArgumentException e) {
                System.err.print(e.getMessage());
            }
        }
        return result;
    }

    private int readIntegerInput() {
        int result;
        while (true) {
            try {
                String string = scanner.next();
                result = Integer.valueOf(string);
                break;
            } catch (NumberFormatException e) {
                System.err.print("Не удалось преобразовать значение в число! Введите корректное значение: ");
            }
        }
        return result;
    }

    private boolean placeShip(Player player, int countOfDesks, int currentShipNumber, int countOfShips, boolean askShipDirection) {
        System.out.println("Расположение корабля " + currentShipNumber + " из " + countOfShips + " с количеством палуб = " + countOfDesks);
        System.out.print("Буква строки: ");
        int row = readLetterInput();
        System.out.print("Буква столбца: ");
        int col = readLetterInput();

        boolean isVertical = true;
        if(askShipDirection) {
            System.out.print("Будет ли корабль расположен вертикально (A - нет | [B:" + (char)('A' + fieldSize - 1) + "] - да)? ");

            int direction = readLetterInput();
            isVertical = direction != 'A';

        }

        return player.placeShip(countOfDesks, row, col, isVertical);
    }

    private void placeShipsForPlayers() {
        System.out.println("---- Расположение кораблей для игроков ----");
        placeShipsForPlayer(player1);
        placeShipsForPlayer(player2);
    }

    private TargetStatus handleShot(Player sniper, Player target) {
        System.out.print("Буква строки: ");
        int row = readLetterInput();
        System.out.print("Буква столбца: ");
        int col = readLetterInput() ;
        TargetStatus targetStatus = target.handleShot(row, col);
        switch (targetStatus) {
            case MISSED -> {
                System.out.println("Вы промахнулись!");
            }
            case HIT_TARGET -> {
                System.out.println("Вы попали в цель!");
            }
            case HIT_SAME_PLACE -> {
                System.out.println("Вы по этим координатам вы уже стреляли ранее!");
            }
        }
        sniper.saveAttackHistory(row, col, targetStatus);
        return targetStatus;
    }
}

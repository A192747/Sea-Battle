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
    boolean gameOver = false;

    //TODO добавить проверку вводимых значений INT
    //FIXME При расположении 2ух палубного корабля на координатах 9x9 ошибка null
    public void start() {
        try {
            init();
            placeShipsForPlayers();

            System.out.println("\n---- Начало боя ----");

            Player winner = startGameLoop(player1, player2);

            System.out.println("---- Игра окончена ----\n" +
                    "Победил игрок под именем " + winner.getName());

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
        initNames();
        initShipsCount();
    }

    private void initNames() {
        int fieldSize = 10;
        System.out.print("Введите имя первого игрока: ");
        String name1 = scanner.nextLine();
        System.out.print("Введите имя второго игрока: ");
        String name2 = scanner.nextLine();
        if(name1.isEmpty() || name2.isEmpty()) {
            throw new IllegalArgumentException("Имена игроков должны быть введены!");
        }

        player1 = new Player(name1, fieldSize, fieldSize); //поле 10x10
        player2 = new Player(name2, fieldSize, fieldSize); //поле 10x10
    }

    private void initShipsCount() throws InputMismatchException, IllegalArgumentException{
        System.out.print("Введите максимальное кол-во палуб, которое будет у корабля в игре: ");

        int maxCount = scanner.nextInt();
        if(maxCount < 1) {
            throw new IllegalArgumentException("Максимальное кол-во палуб у корабля должно быть типа int и > 0");
        }

        for(int i = 1; i <= maxCount; i++) {
            System.out.print("Введите количество кораблей с количеством палуб " + i + ": ");
            int count = scanner.nextInt();
            if(count < 1) {
                throw new IllegalArgumentException("Кол-во палуб у корабля должно быть типа int и > 0");
            }
            shipsAndCount.put(i, count);
        }
    }

    private void placeShipsForPlayer(Player player) {
        System.out.println("Расположение кораблей для игрока " + player.getName());
        for(Integer countOfDesks : shipsAndCount.keySet()) {
            if(countOfDesks > 0) {
                int countOfShips = shipsAndCount.get(countOfDesks);
                for(int i = 0; i < countOfShips; i++) {
                    if(placeShip(player, countOfDesks, i, countOfShips, countOfDesks != 1)) {
                        player.printPlayerBoard();
                    } else {
                        System.err.println("Не удалось разместить корабль с такими координатами!\n" +
                                "Корабль должен быть на расстоянии в 1 клетку от других.\n" +
                                "Координаты корабля не должны выходить за пределы поля!");
                        i--;
                    }
                }
            }
        }
        System.out.println();
    }

    private boolean placeShip(Player player, int countOfDesks, int currentShipNumber, int countOfShips, boolean askShipDirection) {
        System.out.println("Расположение корабля " + (currentShipNumber + 1) + " из " + countOfShips + " с количеством палуб = " + countOfDesks);
        System.out.print("Номер строки: ");
        int row = scanner.nextInt();
        System.out.print("Номер столбца: ");
        int col = scanner.nextInt();
        boolean isVertical = true;
        if(askShipDirection) {
            System.out.print("Будет ли корабль расположен вертикально (0 - нет | остальные символы - да)? ");
            if(scanner.nextInt() == 0) {
                isVertical = false;
            } else {
                isVertical = true;
            }
        }

        return player.placeShip(countOfDesks, row, col, isVertical);
    }

    private void placeShipsForPlayers() {
        System.out.println("---- Расположение кораблей для игроков ----");
        placeShipsForPlayer(player1);
        placeShipsForPlayer(player2);
    }

    private TargetStatus handleShot(Player sniper, Player target) {
        System.out.print("Номер строки: ");
        int row = scanner.nextInt();
        System.out.print("Номер столбца: ");
        int col = scanner.nextInt();
        TargetStatus targetStatus = target.handleShot(row, col);
        switch (targetStatus) {
            case TargetStatus.MISSED -> {
                System.out.println("Вы промахнулись!");
            }
            case TargetStatus.HIT_TARGET -> {
                System.out.println("Вы попали в цель!");
            }
        }
        sniper.saveAttackHistory(row, col, targetStatus);
        return targetStatus;
    }
}

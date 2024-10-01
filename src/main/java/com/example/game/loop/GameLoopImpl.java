package com.example.game.loop;

import com.example.game.entity.Player;
import com.example.game.entity.TargetStatus;
import com.example.game.manager.PlayerManager;
import com.example.game.manager.InputHandler;

public class GameLoopImpl implements GameLoop {
    private Player currentPlayer;
    private Player targetPlayer;
    private boolean gameOver = false;
    private Player winner;
    private final InputHandler inputHandler;

    public GameLoopImpl(PlayerManager playerManager, InputHandler inputHandler) {
        currentPlayer = playerManager.getPlayer1();
        targetPlayer = playerManager.getPlayer2();
        this.inputHandler = inputHandler;
    }

    public void startGame() {
        System.out.println("\n---- Начало боя ----");
        while (!gameOver) {
            System.out.println("Ход игрока " + currentPlayer.getName());
            currentPlayer.printPlayerBoard();
            currentPlayer.printOpponentBoard();

            handleShots(currentPlayer, targetPlayer);

            Player temp = currentPlayer;
            currentPlayer = targetPlayer;
            targetPlayer = temp;
        }
        winner = targetPlayer;
    }

    public void printWinner() {
        System.out.println("---- Игра окончена ----\n" +
                "Победил игрок под именем " + winner.getName());
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

    private TargetStatus handleShot(Player sniper, Player target) {
        System.out.print("Буква строки: ");
        int row = inputHandler.readLetterInput();
        System.out.print("Буква столбца: ");
        int col = inputHandler.readLetterInput();
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

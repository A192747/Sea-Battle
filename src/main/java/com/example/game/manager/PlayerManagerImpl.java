package com.example.game.manager;

import com.example.game.entity.BoardFactory;
import com.example.game.entity.Player;

public class PlayerManagerImpl implements PlayerManager {
    private Player player1;
    private Player player2;
    private final InputHandler inputHandler;
    private final BoardFactory boardFactory;

    public PlayerManagerImpl(InputHandler inputHandler, BoardFactory boardFactory) {
        this.inputHandler = inputHandler;
        this.boardFactory = boardFactory;
    }

    public void init() {
        System.out.print("Введите имя первого игрока: ");
        String name1 = inputHandler.readPlayerNameInput();
        System.out.print("Введите имя второго игрока: ");
        String name2 = inputHandler.readPlayerNameInput();
        if(name1.isEmpty() || name2.isEmpty()) {
            throw new IllegalArgumentException("Имена игроков не должны быть пустыми!");
        }

        player1 = new Player(name1,
                boardFactory.createPlayerBoard(),
                boardFactory.createOpponentBoard()); //поле 10x10
        player2 = new Player(name2,
                boardFactory.createPlayerBoard(),
                boardFactory.createOpponentBoard()); //поле 10x10
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}

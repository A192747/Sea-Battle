package com.example.game;

import com.example.game.entity.GameLoop;
import com.example.game.factory.GameFactory;
import com.example.game.manager.InputHandler;

import java.util.InputMismatchException;

public class Game {
    public final static int fieldSize = 10;
    private final GameFactory factory = new GameFactory();

    public void start() {
        try {

            factory.initialize();
            GameLoop gameLoop = factory.getGameLoop();
            gameLoop.startGame();
            gameLoop.printWinner();

        } catch (InputMismatchException mismatchException) {
            System.err.println("Произошла ошибка приведения типов! Ожидался другой тип данных");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            factory.getInputHandler().close();
        }
    }

}

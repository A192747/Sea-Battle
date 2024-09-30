package com.example.game;

import com.example.game.entity.GameLoop;
import com.example.game.factory.GameFactory;
import com.example.game.manager.InputHandler;

import java.util.InputMismatchException;

public class Game {
    public final static int fieldSize = 10;

    public void start() {
        InputHandler inputHandler = new InputHandler();
        try {

            GameFactory.initialize();
            GameLoop gameLoop = GameFactory.getGameLoop();
            gameLoop.startGame();
            gameLoop.printWinner();

        } catch (InputMismatchException mismatchException) {
            System.err.println("Произошла ошибка приведения типов! Ожидался другой тип данных");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println(inputHandler);
            System.out.println(GameFactory.getInputHandler());
            GameFactory.getInputHandler().close();
        }
    }

}

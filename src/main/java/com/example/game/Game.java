package com.example.game;

import com.example.game.loop.GameLoop;
import com.example.game.init.GameInitializerImpl;

import java.util.InputMismatchException;

public class Game {
    public final static int fieldSize = 10;
    private final GameInitializerImpl initializer = new GameInitializerImpl();

    public void start() {
        try {

            initializer.initialize();
            GameLoop gameLoop = initializer.getGameLoop();
            gameLoop.startGame();
            gameLoop.printWinner();

        } catch (InputMismatchException mismatchException) {
            System.err.println("Произошла ошибка приведения типов! Ожидался другой тип данных");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            initializer.getInputHandler().close();
        }
    }

}

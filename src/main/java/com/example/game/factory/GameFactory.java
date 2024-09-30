package com.example.game.factory;
import com.example.game.entity.GameLoop;
import com.example.game.manager.InputHandler;
import com.example.game.manager.PlayerManager;
import com.example.game.manager.ShipManager;

import static com.example.game.Game.fieldSize;

public class GameFactory {
    private static InputHandler inputHandler;
    private static PlayerManager playerManager;
    private static ShipManager shipManager;
    private static GameLoop gameLoop;

    private GameFactory() {}

    public static void initialize() {
        inputHandler = new InputHandler();
        playerManager = new PlayerManager(inputHandler, fieldSize);
        shipManager = new ShipManager(inputHandler, playerManager);
        gameLoop = new GameLoop(playerManager, inputHandler);
    }

    public static InputHandler getInputHandler() {
        return inputHandler;
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static GameLoop getGameLoop() {
        return gameLoop;
    }

}

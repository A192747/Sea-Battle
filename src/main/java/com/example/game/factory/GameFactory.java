package com.example.game.factory;
import com.example.game.entity.GameLoop;
import com.example.game.manager.InputHandler;
import com.example.game.manager.PlayerManager;
import com.example.game.manager.ShipManager;

import static com.example.game.Game.fieldSize;

public class GameFactory {
    private static InputHandler inputHandler;
    private static GameLoop gameLoop;

    public GameFactory() {}

    public void initialize() {
        inputHandler = new InputHandler();
        PlayerManager playerManager = new PlayerManager(inputHandler);
        playerManager.init(fieldSize);
        ShipManager shipManager = new ShipManager(inputHandler);
        shipManager.init();
        shipManager.placeShipsForPlayers(playerManager);
        gameLoop = new GameLoop(playerManager, inputHandler);
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

}

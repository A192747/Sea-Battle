package com.example.game.init;
import com.example.game.loop.GameLoop;
import com.example.game.loop.GameLoopImpl;
import com.example.game.manager.*;

import static com.example.game.Game.fieldSize;

public class GameInitializerImpl implements GameInitializer {
    private static InputHandler inputHandler;
    private static GameLoop gameLoop;
    private static PlayerManager playerManager;
    private static ShipManager shipManager;

    public GameInitializerImpl() {}

    public void initialize() {
        inputHandler = InputHandlerImpl.getInstance();
        playerManager = new PlayerManagerImpl(inputHandler);
        shipManager = new ShipManagerImpl(inputHandler);

        initPlayerAndShips();

        gameLoop = new GameLoopImpl(playerManager, inputHandler);
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    private void initPlayerAndShips() {
        playerManager.init(fieldSize, fieldSize);
        shipManager.init();
        shipManager.placeShipsForPlayers(playerManager);
    }

}

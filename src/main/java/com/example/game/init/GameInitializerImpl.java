package com.example.game.init;
import com.example.game.entity.BoardFactory;
import com.example.game.entity.BoardFactoryImpl;
import com.example.game.loop.GameLoop;
import com.example.game.loop.GameLoopImpl;
import com.example.game.manager.*;


public class GameInitializerImpl implements GameInitializer {
    private static InputHandler inputHandler;
    private static GameLoop gameLoop;
    private static PlayerManager playerManager;
    private static ShipManager shipManager;
    private static BoardFactory boardFactory;
    private final int rowCount;
    private final int columnCount;

    public GameInitializerImpl(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    public void initialize() {
        inputHandler = InputHandlerImpl.getInstance();
        boardFactory = new BoardFactoryImpl();
        playerManager = new PlayerManagerImpl(inputHandler, boardFactory);
        shipManager = new ShipManagerImpl(inputHandler);

        initEntities();

        gameLoop = new GameLoopImpl(playerManager, inputHandler);
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    private void initEntities() {
        inputHandler.init(rowCount, columnCount);
        boardFactory.init(rowCount, columnCount);
        playerManager.init();
        shipManager.init(rowCount, columnCount);
        shipManager.placeShipsForPlayers(playerManager);
    }

}

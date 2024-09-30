package com.example.game.factory;
import com.example.game.entity.GameLoop;
import com.example.game.entity.GameLoopImpl;
import com.example.game.manager.*;

import static com.example.game.Game.fieldSize;

public class GameFactory {
    private static InputHandler inputHandler;
    private static GameLoopImpl gameLoop;

    public GameFactory() {}

    public void initialize() {
        //TODO Разобраться как работает factory и в каком случае использовать getInstance
        //TODO Возможно вынести init в отдельный класс
        //TODO Правильно раскидать всё по папкам
        inputHandler = InputHandlerImpl.getInstance();
        PlayerManagerImpl playerManager = new PlayerManagerImpl(inputHandler);
        playerManager.init(fieldSize, fieldSize);
        ShipManager shipManager = new ShipManagerImpl(inputHandler);
        shipManager.init();
        shipManager.placeShipsForPlayers(playerManager);
        gameLoop = new GameLoopImpl(playerManager, inputHandler);
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

}

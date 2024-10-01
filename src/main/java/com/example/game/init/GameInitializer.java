package com.example.game.init;

import com.example.game.loop.GameLoop;
import com.example.game.manager.InputHandler;

public interface GameInitializer {
    void initialize();
    InputHandler getInputHandler();
    GameLoop getGameLoop();
}

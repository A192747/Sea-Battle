package com.example.game.manager;

public interface InputHandler {
    int readLetterInput();
    int readIntegerInput();
    String readPlayerNameInput();
    void close();
}

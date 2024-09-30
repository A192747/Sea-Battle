package com.example.game.manager;

public interface InputHandler {
    static InputHandler getInstance() {
        return null;
    }
    int readLetterInput();
    int readIntegerInput();
    String readPlayerNameInput();
    void close();
}

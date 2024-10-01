package com.example.game.manager;

public interface InputHandler {
    void init(int rowsCount, int columnsCount);
    void readAnyInput();
    int readLetterInput();
    int readIntegerInput();
    String readPlayerNameInput();
    void close();
}

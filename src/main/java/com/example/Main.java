package com.example;

import com.example.game.Game;
import com.example.game.init.GameInitializerImpl;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new GameInitializerImpl(10, 10));
        game.start();
    }
}
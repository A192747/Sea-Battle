package com.example.game.manager;

import java.util.Scanner;

import static com.example.game.Game.fieldSize;

public class InputHandlerImpl implements InputHandler {
    private static Scanner scanner;

    private static InputHandlerImpl instance;

    public static InputHandler getInstance() {
        if (instance == null) {
            instance = new InputHandlerImpl();
        }
        return instance;
    }

    private InputHandlerImpl() {
        scanner = new Scanner(System.in);
    }

    public int readLetterInput() {
        int result = 0;
        while (true) {
            try {
                String string = scanner.next();
                if(string.length() == 1) {
                    result = string.charAt(0) - 'A';
                    if(result > fieldSize - 1 || result < 0) {
                        throw new IllegalArgumentException("Разрешено использовать только буквы в диапазоне [A:" + (char)('A' + fieldSize - 1) +  "].\n" +
                                "Введите корректное значение: ");
                    }
                }
                break;
            } catch (IllegalArgumentException e) {
                System.err.print(e.getMessage());
            }
        }
        return result;
    }

    public int readIntegerInput() {
        int result;
        while (true) {
            try {
                String string = scanner.next();
                result = Integer.parseInt(string);
                break;
            } catch (NumberFormatException e) {
                System.err.print("Не удалось преобразовать значение в число! Введите корректное значение: ");
            }
        }
        return result;
    }

    public String readPlayerNameInput() {
        return scanner.next();
    }

    public void close() {
        scanner.close();
    }
}

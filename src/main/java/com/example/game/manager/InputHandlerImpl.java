package com.example.game.manager;

import java.util.Scanner;


public class InputHandlerImpl implements InputHandler {
    private static Scanner scanner;
    private static int rowsCount;
    private static int columnsCount;
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

    public void init(int rowsCount, int columnsCount) {
        InputHandlerImpl.rowsCount = rowsCount;
        InputHandlerImpl.columnsCount = columnsCount;
    }

    public int readLetterInput() {
        while (true) {
            String string = scanner.next();
            if(string.length() == 1 && string.charAt(0) <= (char) ('A' + rowsCount - 1) && string.charAt(0) >= 'A') {
                return string.charAt(0) - 'A';

            } else {
                System.out.print("Разрешено использовать только буквы в диапазоне [A:" + (char)('A' + rowsCount - 1) +  "].\n" +
                        "Введите корректное значение: ");
            }
        }
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

    public void readAnyInput() {
        System.out.println("Введите любой символ чтобы продолжить...");
        scanner.next();
    }

    public void close() {
        scanner.close();
    }
}

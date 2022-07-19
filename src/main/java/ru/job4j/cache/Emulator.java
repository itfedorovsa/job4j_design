package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    private static final String UPLOAD_TO_CACHE = "1";
    private static final String GET_FROM_CACHE = "2";
    private static final String EXIT = "0";
    private static final String DIRECTORY = "C:\\projects\\job4j_design";
    private static final DirFileCache DFC = new DirFileCache(DIRECTORY);
    private static final String ENTRY_TEXT = """
            Enter the directory first (relative). Then 1 - Upload to cache, 2 - Get from cache, 0 - Exit
            Enter your choice:
            """;

    public static void main(String[] args) {
        boolean wrongInsert;
        String path = "";
        Scanner scanner = new Scanner(System.in);
        do {
            wrongInsert = false;
            System.out.println(ENTRY_TEXT);
            String input = scanner.nextLine();
            switch (input) {
                case UPLOAD_TO_CACHE:
                    DFC.put(path, DFC.load(path));
                    break;
                case GET_FROM_CACHE:
                    System.out.println(DFC.get(path));
                    break;
                case EXIT:
                    wrongInsert = true;
                    break;
                default:
                    path = input;
                    break;
            }
        } while (!wrongInsert);
    }
}

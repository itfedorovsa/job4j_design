package ru.job4j.cache;

import java.util.Scanner;
import java.util.StringJoiner;

public class Emulator {
    private static final String ENTER_THE_DIR = "1";
    private static final String ENTER_THE_FILE = "2";
    private static final String GET_FROM_CACHE = "3";
    private static final String EXIT = "0";
    private static String directory;
    private static String file;
    private static DirFileCache dfc;
    private static final String ENTRY_TEXT = """
            1 - Enter the directory, 2 - Enter the file to upload, 3 - Get from cache, 0 - Exit
            Enter your choice:
            """;

    public static void main(String[] args) {
        boolean wrongInsert;
        Scanner scanner = new Scanner(System.in);
        do {
            wrongInsert = false;
            System.out.println(ENTRY_TEXT);
            String input = scanner.nextLine();
            switch (input) {
                case ENTER_THE_DIR:
                    System.out.println("Enter the directory:");
                    directory = scanner.nextLine();
                    dfc = new DirFileCache(directory);
                    break;
                case ENTER_THE_FILE:
                    System.out.println("Enter the file to upload:");
                    file = scanner.nextLine();
                    break;
                case GET_FROM_CACHE:
                    System.out.println(dfc.get(file));
                    break;
                case EXIT:
                    wrongInsert = true;
                    break;
                default:
                    break;
            }
        } while (!wrongInsert);
    }
}

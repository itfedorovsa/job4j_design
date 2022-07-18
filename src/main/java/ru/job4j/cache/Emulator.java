package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        boolean wrongInsert;
        String path = "";
        Scanner scanner = new Scanner(System.in);
        do {
            wrongInsert = false;
            System.out.println("Enter the directory first (relative). Then 1 - Upload to cache, 2 - Get from cache, 0 - Exit");
            System.out.println("Enter your choice: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    DirFileCache dfc = new DirFileCache(path);
                    dfc.put(path, dfc.load(path));
                    break;
                case "2":
                    DirFileCache dirfc = new DirFileCache(path);
                    System.out.println(dirfc.get(path));
                    break;
                case "0":
                    wrongInsert = true;
                    break;
                default:
                    path = input;
                    break;
            }
        } while (!wrongInsert);
    }
}

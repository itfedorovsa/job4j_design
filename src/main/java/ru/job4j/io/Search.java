package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (validation(args)) {
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    private static boolean validation(String[] args) {
        File path = new File(args[0]);
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Invalid amount of arguments. Required: 2. Use [0, 1]: java -jar search.jar ROOT_FOLDER .js");
        }
        if (!path.exists()) {
            throw new IllegalArgumentException(
                    "The path is not exist");
        }
        if (!path.isDirectory()) {
            throw new IllegalArgumentException(
                    "The path is not a directory.");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(
                    "Incorreсt second parameter form. Use \".js\"");
        }
        return true;
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}

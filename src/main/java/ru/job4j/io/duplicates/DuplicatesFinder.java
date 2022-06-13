package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        visitor.map.keySet().stream()
                    .filter(f -> visitor.map.get(f).size() > 1)
                    .peek(f -> System.out.printf("Name of duplicate file: %s; Size: %s%n", f.getName(), f.getSize()))
                    .forEach(f -> visitor.map.get(f).forEach(System.out::println));

        }
}

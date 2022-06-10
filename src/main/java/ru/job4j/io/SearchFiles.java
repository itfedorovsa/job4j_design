package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private final Predicate<Path> pred;
    List<Path> paths = new ArrayList<>();

    public SearchFiles(final Predicate<Path> pred) {
        this.pred = pred;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (pred.test(file.toAbsolutePath())) {
            paths.add(file.getFileName());
        }
        return CONTINUE;
    }

    public List<Path> getPaths() {
        return paths;
    }
}

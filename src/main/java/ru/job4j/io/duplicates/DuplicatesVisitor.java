package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(attrs.size(), file.toFile().getName());
        Path path = file.toAbsolutePath();
        if (!map.containsKey(property)) {
            List<Path> paths = new ArrayList<>();
            paths.add(path);
            map.put(property, paths);
        } else {
            map.get(property).add(path);
        }
        return super.visitFile(file, attrs);
    }
}

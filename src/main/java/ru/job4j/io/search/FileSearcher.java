package ru.job4j.io.search;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileSearcher {

    public List<Path> searchFile(ArgsName argsName) throws IOException {
        Path path = Paths.get(argsName.get("d"));
        return Search.search(path, condition(argsName));
    }

    public Predicate<Path> condition(ArgsName argsName) {
        String name = argsName.get("n");
        String type = argsName.get("t");
        Predicate<Path> pred = null;
        if ("name".equals(type)) {
            pred = p -> p.toFile().getName().equals(name);
        } else if ("mask".equals(type)) {
            pred =  p -> p.toFile().getName().contains(name);
        } else if ("regex".equals(type)) {
            pred = p -> Pattern.compile(name).matcher(p.toFile().getName()).find();
        }
        return pred;
    }

    private void validation(ArgsName argsName) {
        File path = new File(argsName.get("d"));
        String type = argsName.get("t");
        if (argsName.argsSize() != 4) {
            throw new IllegalArgumentException(
                    "Wrong amount of arguments. Use: -d=PathFrom -n=FileToFind.EXP -t=TypeOfSearch -o=PathTo");
        }
        if (!path.exists()) {
            throw new IllegalArgumentException("PathFrom is not exist!");
        }
        if (!path.isDirectory()) {
            throw new IllegalArgumentException("PathFrom must be a directory!");
        }
        if (!"name".equals(type) && !"mask".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("Type must be name/mask/regex!");
        }
    }

    public void write(List<Path> list, ArgsName argsName) {
        File target = new File(argsName.get("o"));
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path el : list) {
                writer.println(el.toFile().getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        FileSearcher searcher = new FileSearcher();
        searcher.validation(argsName);
        List<Path> files = searcher.searchFile(argsName);
        searcher.write(files, argsName);
    }
}

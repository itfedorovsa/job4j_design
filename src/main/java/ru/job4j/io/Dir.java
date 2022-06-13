package ru.job4j.io;

import java.io.File;
import java.util.StringJoiner;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Use java -jar dir.jar ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size: %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            StringJoiner joiner = new StringJoiner("; ");
            System.out.println(joiner.add(String.format("name: %s", subfile.getName())).add(String.format("size: %s", subfile.length())));
        }
    }
}

package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void validation(ArgsName argsName, File path) {
        if (argsName.argsSize() != 4) {
            throw new IllegalArgumentException("Incorrect quantity of arguments. Required: 4");
        }
        if (!path.exists()) {
            throw new IllegalArgumentException("Path is not exist!");
        }
        if (path.isDirectory()) {
            throw new IllegalArgumentException("Path must be a file!");
        }
    }

    public static void handle(ArgsName argsName) throws IOException {
        File path = new File(argsName.get("path"));
        validation(argsName, path);
        String out = argsName.get("out");
        if ("stdout".equals(out)) {
            List<String> lines = transform(argsName, path);
            for (String line : lines) {
                System.out.println(line);
            }
        } else {
            File outPath = new File(argsName.get("out"));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outPath))) {
                List<String> lines = transform(argsName, path);
                for (String line : lines) {
                    writer.write(line);
                    writer.write(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> transform(ArgsName argsName, File path) throws IOException {
        List<String> result = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        String[] filter = argsName.get("filter").split(",");
        String[] fields;
        StringJoiner line = new StringJoiner(delimiter);
        List<Integer> indexes = new ArrayList<>();
        try (var scanner = new Scanner(path)) {
            fields = scanner.nextLine().split(";");
            for (int ind = 0; ind < fields.length; ind++) {
                for (int i = 0; i < filter.length; i++) {
                    if (filter[i].equals(fields[ind])) {
                        line.add(fields[ind]);
                        indexes.add(ind);
                        break;
                    }
                }
            }
            result.add(line.toString());
            while (scanner.hasNextLine()) {
                fields = scanner.nextLine().split(";");
                line = new StringJoiner(delimiter);
                for (Integer index : indexes) {
                    line.add(fields[index]);
                }
                result.add(line.toString());
                }
            }
        return result;
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(new String[] {"-path=src/data/source.csv",
                "-delimiter=;",  "-out=stdout", "-filter=brand,model,engine.vol"});
        handle(argsName);
    }
}


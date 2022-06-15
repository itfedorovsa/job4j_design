package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                for (Path source : sources) {
                    zip.putNextEntry(new ZipEntry(source.toFile().getAbsolutePath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile().getAbsolutePath()))) {
                        zip.write(out.readAllBytes());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validation(String[] args, ArgsName argsMap) {
        File path = new File(argsMap.get("d"));
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrent arguments amount. Required: 3. Use: -d=\"PATH\" -e=.EXTENSION -o=*.zip");
        }
        if (!path.exists()) {
            throw new IllegalArgumentException("Path is not exist!");
        }
        if (!path.isDirectory()) {
            throw new IllegalArgumentException("Path is not a directory!");
        }
        if (!argsMap.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Incorrect second parameter form. Use: \"-e=.EXTENSION\"");
        }
        if (!argsMap.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Incorrect third parameter form. Use: \"-o=NAME.zip\"");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName map = ArgsName.of(args);
        zip.validation(args, map);
        Path path = Paths.get(map.get("d"));
        List<Path> sources = Search.search(path, p -> !p.toFile().getName().endsWith(map.get("e")));
        File target = Paths.get(map.get("o")).toFile();
        zip.packFiles(sources, target);
    }
}

package ru.job4j.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CSVReaderTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenFilterTwoColumns() throws IOException {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test
    public void whenFilter3Columns() throws IOException {
        String data = String.join(
                System.lineSeparator(),
                "year;brand;model;colour;engine.vol",
                "1995;Toyota;Supra;black;3.0",
                "2006;BMW;M5 E60;gray;5.0",
                "1994;Porsche;911 993;green;3.6",
                "2005;Mercedes-Benz;SL;silver;5.5"
        ).concat(System.lineSeparator());
        File source = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {"-path=" + source.getAbsolutePath(),
                "-delimiter=;",  "-out=" + target.getAbsolutePath(), "-filter=brand,model,engine.vol"});
        Files.writeString(source.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "brand;model;engine.vol",
                "Toyota;Supra;3.0",
                "BMW;M5 E60;5.0",
                "Porsche;911 993;3.6",
                "Mercedes-Benz;SL;5.5"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPathIsNotExistThenIllegalArgumentException() throws IOException {
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {"-path=src/data/wrong.csv",
                "-delimiter=;",  "-out=" + target.getAbsolutePath(), "-filter=brand,model,engine.vol"});
        File path = new File(argsName.get("path"));
        CSVReader.validation(argsName, path);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenIncorrectAmountOfArgsThenIllegalArgumentException() throws IOException {
        File source = temporaryFolder.newFile("source.csv");
        ArgsName argsName = ArgsName.of(new String[] {"-path=" + source.getAbsolutePath(),
                "-delimiter=;", "-filter=brand,model,engine.vol"});
        File path = new File(argsName.get("path"));
        CSVReader.validation(argsName, path);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPathIsADirectoryThenIllegalArgumentException() throws IOException {
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {"-path=src/data",
                "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=brand,model,engine.vol"});
        File path = new File(argsName.get("path"));
        CSVReader.validation(argsName, path);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenIncorrectKeyThenIllegalArgumentException() throws IOException {
        File source = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {"-path=" + source.getAbsolutePath(),
                "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter="});
        File path = new File(argsName.get("path"));
        CSVReader.validation(argsName, path);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenOutputPathIsNotExistThenIllegalArgumentException() throws IOException {
        File source = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {"-path=" + source.getAbsolutePath(),
                "-delimiter=;", "-out=src/data/wrong.csv", "-filter="});
        File path = new File(argsName.get("path"));
        CSVReader.validation(argsName, path);
    }
}
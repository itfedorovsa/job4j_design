package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines()
                    .filter(f -> !f.startsWith("#") && !f.startsWith(" #"))
                    .filter(f -> !"".equals(f))
                    .filter(f -> {
                            if (f.startsWith("=") || f.endsWith("=") && f.indexOf("=") == f.lastIndexOf("=")) {
                                throw new IllegalArgumentException();
                            }
                            return true;
                     })
                    .map(m -> m.split("=", 2))
                    .forEach(f -> values.put(f[0], f[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
        Config config = new Config("app.properties");
        config.load();
        for (String val : config.values.keySet()) {
            System.out.println("Key: " + val + " , Value: " + config.values.get(val));
        }
    }
}

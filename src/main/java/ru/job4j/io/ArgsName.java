package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key is not found");
        }
        return values.get(key);
    }

    private static boolean validation(String str) {
        str.trim();
        if (!str.startsWith("-") || !str.contains("=") || str.startsWith("-=") || str.startsWith("=")
            || str.endsWith("=") && str.indexOf("=") == str.lastIndexOf("=")) {
            throw new IllegalArgumentException("Incorrect pair. Form: \"-Key=Value\"");
        }
        return true;
    }

    private void parse(String[] args) {
            Arrays.stream(args)
                    .filter(ArgsName::validation)
                    .map(m -> m.substring(1))
                    .map(m -> m.split("=", 2))
                    .forEach(f -> values.put(f[0], f[1])
            );
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Invalid arguments. Use [0 - 1]: java -jar argsname.jar Xmx encoding");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

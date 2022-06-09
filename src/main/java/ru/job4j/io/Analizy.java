package ru.job4j.io;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Analizy {
    public void unavailable(String source, String target) {
        Map<String, String> checks = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines()
                    .map(m -> m.split(" "))
                    .forEach(f -> checks.put(f[1], f[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder builder = new StringBuilder();
            checks.entrySet()
                    .stream()
                    .filter(f -> "200".equals(f.getValue()) && builder.length() != 0
                            || "300".equals(f.getValue()) && builder.length() != 0
                            || "400".equals(f.getValue()) && builder.length() == 0
                            || "500".equals(f.getValue()) && builder.length() == 0)
                    .forEach(m -> {
                        if ("400".equals(m.getValue()) || "500".equals(m.getValue())) {
                            builder.append(m.getKey());
                            builder.append(";");
                        } else {
                            builder.append(m.getKey());
                            builder.append(";");
                            writer.println(builder);
                            builder.delete(0, 18);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        String source = "server.log";
        String target = "unavailable.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
    }
}
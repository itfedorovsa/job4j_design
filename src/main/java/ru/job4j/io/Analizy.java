package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            reader.lines()
                    .map(m -> m.split(" "))
                    .filter(f -> "200".equals(f[0]) && builder.length() != 0
                            || "300".equals(f[0]) && builder.length() != 0
                            || "400".equals(f[0]) && builder.length() == 0
                            || "500".equals(f[0]) && builder.length() == 0)
                    .forEach(m -> {
                            if ("400".equals(m[0]) || "500".equals(m[0])) {
                                builder.append(m[1]);
                                builder.append(";");
                            } else {
                                builder.append(m[1]);
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
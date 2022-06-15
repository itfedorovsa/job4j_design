package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private static final String OUT = "finish";
    private static final String STOP = "stop";
    private static final String CONTINUE = "continue";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        String phrase;
        boolean isStopped = false;
        System.out.println("Welcome to the Bot! Commands: stop, continue, finish.");
        System.out.println("Ask your question:");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                phrase = reader.readLine();
                saveLog(phrase);
                if (STOP.equals(phrase)) {
                    isStopped = true;
                }
                if (CONTINUE.equals(phrase)) {
                    isStopped = false;
                }
                if (!isStopped && !OUT.equals(phrase)) {
                    String answer = readPhrases().get((int) (Math.random() * readPhrases().size()));
                    System.out.println(answer);
                    saveLog(answer);
                }
                } while (!OUT.equals(phrase));
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> answ = new ArrayList<>();
        try (BufferedReader readAnsw = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            readAnsw.lines().forEach(answ::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answ;
    }

    private void saveLog(String log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
                writer.println(log);
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/log.txt", "./src/data/answers.txt");
        cc.run();
    }
}

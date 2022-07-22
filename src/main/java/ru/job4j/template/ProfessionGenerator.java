package ru.job4j.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class ProfessionGenerator implements Generator {
    @Override
    public String produce(String template, Map<String, String> args) {
        return null;
    }

    public static void main(String[] args) {
        Generator gen = new ProfessionGenerator();
        Map<String, String> map = Map.of("name", "doctor", "subject", "you");
        String expected = "name, subject";
        StringJoiner keyJoiner = new StringJoiner(", ");
        for (String k : map.keySet()) {
            keyJoiner.add(k);
        }
        String rsl = keyJoiner.toString();
        System.out.println(rsl);
    }

}

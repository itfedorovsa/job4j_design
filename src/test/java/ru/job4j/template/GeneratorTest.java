package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Map;

public class GeneratorTest {

    @Ignore
    @Test
    public void whenAllKeysMatches() {
        Generator gen = new ProfessionGenerator();
        Map<String, String> map = Map.of("name", "doctor", "subject", "you");
        String template = "I am a ${name}, Who are ${subject}? ";
        String rsl = "I am a doctor, Who are you? ";
        assertThat(gen.produce(template, map), is(rsl));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenTemplateContainsMoreKeysThanMapThenException() {
        Generator gen = new ProfessionGenerator();
        Map<String, String> map = Map.of("name", "doctor", "subject", "you");
        String template = "I am a ${name}, Who are ${subject}? , What is your ${age}?";
        gen.produce(template, map);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenMapContainsMoreKeysThanTemplateThenException() {
        Generator gen = new ProfessionGenerator();
        Map<String, String> map = Map.of("name", "doctor", "subject", "you", "age", "33");
        String template = "I am a ${name}, Who are ${subject}? ";
        gen.produce(template, map);
    }
}
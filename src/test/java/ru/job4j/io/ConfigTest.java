package ru.job4j.io;

import org.junit.Test;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Bob"));
        assertThat(config.value("surname"), is("Fisher"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Bob"));
        assertThat(config.value("surname"), is("Fisher"));
    }

    @Test
    public void whenPairWithEmptyLines() {
        String path = "./data/pair_with_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Bob"));
        assertThat(config.value("surname"), is("Fisher"));
    }

    @Test
    public void whenPairWithEmptyLinesAndComments() {
        String path = "./data/pair_with_comments_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Bob"));
        assertThat(config.value("surname"), is("Fisher"));
    }

    @Test
    public void whenPairAndDoubleEqualSignAtTheEnd() {
        String path = "./data/pair_and_double_equal_sign_at_the_end.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Bob="));
        assertThat(config.value("surname"), is("Fisher=1"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNotPairAtStart() {
        String path = "./data/not_pair_at_start.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNotPairAtEnd() {
        String path = "./data/not_pair_at_end.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenOnlyEqualSymbol() {
        String path = "./data/not_pair_only_equal_symbol.properties";
        Config config = new Config(path);
        config.load();
    }
}
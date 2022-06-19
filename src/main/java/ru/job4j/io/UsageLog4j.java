package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        String name = "Sergey Fedorov";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        String bb = "Byte";
        byte b = 1;
        LOG.debug("Type: {}, value: {}", bb, b);
        String ss = "Short";
        short s = 2;
        LOG.debug("Type: {}, value: {}", ss, s);
        String ii = "Int";
        int i = 3;
        LOG.debug("Type: {}, value: {}", ii, i);
        String ll = "Long";
        long l = 4;
        LOG.debug("Type: {}, value: {}", ll, l);
        String ff = "Float";
        float f = 5.0f;
        LOG.debug("Type: {}, value: {}", ff, f);
        String dd = "Double";
        double d = 6.0d;
        LOG.debug("Type: {}, value: {}", dd, d);
        String cc = "Char";
        char c = '7';
        LOG.debug("Type: {}, value: {}", cc, c);
        String nn = "Boolean";
        boolean n = true;
        LOG.debug("Type: {}, value: {}", nn, n);
    }
}

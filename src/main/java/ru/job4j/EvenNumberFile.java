package ru.job4j;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder nums = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                nums.append((char) read);
            }
            String[] lines = nums.toString().split(System.lineSeparator());
            for (String line : lines) {
                int number = Integer.parseInt(line);
                if (number % 2 == 0) {
                    System.out.println(number + " is even");
                } else {
                    System.out.println(number + " is odd");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ru.job4j;

public class NumToBinary {
    public static void main(String[] args) {
        int num = 123456789;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        System.out.println(sb.reverse());
    }
}


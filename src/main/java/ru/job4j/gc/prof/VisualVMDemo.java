package ru.job4j.gc.prof;

import java.util.Random;
import java.util.Scanner;

public class VisualVMDemo {
    public static void main(String[] args) {
        Random random = new Random();
        Data rand = new RandomArray(random);
        rand.insert(250000);
        BubbleSort bub = new BubbleSort();
        InsertSort ins = new InsertSort();
        MergeSort mer = new MergeSort();
        boolean wrongInsert;
        Scanner scanner = new Scanner(System.in);
        do {
            wrongInsert = false;
            System.out.println("1 - BubbleSort, 2 - InsertSort, 3 - MergeSort, Any - Exit");
            System.out.println("Enter your choice: ");
            switch (scanner.nextInt()) {
                case 1:
                    bub.sort(rand);
                    break;
                case 2:
                    ins.sort(rand);
                    break;
                case 3:
                    mer.sort(rand);
                    break;
                default:
                    wrongInsert = true;
                    break;
            }
        } while (!wrongInsert);
    }
}

package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class MenuDemo {
    private static final String ENTER_THE_TASK = "1";
    private static final String ENTER_THE_SUBTASK = "2";
    private static final String PRINT_TASK_LIST = "3";
    private static final String EXIT = "0";
    private static SimpleMenu menu = new SimpleMenu();
    public static final ActionDelegate STUB_ACTION = System.out::println;
    private static final String ENTRY_TEXT = """
            1 - Enter the task, 2 - Enter the subtask, 3 - Print task list, 0 - Exit
            Enter your choice:
            """;

    public static void main(String[] args) {
        boolean wrongInsert;
        SimpleMenuPrinter printer = new SimpleMenuPrinter();
        Scanner scanner = new Scanner(System.in);
        do {
            wrongInsert = false;
            System.out.println(ENTRY_TEXT);
            String input = scanner.nextLine();
            switch (input) {
                case ENTER_THE_TASK:
                    System.out.println("Enter the task:");
                    String task = scanner.nextLine();
                    menu.add(Menu.ROOT, task, STUB_ACTION);
                    break;
                case ENTER_THE_SUBTASK:
                    System.out.println("Enter the main task before:");
                    String mainTask = scanner.nextLine();
                    System.out.println("Enter the subtask:");
                    String subTask = scanner.nextLine();
                    menu.add(mainTask, subTask, STUB_ACTION);
                    break;
                case PRINT_TASK_LIST:
                    printer.print(menu);
                    break;
                case EXIT:
                    wrongInsert = true;
                    break;
                default:
                    break;
            }
        } while (!wrongInsert);
    }
}

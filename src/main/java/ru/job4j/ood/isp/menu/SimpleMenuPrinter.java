package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    StringBuilder builder = new StringBuilder();

    @Override
    public void print(Menu menu) {
        menu.forEach(f -> {
                    String[] split = f.getNumber().split("\\.");
                    String filling = "\t";
                    builder.append(filling.repeat(split.length - 1))
                            .append(f.getNumber())
                            .append(f.getName())
                            .append(System.lineSeparator());
                    }
                );
        System.out.println(builder);
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    public static void main(String[] args) {
        ActionDelegate stubAction = System.out::println;
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Go to the store", stubAction);
        menu.add(Menu.ROOT, "Feed the dog", stubAction);
        menu.add("Go to the store", "Buy drinks", stubAction);
        menu.add("Buy drinks", "Buy juice", stubAction);
        SimpleMenuPrinter s = new SimpleMenuPrinter();
        s.print(menu);
    }
}

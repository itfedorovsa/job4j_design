package ru.job4j.ood.lsp.lspfailuredemo;

public class Account {
    protected float money;
    protected boolean permission;
    protected int age;

    public Account(float money) {
        this.money = money;
    }

    private void validate(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("You must be at least 18 years old!");
        }
    }

    public void transfer() {
        validate(age);
        if (money <= 0) {
            throw new IllegalArgumentException("You don't have enough money!");
        }
        if (!permission) {
            throw new IllegalArgumentException("You must have a permission!");
        }
    }
}

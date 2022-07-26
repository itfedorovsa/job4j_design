package ru.job4j.ood.lsp.lspfailuredemo;

public class UserAccount extends Account {

    public UserAccount(float money) {
        super(money);
    }

    @Override
    public void transfer() {
        if (money < 100) {
            throw new IllegalArgumentException("You must always have at least 100 on your account!");
        }
    }
}

package ru.job4j.serialization.json;

import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Customer {
    private final boolean revisit;
    private final String name;
    private final int purchasesNumber;
    private final Contact phoneNumber;
    private final String[] purchases;

    public Customer(boolean revisit, String name, int purchasesNumber, Contact phoneNumber, String[] purchases) {
        this.revisit = revisit;
        this.name = name;
        this.purchasesNumber = purchasesNumber;
        this.phoneNumber = phoneNumber;
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "Customer{"
                + "revisit=" + revisit
                + ", name='" + name + '\''
                + ", purchasesNumber=" + purchasesNumber
                + ", phoneNumber=" + phoneNumber
                + ", purchases=" + Arrays.toString(purchases)
                + '}';
    }

    public static void main(String[] args) {
        String[] purchases = {"TV", "Table", "Shovel"};
        Customer customer = new Customer(true, "Bob", 3, new Contact("111-333"), purchases);
        Gson gson = new GsonBuilder().create();
        String to = gson.toJson(customer);
        System.out.println(gson.toJson(customer));
        Customer from = gson.fromJson(to, Customer.class);
        System.out.println(from);
    }
}

package ru.job4j.serialization.json;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public boolean getRevisit() {
        return revisit;
    }

    public String getName() {
        return name;
    }

    public int getPurchasesNumber() {
        return purchasesNumber;
    }

    public Contact getPhoneNumber() {
        return phoneNumber;
    }

    public String[] getPurchases() {
        return purchases;
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
        JSONObject objectContact = new JSONObject("{\"phone\" : \"111-333\"}");
        List<String> purchasesList = List.of("TV", "Table", "Shovel");
        JSONArray purchasesArray = new JSONArray(purchasesList);
        JSONObject custom = new JSONObject();
        custom.put("revisit", customer.getRevisit());
        custom.put("name", customer.getName());
        custom.put("purchasesNumber", customer.getPurchasesNumber());
        custom.put("phoneNumber", objectContact);
        custom.put("purchases", purchasesArray);
        System.out.println(custom);
    }
}

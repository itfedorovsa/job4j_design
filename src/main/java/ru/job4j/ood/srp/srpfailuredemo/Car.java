package ru.job4j.ood.srp.srpfailuredemo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Car implements Transport {
    private String brand;
    private String model;
    private int year;
    private String colour;
    private String created;

    public Car(String brand, String model, int year, String colour, String created) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.colour = colour;
        this.created = created;
    }

    public Car createCar() {
        LocalDateTime created = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        String createdFormat = created.format(formatter);
        return new Car("Toyota", "Supra", 1999, "Black", createdFormat);
    }

    public String createSpec(Car car) {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(car.brand).add(car.model).add(String.valueOf(car.year)).add(car.colour).add(car.created);
        return joiner.toString();
    }

    @Override
    public String drive() {
        return "Drive somehow";
    }

    @Override
    public void printSpecifications(String spec) {
        System.out.println(spec);
    }
}

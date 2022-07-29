package ru.job4j.ood.dip.dipfailuredemo;

public class Car {
    private int id;
    private String model;
    private String licensePlate;

    public Car(int id, String model, String licensePlate) {
        this.id = id;
        this.model = model;
        this.licensePlate = licensePlate;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}


package ru.job4j.ood.lsp.parking;

public class Car extends Vehicle {
    public static final int CAR_SIZE = 1;
    public static final String CAR_TYPE = "Car";

    public Car(String type, int size, String licensePlate) {
        super(type, size, licensePlate);
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public int getSize() {
        return super.getSize();
    }

    @Override
    public String getLicensePlate() {
        return super.getLicensePlate();
    }

}

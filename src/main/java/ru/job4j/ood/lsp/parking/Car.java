package ru.job4j.ood.lsp.parking;

public class Car extends Vehicle {
    private static final int CAR_SIZE = 1;
    private static final String CAR_TYPE = "Car";

    public Car(String type, int size, String licensePlate) {
        super(CAR_TYPE, CAR_SIZE, licensePlate);
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

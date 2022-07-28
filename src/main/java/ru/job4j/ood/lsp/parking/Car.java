package ru.job4j.ood.lsp.parking;

public class Car extends Vehicle {
    public static final int CAR_SIZE = 1;

    public Car(String type, String licensePlate) {
        super(type, licensePlate);
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public int getSize() {
        return CAR_SIZE;
    }

    @Override
    public String getLicensePlate() {
        return super.getLicensePlate();
    }

}

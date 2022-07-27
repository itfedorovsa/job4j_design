package ru.job4j.ood.lsp.parking;

public class Truck extends Vehicle {
    public static final int TRUCK_SIZE = 2;
    public static final String TRUCK_TYPE = "Truck";

    public Truck(String type, int size, String licensePlate) {
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

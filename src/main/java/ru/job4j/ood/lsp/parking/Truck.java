package ru.job4j.ood.lsp.parking;

public class Truck extends Vehicle {
    private static final int TRUCK_SIZE = 2;
    private static final String TRUCK_TYPE = "Truck";

    public Truck(String type, int size, String licensePlate) {
        super(TRUCK_TYPE, TRUCK_SIZE, licensePlate);
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

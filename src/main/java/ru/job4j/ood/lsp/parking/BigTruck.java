package ru.job4j.ood.lsp.parking;

public class BigTruck extends Vehicle {
    public static final int BIG_TRUCK_SIZE = 3;
    public static final String BIG_TRUCK_TYPE = "Big Truck";

    public BigTruck(String type, int size, String licensePlate) {
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

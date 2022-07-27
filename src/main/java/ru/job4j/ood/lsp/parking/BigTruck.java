package ru.job4j.ood.lsp.parking;

public class BigTruck extends Vehicle {
    private static final int BIG_TRUCK_SIZE = 3;
    private static final String BIG_TRUCK_TYPE = "Big Truck";

    public BigTruck(String type, int size, String licensePlate) {
        super(BIG_TRUCK_TYPE, BIG_TRUCK_SIZE, licensePlate);
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

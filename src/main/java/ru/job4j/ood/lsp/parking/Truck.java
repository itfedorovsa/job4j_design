package ru.job4j.ood.lsp.parking;

public class Truck extends Vehicle {

    public Truck(String type, int size, String licensePlate) {
        super(type, size, licensePlate);
        if (size <= Car.CAR_SIZE) {
            throw new IllegalArgumentException("Truck size must be more than 1");
        }

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

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

package ru.job4j.ood.lsp.parking;

public abstract class Vehicle {
    private final String type;
    private int size;
    private final String licensePlate;

    public Vehicle(String type, int size, String licensePlate) {
        this.type = type;
        this.size = size;
        this.licensePlate = licensePlate;
    }

    public Vehicle(String type, String licensePlate) {
        this.type = type;
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public String toString() {
        return "Vehicle{"
                + "type='" + type + '\''
                + ", size=" + size
                + ", licensePlate='" + licensePlate + '\''
                + '}';
    }

}

package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {
    private int[] carLots;
    private int[] truckLots;
    private final List<Vehicle> vehiclesList = new ArrayList<>();

    @Override
    public boolean park(Vehicle vehicle) {
        return false;
    }

    @Override
    public List<Vehicle> getParkedVehicles() {
        return null;
    }

    @Override
    public Vehicle getVehicle(String licensePlate) {
        return null;
    }

    @Override
    public boolean check(Vehicle vehicle) {
        return false;
    }
}

package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {
    private int carLots;
    private int truckLots;
    private static final int EMPTY_PARKING = 0;
    private static final int ONE_TRUCK_LOT = 1;
    private final List<Vehicle> vehiclesList = new ArrayList<>();

    public SimpleParking(int carLots, int truckLots) {
        this.carLots = carLots;
        this.truckLots = truckLots;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        int size = vehicle.getSize();
        if (size < 1) {
            throw new IllegalArgumentException("Every vehicle must have a certain size!");
        } else if (size == 1 && carLots > EMPTY_PARKING) {
            vehiclesList.add(vehicle);
            carLots -= size;
            return true;
        } else if (size > 1 && truckLots > EMPTY_PARKING) {
            vehiclesList.add(vehicle);
            truckLots -= ONE_TRUCK_LOT;
            return true;
        } else if (size > 1 && carLots >= size) {
            vehiclesList.add(vehicle);
            truckLots -= size;
            return true;
        }
        return false;
    }

    @Override
    public List<Vehicle> getParkedVehicles() {
        return new ArrayList<>(vehiclesList);
    }

    @Override
    public Vehicle getVehicle(String licensePlate) {
        for (Vehicle v : vehiclesList) {
            if (v.getLicensePlate().equals(licensePlate)) {
                return v;
            }
        }
        return null;
    }

}

package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.parking.Car.CAR_SIZE;

public class SimpleParking implements Parking {
    private int carLots;
    private int truckLots;
    private static final int EMPTY_PARKING = 0;
    private static final int ONE_TRUCK_LOT = 1;
    private final List<Vehicle> carsList = new ArrayList<>();
    private final List<Vehicle> trucksList = new ArrayList<>();

    public SimpleParking(int carLots, int truckLots) {
        this.carLots = carLots;
        this.truckLots = truckLots;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        int size = vehicle.getSize();
        /*for (Vehicle v : carsList) {
            if (v.getLicensePlate().equals(vehicle.getLicensePlate())) {
                return false;
            }
        }*/
        if (size < CAR_SIZE) {
            throw new IllegalArgumentException("Every vehicle must have a certain size!");
        } else if (size == CAR_SIZE && carLots > EMPTY_PARKING) {
            carsList.add(vehicle);
            carLots -= size;
            return true;
        } else if (size > CAR_SIZE && truckLots > EMPTY_PARKING) {
            carsList.add(vehicle);
            truckLots -= ONE_TRUCK_LOT;
            return true;
        } else if (size > CAR_SIZE && carLots >= size) {
            carsList.add(vehicle);
            truckLots -= size;
            return true;
        }
        return false;
    }

    @Override
    public List<Vehicle> getParkedVehicles() {
        return new ArrayList<>(carsList);
    }

    @Override
    public Vehicle getVehicle(Vehicle vehicle) {
        if (vehicle.getSize() == 1) {
            return findVehicle(carsList, vehicle);
        }
        return findVehicle(trucksList, vehicle);
    }

    protected static Vehicle findVehicle(List<Vehicle> list, Vehicle vehicle) {
        for (Vehicle v : list) {
            if (v.getLicensePlate().equals(vehicle.getLicensePlate())) {
                return v;
            }
        }
        return null;
    }

}

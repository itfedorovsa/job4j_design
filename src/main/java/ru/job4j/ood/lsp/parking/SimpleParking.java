package ru.job4j.ood.lsp.parking;

import java.util.HashSet;
import java.util.Set;

import static ru.job4j.ood.lsp.parking.Car.CAR_SIZE;

public class SimpleParking implements Parking {
    private int carLots;
    private int truckLots;
    private static final int EMPTY_PARKING = 0;
    private static final int ONE_TRUCK_LOT = 1;
    private final Set<Vehicle> carsList = new HashSet<>();
    private final Set<Vehicle> trucksList = new HashSet<>();

    public SimpleParking(int carLots, int truckLots) {
        this.carLots = carLots;
        this.truckLots = truckLots;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        boolean isParked = false;
        int size = vehicle.getSize();
        if (carsList.contains(vehicle) || trucksList.contains(vehicle)) {
            return false;
        }
        if (size == CAR_SIZE && carLots > EMPTY_PARKING) {
            carsList.add(vehicle);
            carLots -= size;
            isParked = true;
        } else if (size > CAR_SIZE && truckLots > EMPTY_PARKING) {
            trucksList.add(vehicle);
            truckLots -= ONE_TRUCK_LOT;
            isParked = true;
        } else if (size > CAR_SIZE && carLots >= size) {
            trucksList.add(vehicle);
            carLots -= size;
            isParked = true;
        }
        return isParked;
    }

    @Override
    public Set<Vehicle> getParkedCars() {
        return Set.copyOf(carsList);
    }

    @Override
    public Set<Vehicle> getParkedTrucks() {
        return Set.copyOf(trucksList);
    }

    @Override
    public Vehicle getVehicle(Vehicle vehicle) {
        Vehicle v = null;
        if (vehicle.getSize() == 1) {
            vehicle = findVehicle(carsList, vehicle);
        }
        vehicle = findVehicle(trucksList, vehicle);
        return vehicle;
    }

    protected static Vehicle findVehicle(Set<Vehicle> vehicles, Vehicle vehicle) {
        Vehicle v = null;
        if (vehicles.contains(vehicle)) {
            v = vehicle;
        }
        return v;
    }

}

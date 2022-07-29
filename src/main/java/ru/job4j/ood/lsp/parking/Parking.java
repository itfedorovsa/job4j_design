package ru.job4j.ood.lsp.parking;

import java.util.Set;

public interface Parking {
    boolean park(Vehicle vehicle);

    Set<Vehicle> getParkedCars();

    Set<Vehicle> getParkedTrucks();

    Vehicle getVehicle(Vehicle vehicle);

}

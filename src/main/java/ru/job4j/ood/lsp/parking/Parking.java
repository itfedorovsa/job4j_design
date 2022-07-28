package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {
    boolean park(Vehicle vehicle);

    List<Vehicle> getParkedVehicles();

    Vehicle getVehicle(Vehicle vehicle);

}

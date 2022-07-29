package ru.job4j.ood.lsp.parking;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleParkingTest {
    SimpleParking parking;

    @Test
    public void whenCarParks() {
        parking = new SimpleParking(1, 1);
        Car car = new Car("Car", "CCC333");
        parking.park(car);
        Set<Vehicle> expected = parking.getParkedCars();
        assertThat(expected, is(Set.of(car)));
    }

    @Test
    public void whenAnyTruckParksInTruckLot() {
        parking = new SimpleParking(1, 1);
        Truck truck = new Truck("Truck", 2, "TTT333");
        parking.park(truck);
        Set<Vehicle> expected = parking.getParkedTrucks();
        assertThat(expected, is(Set.of(truck)));
    }

    @Test
    public void whenTwoCarsParksButOnlyOnePlace() {
        parking = new SimpleParking(1, 1);
        Car car1 = new Car("Car", "CCC333");
        Car car2 = new Car("Car", "CCC111");
        parking.park(car1);
        assertFalse(parking.park(car2));
    }

    @Test
    public void whenTwoTrucksParksButOnlyOnePlace() {
        parking = new SimpleParking(1, 1);
        Truck truck1 = new Truck("Truck", 2, "TTT333");
        Truck truck2 = new Truck("Truck", 2, "TTT111");
        parking.park(truck1);
        assertFalse(parking.park(truck2));
    }

    @Test
    public void whenTwoTrucksParksInTruckLotAndSeveralCarLot() {
        parking = new SimpleParking(2, 1);
        Truck truck1 = new Truck("Truck", 2, "TTT111");
        Truck truck2 = new Truck("Truck", 2, "TTT333");
        parking.park(truck1);
        parking.park(truck2);
        Set<Vehicle> expected = parking.getParkedTrucks();
        assertThat(expected, is(Set.of(truck1, truck2)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenTruckHaveCarSizeThenIAE() {
        parking = new SimpleParking(1, 1);
        parking.park(new Truck("Truck", 1, "TTT333"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenZeroTruckSizeThenIAE() {
        parking = new SimpleParking(1, 1);
        parking.park(new Truck("Truck", 0, "TTT333"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNegativeTruckSizeThenIAE() {
        parking = new SimpleParking(1, 1);
        parking.park(new Truck("Truck", -1, "TTT333"));
    }

    @Test
    public void whenTryingToParkIdenticalVehicle() {
        parking = new SimpleParking(1, 1);
        Truck truck1 = new Truck("Truck", 2, "TTT111");
        Truck truck2 = new Truck("Truck", 2, "TTT111");
        parking.park(truck1);
        assertFalse(parking.park(truck2));
    }
}
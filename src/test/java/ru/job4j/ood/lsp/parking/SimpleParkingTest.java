package ru.job4j.ood.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleParkingTest {
    SimpleParking parking;

    @Ignore
    @Test
    public void whenCarParks() {
        parking = new SimpleParking(1, 1);
        parking.park(new Car(Car.CAR_TYPE, Car.CAR_SIZE, "CCC333"));
        List<Vehicle> expected = parking.getParkedVehicles();
        assertThat(expected.get(0).getLicensePlate(), is("CCC333"));
    }

    @Ignore
    @Test
    public void whenAnyTruckParksInTruckLot() {
        parking = new SimpleParking(1, 1);
        parking.park(new Truck(Truck.TRUCK_TYPE, Truck.TRUCK_SIZE, "TTT333"));
        List<Vehicle> expected = parking.getParkedVehicles();
        assertThat(expected.get(0).getLicensePlate(), is("TTT333"));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenTwoCarsParksButOnlyOnePlace() {
        parking = new SimpleParking(1, 1);
        parking.park(new Car(Car.CAR_TYPE, Car.CAR_SIZE, "CCC333"));
        parking.park(new Car(Car.CAR_TYPE, Car.CAR_SIZE, "CCC111"));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenTwoTrucksParksButOnlyOnePlace() {
        parking = new SimpleParking(1, 1);
        parking.park(new Truck(Truck.TRUCK_TYPE, Truck.TRUCK_SIZE, "TTT333"));
        parking.park(new Truck(Truck.TRUCK_TYPE, Truck.TRUCK_SIZE, "TTT111"));
    }

    @Ignore
    @Test
    public void whenTwoTrucksParksInTruckLotAndSeveralCarLot() {
        parking = new SimpleParking(2, 1);
        Truck truck1 = new Truck(Truck.TRUCK_TYPE, Truck.TRUCK_SIZE, "TTT111");
        Truck truck2 = new Truck(Truck.TRUCK_TYPE, Truck.TRUCK_SIZE, "TTT333");
        parking.park(truck1);
        parking.park(truck2);
        List<Vehicle> result = parking.getParkedVehicles();
        List<Vehicle> expected = List.of(truck1, truck2);
        assertThat(expected, is(result));
    }
}
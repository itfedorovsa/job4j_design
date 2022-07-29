package ru.job4j.ood.dip.dipfailuredemo;

import java.util.*;

public class Shop {
    Map<String, Car> carStorage = new HashMap<>();
    Set<Car> selledCars = new HashSet<>();

    public boolean sell(Car car) {
        if (car.getId() < 0)  {
            System.out.println("This car is not exist!");
            throw new IllegalArgumentException("Car id must be more than 0");

        }
        boolean selled = selledCars.add(car);
        if (selled) {
            carStorage.remove(car.getLicensePlate());
        }
        return selled;
    }
}

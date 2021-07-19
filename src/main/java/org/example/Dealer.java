package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private static Dealer instance = null;

    protected String carModel;
    protected Seller seller = new Seller(this);
    protected List<Car> cars = new ArrayList<>(5);

    private Dealer(String carModel, int carsInStock) {

        this.carModel = carModel;
        for (int i = 0; i < carsInStock; i++) {
            cars.add(new Car());
        }
    }

    public static Dealer get(String name, int carsInStock) {
        if (instance == null) instance = new Dealer(name, carsInStock);
        return instance;
    }

    public String getCarModel() {
        return carModel;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void acceptToStock() {
        seller.receiveCar();
    }

    public Car transferToBuyer() {
        return seller.sellCar();
    }
}

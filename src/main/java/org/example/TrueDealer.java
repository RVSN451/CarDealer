package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TrueDealer {


    private static TrueDealer instance = null;

    protected String carModel;
    protected TrueSeller trueSeller = new TrueSeller((this), new ReentrantLock(true));
    protected List<Car> cars = new ArrayList<>(5);

    private TrueDealer(String carModel, int carsInStock) {

        this.carModel = carModel;
        for (int i = 0; i < carsInStock; i++) {
            cars.add(new Car());
        }
    }

    public static TrueDealer get(String name, int carsInStock) {
        if (instance == null) instance = new TrueDealer(name, carsInStock);
        return instance;
    }

    public String getCarModel() {
        return carModel;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void acceptToStock() {
        trueSeller.receiveCar();
    }

    public Car transferToBuyer() {
        return trueSeller.sellCar();
    }

}

package krzysztof.studio.service;

import krzysztof.studio.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService implements CarOperations {

    private List<Car> cars = new ArrayList();

    @Override
    public List<Car> read() {
        return cars;
    }

    @Override
    public Car read(String vin) {
        if(vin != null) {
            for(Car c : cars) {
                if(c.getVin().equals(vin)) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public void create(Car car) {
        if(car.getVin() != null && !exists(car)) {
            cars.add(car);
        }
    }

    @Override
    public void delete(String vin) {
        if(vin != null) {
            cars.removeIf(t -> t.getVin().equals(vin));
        }
    }

    @Override
    public void update(String vin, Car car) {
        int position = 0;
        if(vin != null) {
            for(Car element : cars) {
                if(element.getVin().equals(vin)) {
                    car.setVin(vin);
                    cars.set(position, car);
                    return;
                }
                position++;
            }
        }
    }

    public boolean exists(Car car) {
        return read(car.getVin()) != null;
    }
}
package krzysztof.studio.car;

import krzysztof.studio.model.Car;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class CarService implements CarOperations {

    private List<Car> cars = new ArrayList();

    @Override
    public List<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getCarByVin(String vin) {
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
    public void createCar(Car car) {
        if(car.getVin() != null && !exists(car)) {
            cars.add(car);
        }
    }

    @Override
    public void deleteCar(String vin) {
        if(vin != null) {
            cars.removeIf(t -> t.getVin().equals(vin));
        }
    }

    @Override
    public void updateCar(String vin, Car car) {
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
        return getCarByVin(car.getVin()) != null;
    }
}
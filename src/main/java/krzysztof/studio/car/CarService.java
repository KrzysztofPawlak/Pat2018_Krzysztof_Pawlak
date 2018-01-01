package krzysztof.studio.car;

import krzysztof.studio.model.Car;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private List<Car> cars = new ArrayList();

    public List<Car> getAllCars() {
        return cars;
    }

    public Car getCarByVin(@PathVariable String vin) {
        if(vin != null) {
            for(Car c : cars) {
                if(c.getVin().equals(vin)) {
                    return c;
                }
            }
        }
        return null;
    }

    public void addCar(Car car) {
        if(car.getVin() != null && !exists(car)) {
            cars.add(car);
        }
    }

    public void deleteCar(String vin) {
        if(vin != null) {
            cars.removeIf(t -> t.getVin().equals(vin));
        }
    }

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
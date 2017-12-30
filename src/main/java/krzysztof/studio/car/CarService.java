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

    public Car getCar(@PathVariable String vin) {
        return cars.stream().filter(t -> t.getVin().equals(vin)).findFirst().get();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void deleteCar(String vin) {
        cars.removeIf(t -> t.getVin().equals(vin));
    }

    public void updateCar(String vin, Car car) {
        int position = 0;
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
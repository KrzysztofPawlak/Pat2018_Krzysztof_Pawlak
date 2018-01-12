package krzysztof.studio.car;

import krzysztof.studio.model.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CarServiceH2 implements CarOperations {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }

    @Override
    public Car getCarByVin(String vin) {
        return carRepository.findOne(vin);
    }

    @Override
    public void createCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void deleteCar(String vin) {
        carRepository.delete(vin);
    }

    @Override
    public void updateCar(String vin, Car car) {
        carRepository.save(car);
    }
}
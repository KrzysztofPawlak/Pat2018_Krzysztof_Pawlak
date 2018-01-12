package krzysztof.studio.car;

import krzysztof.studio.model.Car;

import java.util.List;

public interface CarOperations {

    List<Car> getAllCars();
    Car getCarByVin(String vin);
    void createCar(Car car);
    void deleteCar(String vin);
    void updateCar(String vin, Car car);
}
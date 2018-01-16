package krzysztof.studio.car;

import krzysztof.studio.model.Car;

import java.util.List;

public interface CarOperations {

    List<Car> getAllCars();
    Car getCarByVin(String vin) throws Exception;
    void createCar(Car car) throws Exception;
    void deleteCar(String vin) throws Exception;
    void updateCar(String vin, Car car) throws Exception;
}
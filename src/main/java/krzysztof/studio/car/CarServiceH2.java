package krzysztof.studio.car;

import krzysztof.studio.exceptions.component.ExceptionThrower;
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
    public Car getCarByVin(String vin) throws Exception {

        Car car = carRepository.findOne(vin);

        if (car == null) {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwNotFoundException();
        }

        return car;
    }

    @Override
    public void createCar(Car car) throws Exception {
        if(carRepository.findOne(car.getVin()) == null) {
            carRepository.save(car);
        } else {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwAlreadyExistException();
        }
    }

    @Override
    public void deleteCar(String vin) throws Exception {
        if(carRepository.findOne(vin) != null) {
            carRepository.delete(vin);
        } else {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwNotFoundException();
        }
    }

    @Override
    public void updateCar(String vin, Car car) throws Exception {
        if(carRepository.findOne(vin) != null) {
            carRepository.save(car);
        } else {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwNotFoundException();
        }
    }

    public boolean exists(Car car) throws Exception {
        return getCarByVin(car.getVin()) != null;
    }
}
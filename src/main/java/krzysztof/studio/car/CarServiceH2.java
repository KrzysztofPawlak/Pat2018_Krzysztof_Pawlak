package krzysztof.studio.car;

import krzysztof.studio.exceptions.component.ExceptionThrower;
import krzysztof.studio.model.Car;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
public class CarServiceH2 implements CarOperations {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> read() throws Exception {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);

        if(cars.isEmpty()) {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwNotFoundException();
        }

        return cars;
    }

    @Override
    public Car read(String vin) throws Exception {

        Car car = carRepository.findOne(vin);

        if (car == null) {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwNotFoundException();
        }

        return car;
    }

    @Override
    public void create(Car car) throws Exception {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        if(carRepository.findOne(car.getVin()) == null) {
            carRepository.save(car);
        } else {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwAlreadyExistException();
        }
    }

    @Override
    public void delete(String vin) throws Exception {
        if(carRepository.findOne(vin) != null) {
            carRepository.delete(vin);
        } else {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwNotFoundException();
        }
    }

    @Override
    public void update(String vin, Car car) throws Exception {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        if(read(vin) != null) {
            Car currentCar = carRepository.findOne(vin);
            currentCar.setDateOfFirstRegistration(car.getDateOfFirstRegistration());
            currentCar.setDateOfRegistration(car.getDateOfRegistration());
            currentCar.setCylinderCapacity(car.getCylinderCapacity());
            currentCar.setRegistrationNumber(car.getRegistrationNumber());
            currentCar.setMake(car.getMake());
            currentCar.setModel(car.getModel());
            currentCar.setNrOfSeats(car.getNrOfSeats());
        } else {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwNotFoundException();
        }
    }

    public boolean exists(Car car) throws Exception {
        return read(car.getVin()) != null;
    }
}
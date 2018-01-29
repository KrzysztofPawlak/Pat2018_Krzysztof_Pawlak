package krzysztof.studio.car;

import krzysztof.studio.model.Car;

import java.util.List;

public interface CarOperations {

    List<Car> read() throws Exception;
    Car read(String vin) throws Exception;
    void create(Car car) throws Exception;
    void delete(String vin) throws Exception;
    void update(String vin, Car car) throws Exception;
}
package krzysztof.studio.krzysztof.studio.car;

import krzysztof.studio.krzysztof.studio.model.Car;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    UUID uuid;
    private List<Car> cars = Arrays.asList(
            new Car(uuid.randomUUID().toString(), "BMW", "X2", 2009, "WE984KV"),
            new Car(uuid.randomUUID().toString(), "Honda", "HR-V", 2016, "AX214H1"),
            new Car(uuid.randomUUID().toString(), "Volkswagen", "Polo", 2014, "QR671CB")
    );

    public List<Car> getAllCars() {
        return cars;
    }

    public Car getCar(@PathVariable String id) {
        return cars.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }
}
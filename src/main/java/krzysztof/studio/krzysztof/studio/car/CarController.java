package krzysztof.studio.krzysztof.studio.car;

import krzysztof.studio.krzysztof.studio.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping("/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @RequestMapping("/cars/{id}")
    public Car getCar(@PathVariable String id) {
        return carService.getCar(id);
    }
}
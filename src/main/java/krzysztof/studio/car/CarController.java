package krzysztof.studio.car;

import krzysztof.studio.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping("/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @RequestMapping("/cars/{vin}")
    public Car getCarByVin(@PathVariable String vin) {
        return carService.getCarByVin(vin);
    }

    @RequestMapping(method = RequestMethod.POST, value="/cars")
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/cars/{vin}")
    public void deleteCar(@PathVariable String vin) {
        carService.deleteCar(vin);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/cars/{vin}")
    public void updateCar(@PathVariable String vin, @RequestBody Car car) {
        carService.updateCar(vin, car);
    }
}
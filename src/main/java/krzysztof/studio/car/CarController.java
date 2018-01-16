package krzysztof.studio.car;

import krzysztof.studio.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
public class CarController {

    @Autowired
    private CarOperations carService;

    @RequestMapping(method = RequestMethod.GET, value="/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @RequestMapping(method = RequestMethod.GET, value="/cars/{vin}")
    public Car getCarByVin(@PathVariable String vin) throws Exception {
        return carService.getCarByVin(vin);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value="/cars")
    public void createCar(@Valid @RequestBody Car car) throws Exception {
        carService.createCar(car);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/cars/{vin}")
    public void deleteCar(@PathVariable String vin) throws Exception {
        carService.deleteCar(vin);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/cars/{vin}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCar(@PathVariable String vin, @Valid @RequestBody Car car) throws Exception {
        carService.updateCar(vin, car);
    }
}
package krzysztof.studio.car;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import krzysztof.studio.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@Api(value = "car", description = "Rest API dla operacji na pojazdach", tags = "Car API")
public class CarController {

    @Autowired
    private CarOperations carService;

    @ApiOperation(value = "Wyświetla wszystkie pojazdy znajdujące się w bazie komisu.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sukces")
    })
    @RequestMapping(method = RequestMethod.GET, value="/cars", produces = { "application/json", "application/xml" })
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sukces")
    })
    @ApiOperation(value = "Wyświetla dane pojedyńczego pojazdu na podstawie numeru identyfikacyjnego.")
    @RequestMapping(method = RequestMethod.GET, value="/cars/{vin}")
    public Car getCarByVin(@PathVariable String vin) throws Exception {
        return carService.getCarByVin(vin);
    }

    @ApiOperation(value = "Dodaje nowy pojazd do bazy komisu.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value="/cars")
    public void createCar(@Valid @RequestBody Car car) throws Exception {
        carService.createCar(car);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sukces")
    })
    @ApiOperation(value = "Usuwa pojazd z bazy komisu.")
    @RequestMapping(method=RequestMethod.DELETE, value="/cars/{vin}")
    public void deleteCar(@PathVariable String vin) throws Exception {
        carService.deleteCar(vin);
    }

    @ApiOperation(value = "Aktualizuje dane pojazdu z bazy komisu.")
    @RequestMapping(method=RequestMethod.PUT, value="/cars/{vin}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCar(@PathVariable String vin, @Valid @RequestBody Car car) throws Exception {
        carService.updateCar(vin, car);
    }
}
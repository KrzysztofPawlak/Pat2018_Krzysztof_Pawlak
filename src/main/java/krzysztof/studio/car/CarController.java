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

import java.util.List;

@Validated
@RestController
@Api(value = "car", description = "Rest API dla operacji na pojazdach", tags = "Car API")
public class CarController {

    @Autowired
    private CarOperations carService;

    @ApiOperation(value = "Wyświetla wszystkie pojazdy znajdujące się w bazie komisu.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sukces"),
            @ApiResponse(code = 404, message = "nie znaleziono")
    })
    @RequestMapping(method = RequestMethod.GET, value="/cars", produces = { "application/json", "application/xml" })
    public List<Car> read() throws Exception {
        return carService.read();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sukces"),
            @ApiResponse(code = 404, message = "nie znaleziono")
    })
    @ApiOperation(value = "Wyświetla dane pojedyńczego pojazdu na podstawie numeru identyfikacyjnego.")
    @RequestMapping(method = RequestMethod.GET, value="/cars/{vin}")
    public Car read(@PathVariable String vin) throws Exception {
        return carService.read(vin);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "utworzono"),
            @ApiResponse(code = 404, message = "nie znaleziono"),
            @ApiResponse(code = 422, message = "błąd walidacji danych wejściowych")
    })
    @ApiOperation(value = "Dodaje nowy pojazd do bazy komisu.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value="/cars")
    public void create(@RequestBody Car car) throws Exception {
        carService.create(car);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "usunieto pomyślnie"),
            @ApiResponse(code = 404, message = "nie znaleziono")
    })
    @ApiOperation(value = "Usuwa pojazd z bazy komisu.")
    @RequestMapping(method=RequestMethod.DELETE, value="/cars/{vin}")
    public void delete(@PathVariable String vin) throws Exception {
        carService.delete(vin);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "usuwanie zakończone sukcesem"),
            @ApiResponse(code = 404, message = "nie znaleziono"),
            @ApiResponse(code = 422, message = "błąd walidacji danych wejściowych")
    })
    @ApiOperation(value = "Aktualizuje dane pojazdu z bazy komisu.")
    @RequestMapping(method=RequestMethod.PUT, value="/cars/{vin}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String vin, @RequestBody Car car) throws Exception {
        carService.update(vin, car);
    }
}
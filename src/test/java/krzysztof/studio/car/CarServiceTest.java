package krzysztof.studio.car;

import krzysztof.studio.model.Car;
import krzysztof.studio.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import java.util.UUID;

public class CarServiceTest {

    private CarService carService;
    private UUID uuid;
    private String vin;

    @Mock
    private CarRepository carRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        carService = new CarService();
        vin = uuid.randomUUID().toString();

        Customer customer = new Customer();
        Car car = new Car(vin, "BMW", "X2");
        carService.create(car);
    }

    @Test
    public void testGetCar() {
        Car car = carService.read(vin);
        assertEquals(car.getVin(), vin);
    }

}
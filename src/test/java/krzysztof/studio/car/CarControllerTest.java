package krzysztof.studio.car;

import krzysztof.studio.model.Car;
import krzysztof.studio.model.Customer;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarControllerTest {

    private MockMvc mockMvc;

    private UUID uuid;
    private String vin;
    private List<Car> cars;

    @InjectMocks
    private CarController carController;

    @Mock
    private CarService carService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();

        vin = uuid.randomUUID().toString();

        cars = new ArrayList<>();
        Customer customer = new Customer();
        Car car = new Car(vin, "BMW", "X2", 2009);
        car.setCustomer(customer);
        cars.add(car);
    }

    @Test
    public void testGetAllCar() throws Exception {
        when(carService.getAllCars()).thenReturn(cars);
        mockMvc.perform(get("/cars")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].make", Matchers.is("BMW")))
                .andExpect(jsonPath("$[0].model", Matchers.is("X2")));

        verify(carService, times(1)).getAllCars();
        verifyNoMoreInteractions(carService);
    }
}
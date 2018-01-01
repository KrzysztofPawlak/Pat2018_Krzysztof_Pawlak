package krzysztof.studio.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import krzysztof.studio.model.Car;
import krzysztof.studio.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarControllerTest {

    private MockMvc mockMvc;

    private UUID uuid;
    private String vin;
    private List<Car> cars;

    @InjectMocks
    private CarController carControllerMock;

    @Mock
    private CarService carServiceMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carControllerMock).build();

        vin = uuid.randomUUID().toString();

        cars = new ArrayList<>();
        Customer customer = new Customer();
        Car car = new Car(vin, "BMW", "X2", 2009);
        car.setCustomer(customer);
        cars.add(car);
    }

    private static String asJsonString(final Car obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllCar() throws Exception {
        when(carServiceMock.getAllCars()).thenReturn(cars);
        mockMvc.perform(get("/cars")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].make", is("BMW")))
                .andExpect(jsonPath("$[0].model", is("X2")));

        verify(carServiceMock, times(1)).getAllCars();
        verifyNoMoreInteractions(carServiceMock);
    }

    @Test
    public void findCarByVinNotFound() throws Exception {
        String notexistvin = "notexistvin";
        when(carServiceMock.getCarByVin(notexistvin)).thenReturn(null);
        mockMvc.perform(get("/cars/{vin}", notexistvin))
                .andExpect(status().isNotFound());

        verify(carServiceMock, times(1)).getCarByVin(notexistvin);
        verifyNoMoreInteractions(carServiceMock);
    }

    @Test
    public void findCarByVinFound() throws Exception {
        vin = uuid.randomUUID().toString();
        Car car = new Car(vin, "BMW", "X2", 2009);
        when(carServiceMock.getCarByVin(vin)).thenReturn(car);
        mockMvc.perform(get("/cars/{vin}", vin))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.vin", is(vin)))
                .andExpect(jsonPath("$.make", is("BMW")))
                .andExpect(jsonPath("$.model", is("X2")));
        verify(carServiceMock, times(1)).getCarByVin(vin);
        verifyNoMoreInteractions(carServiceMock);
    }

    @Test
    public void testCreateCar() throws Exception {
        vin = uuid.randomUUID().toString();
        Car car = new Car(vin, "BMW", "X2", 2009);
        System.out.println(asJsonString(car));
        when(carServiceMock.exists(car)).thenReturn(false);
        doNothing().when(carServiceMock).addCar(car);

        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(car)))
                .andExpect(status().isCreated()); // spring framework status headers required to fill this

        verify(carServiceMock, times(1)).exists(car);
        verify(carServiceMock, times(1)).addCar(car);
        verifyNoMoreInteractions(carServiceMock);
    }

    @Test
    public void testUpdateCar() throws Exception {
        vin = uuid.randomUUID().toString();
        Car car = new Car(vin, "BMW", "X7", 2011);

        when(carServiceMock.getCarByVin(vin)).thenReturn(car);
        doNothing().when(carServiceMock).updateCar(vin, car);

        mockMvc.perform(put("/cars/{vin}", vin)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(car)))
                .andExpect(status().isOk());
        verify(carServiceMock, times(1)).updateCar(eq(vin), refEq(car));
        verifyNoMoreInteractions(carServiceMock);
    }

    @Test
    public void testDeleteCar() throws Exception {
        vin = uuid.randomUUID().toString();
        Car car = new Car(vin, "BMW", "X7", 2011);
        when(carServiceMock.getCarByVin(vin)).thenReturn(car);
        doNothing().when(carServiceMock).deleteCar(vin);
        mockMvc.perform(delete("/cars/{vin}", vin))
                .andExpect(status().isOk());
        verify(carServiceMock, times(1)).deleteCar(vin);
        verifyNoMoreInteractions(carServiceMock);
    }
}
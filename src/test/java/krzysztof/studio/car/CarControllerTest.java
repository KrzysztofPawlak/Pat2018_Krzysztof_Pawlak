package krzysztof.studio.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import krzysztof.studio.controller.CarController;
import krzysztof.studio.model.Car;
import krzysztof.studio.service.CarServiceH2;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarControllerTest {

    private MockMvc mockMvc;

    private UUID uuid;
    private String vin;
    private List<Car> cars;
    DateFormat format;

    @InjectMocks
    private CarController carControllerMock;

    @Mock
    private CarServiceH2 carServiceMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carControllerMock).build();

        vin = uuid.randomUUID().toString();

        format = new SimpleDateFormat("yyyy-MM-dd");

        cars = new ArrayList<>();
        Car car = new Car(vin, "BMW", "X2");
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
        when(carServiceMock.read()).thenReturn(cars);
        mockMvc.perform(get("/cars")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].make", is("BMW")))
                .andExpect(jsonPath("$[0].model", is("X2")));

        verify(carServiceMock, times(1)).read();
        verifyNoMoreInteractions(carServiceMock);
    }

    @Test
    public void findCarByVinNotFound() throws Exception {
        String notexistvin = "notexistvin";
        when(carServiceMock.read(notexistvin)).thenReturn(null);
        mockMvc.perform(get("/cars/{vin}", notexistvin)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyString()));
        verify(carServiceMock, times(1)).read(notexistvin);
        verifyNoMoreInteractions(carServiceMock);
    }

    @Test
    public void findCarByVinFound() throws Exception {
        vin = uuid.randomUUID().toString();
        Car car = new Car(vin, "BMW", "X2");
        when(carServiceMock.read(vin)).thenReturn(car);
        mockMvc.perform(get("/cars/{vin}", vin)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vin", is(vin)))
                .andExpect(jsonPath("$.make", is("BMW")))
                .andExpect(jsonPath("$.model", is("X2")));
        verify(carServiceMock, times(1)).read(vin);
        verifyNoMoreInteractions(carServiceMock);
    }

    @Test
    public void testCreateCarValid() throws Exception {
        vin = uuid.randomUUID().toString();
        Car car = new Car(vin, "HONDA", "X2");
        car.setRegistrationNumber("XH10221821");
        car.setNrOfSeats(5);
        car.setCylinderCapacity(1000);

        Date registrationDate = format.parse("1970-12-12");
        Date firstRegistrationDate = format.parse("1970-12-12");
        car.setDateOfRegistration(registrationDate);
        car.setDateOfFirstRegistration(firstRegistrationDate);

        when(carServiceMock.exists(car)).thenReturn(false);
        doNothing().when(carServiceMock).create(car);

        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(car)))
                .andExpect(status().isCreated()); // spring framework status headers required to fill this

        verify(carServiceMock, times(1)).create(refEq(car));
        verifyNoMoreInteractions(carServiceMock);
    }

    @Test
    public void testUpdateCar() throws Exception {
        vin = uuid.randomUUID().toString();
        Car car = new Car(vin, "FIAT", "X7");
        car.setRegistrationNumber("XH10221821");
        car.setNrOfSeats(5);
        car.setCylinderCapacity(1000);

        Date registrationDate = format.parse("1970-12-12");
        Date firstRegistrationDate = format.parse("1970-12-12");
        car.setDateOfRegistration(registrationDate);
        car.setDateOfFirstRegistration(firstRegistrationDate);

        when(carServiceMock.read(vin)).thenReturn(car);
        doNothing().when(carServiceMock).update(vin, car);

        mockMvc.perform(put("/cars/{vin}", vin)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(car)))
                .andExpect(status().isNoContent());
        verify(carServiceMock, times(1)).update(eq(vin), refEq(car));
        verifyNoMoreInteractions(carServiceMock);
    }

    @Test
    public void testDeleteCar() throws Exception {
        vin = uuid.randomUUID().toString();
        Car car = new Car(vin, "BMW", "X7");
        when(carServiceMock.read(vin)).thenReturn(car);
        doNothing().when(carServiceMock).delete(vin);
        mockMvc.perform(delete("/cars/{vin}", vin))
                .andExpect(status().isNoContent());
        verify(carServiceMock, times(1)).delete(vin);
        verifyNoMoreInteractions(carServiceMock);
    }

    @Test
    public void testContentNegotationXml() throws Exception {
        when(carServiceMock.read()).thenReturn(cars);
        mockMvc.perform(get("/cars")
                .accept(MediaType.APPLICATION_XML))
                .andExpect(content().contentType("application/xml;charset=UTF-8"));
    }

    @Test
    public void testContentNegotationJson() throws Exception {
        when(carServiceMock.read()).thenReturn(cars);
        mockMvc.perform(get("/cars")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }
}
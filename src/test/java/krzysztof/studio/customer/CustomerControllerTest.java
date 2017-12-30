package krzysztof.studio.customer;

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
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    private String vin;
    private UUID uuid;
    private ArrayList<Customer> customers;

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        vin = uuid.randomUUID().toString();

        customers = new ArrayList<>();
        Customer customer = new Customer("58050503349", "Marian", "Dzik", "18-06-1999", Customer.Sex.male);
        Car car1 = new Car(vin, "BMW", "X2", 2009);
        Car car2 = new Car(vin, "Fiat", "126p", 1970);
        customer.setCars(new ArrayList<Car>() {{
            add(car1);
            add(car2);
        }});
        customers.add(customer);
    }

    @Test
    public void testGetAllCar() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(customers);
        mockMvc.perform(get("/customers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].pesel", Matchers.is("58050503349")));

        verify(customerService, times(1)).getAllCustomers();
        verifyNoMoreInteractions(customerService);
    }
}
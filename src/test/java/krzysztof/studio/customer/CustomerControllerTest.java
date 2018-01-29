package krzysztof.studio.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    private String vin;
    private UUID uuid;
    private ArrayList<Customer> customers;
    private Customer customer;
    private String examplePesel;
    private static final int CUSTOMER_NUMBER_CARS = 2;

    @InjectMocks
    private CustomerController customerControllerMock;

    @Mock
    private CustomerService customerServiceMock;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerControllerMock).build();

        vin = uuid.randomUUID().toString();
        examplePesel = "58050503349";
        customers = new ArrayList<>();

        customer = new Customer(examplePesel, "Marian", "Dzik", "18-06-1999", Customer.Sex.male);
        Car car1 = new Car(vin, "BMW", "X2");
        Car car2 = new Car(vin, "Fiat", "126p");
        customer.setCars(new ArrayList<Car>() {{
            add(car1);
            add(car2);
        }});
        customers.add(customer);
    }

    private static String asJsonString(final Customer obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllCustomer() throws Exception {
        when(customerServiceMock.getAllCustomers()).thenReturn(customers);
        mockMvc.perform(get("/customers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].pesel", Matchers.is("58050503349")));

        verify(customerServiceMock, times(1)).getAllCustomers();
        verifyNoMoreInteractions(customerServiceMock);
    }

    @Test
    public void testCustomerWithPeselExists() throws Exception {
        when(customerServiceMock.getCustomer(examplePesel)).thenReturn(customer);
        mockMvc.perform(get("/customers/{pesel}", examplePesel)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.pesel", is(examplePesel)));
        verify(customerServiceMock, times(1)).getCustomer(examplePesel);
        verifyNoMoreInteractions(customerServiceMock);
    }

    @Test
    public void testCustomerHasTwoCars() throws Exception {
        when(customerServiceMock.getCustomer(examplePesel)).thenReturn(customer);
        mockMvc.perform(get("/customers/{pesel}", examplePesel)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cars", hasSize(CUSTOMER_NUMBER_CARS)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
        verify(customerServiceMock, times(1)).getCustomer(examplePesel);
        verifyNoMoreInteractions(customerServiceMock);
    }
}
package krzysztof.studio.customer;

import krzysztof.studio.model.Car;
import krzysztof.studio.model.Customer;
import krzysztof.studio.service.CustomerService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CustomerServiceTest {

    private CustomerService customerService;
    private UUID uuid;
    private String vin;
    private static final int CUSTOMER_NUMBER_CARS = 2;

    @Before
    public void setup() {
        customerService = new CustomerService();

        vin = uuid.randomUUID().toString();

        Customer customer = new Customer("58050503349", "Marian", "Dzik", "18-06-1999", Customer.Sex.male);
        Car car1 = new Car(vin, "BMW", "X2");
        Car car2 = new Car(vin, "Fiat", "126p");
        customer.setCars(new ArrayList<Car>() {{
            add(car1);
            add(car2);
        }});
        customerService.createCustomer(customer);
    }

    @Test
    public void testGetCustomerCars() {
        Customer customer = customerService.getCustomer("58050503349");
        assertEquals(customer.getCars().size(), CUSTOMER_NUMBER_CARS);
    }

}
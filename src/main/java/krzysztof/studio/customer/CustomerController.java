package krzysztof.studio.customer;

import krzysztof.studio.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerOperations customerService;

    @RequestMapping(method = RequestMethod.GET, value="/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(method = RequestMethod.GET, value="/customers/{pesel}")
    public Customer getCustomer(@PathVariable String pesel) throws Exception {
        return customerService.getCustomer(pesel);
    }

    @RequestMapping(method = RequestMethod.POST, value="/customers")
    public void createCustomer(@RequestBody Customer customer) throws Exception {
        customerService.createCustomer(customer);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/customers/{pesel}")
    public void deleteCustomer(@PathVariable String pesel) throws Exception {
        customerService.deleteCustomer(pesel);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/customers/{pesel}")
    public void updateCustomer(@RequestBody Customer customer, @PathVariable String pesel) throws Exception {
        customerService.updateCustomer(pesel, customer);
    }
}
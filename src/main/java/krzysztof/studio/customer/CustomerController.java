package krzysztof.studio.customer;

import krzysztof.studio.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping("/customers/{pesel}")
    public Customer getCustomer(@PathVariable String pesel) {
        return customerService.getCustomer(pesel);
    }

    @RequestMapping(method = RequestMethod.POST, value="/customers")
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/customers/{pesel}")
    public void deleteCustomer(@PathVariable String pesel) {
        customerService.deleteCustomer(pesel);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/customers/{pesel}")
    public void updateCustomer(@RequestBody Customer customer, @PathVariable String pesel) {
        customerService.updateCustomer(pesel, customer);
    }
}
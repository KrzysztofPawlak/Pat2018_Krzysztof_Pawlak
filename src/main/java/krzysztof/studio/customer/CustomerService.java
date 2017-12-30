package krzysztof.studio.customer;

import krzysztof.studio.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customers = new ArrayList();

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomer(@PathVariable String id) {
        return customers.stream().filter(t -> t.getPesel().equals(id)).findFirst().get();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void deleteCustomer(String pesel) {
        customers.removeIf(t -> t.getPesel().equals(pesel));
    }

    public void updateCustomer(String pesel, Customer customer) {
        int position = 0;
        for(Customer element : customers) {
            if(element.getPesel().equals(pesel)) {
                customer.setPesel(pesel);
                customers.set(position, customer);
                return;
            }
            position++;
        }
    }
}
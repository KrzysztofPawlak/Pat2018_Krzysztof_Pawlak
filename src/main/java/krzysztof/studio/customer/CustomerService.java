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
        if(id != null) {
            for(Customer c : customers) {
                if(c.getPesel().equals(id)) {
                    return c;
                }
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        if(customer.getPesel() != null && !exists(customer)) {
            customers.add(customer);
        }
    }

    public void deleteCustomer(String pesel) {
        if(pesel != null) {
            customers.removeIf(t -> t.getPesel().equals(pesel));
        }
    }

    public void updateCustomer(String pesel, Customer customer) {
        int position = 0;
        if(pesel != null) {
            for (Customer element : customers) {
                if (element.getPesel().equals(pesel)) {
                    customer.setPesel(pesel);
                    customers.set(position, customer);
                    return;
                }
                position++;
            }
        }
    }

    public boolean exists(Customer customer) {
        return getCustomer(customer.getPesel()) != null;
    }
}
package krzysztof.studio.service;

import krzysztof.studio.repository.CustomerRepository;
import krzysztof.studio.util.error.ExceptionThrower;
import krzysztof.studio.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceH2 implements CustomerOperations {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer getCustomer(String pesel) throws Exception {
        Customer customer = customerRepository.findOne(pesel);

        if(customer == null) {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwNotFoundException();
        }

        return customer;
    }

    @Override
    public void createCustomer(Customer customer) throws Exception {
        if(customerRepository.findOne(customer.getPesel()) == null) {
            customerRepository.save(customer);
        } else {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwAlreadyExistException();
        }
    }

    @Override
    public void deleteCustomer(String pesel) throws Exception {
        if(customerRepository.findOne(pesel) != null) {
            customerRepository.delete(pesel);
        } else {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwNotFoundException();
        }
    }

    @Override
    public void updateCustomer(String pesel, Customer customer) throws Exception {
        if(customerRepository.findOne(pesel) != null) {
            customerRepository.save(customer);
        } else {
            ExceptionThrower eT = new ExceptionThrower();
            eT.throwNotFoundException();
        }
    }

    public boolean exist(Customer customer) throws Exception {
        return getCustomer(customer.getPesel()) != null;
    }
}
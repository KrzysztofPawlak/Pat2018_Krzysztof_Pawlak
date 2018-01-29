package krzysztof.studio.service;

import krzysztof.studio.model.Customer;

import java.util.List;

public interface CustomerOperations {
    List<Customer> getAllCustomers();
    Customer getCustomer(String pesel) throws Exception;
    void createCustomer(Customer customer) throws Exception;
    void deleteCustomer(String pesel) throws Exception;
    void updateCustomer(String updateCustomer, Customer customer) throws Exception;
}
package krzysztof.studio.repository;

import krzysztof.studio.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}
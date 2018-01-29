package krzysztof.studio.repository;

import krzysztof.studio.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository <Car, String> {

}
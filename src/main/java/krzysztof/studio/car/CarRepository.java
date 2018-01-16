package krzysztof.studio.car;

import krzysztof.studio.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository <Car, String> {

}
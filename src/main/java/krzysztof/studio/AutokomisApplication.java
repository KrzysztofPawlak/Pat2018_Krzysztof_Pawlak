package krzysztof.studio;

import krzysztof.studio.repository.CarRepository;
import krzysztof.studio.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class AutokomisApplication {

	private static final Logger log = LoggerFactory.getLogger(AutokomisApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AutokomisApplication.class, args);
		log.info("url: " + "http://localhost:8080/v2/api-docs");
	}

	@Bean
	public CommandLineRunner demoData(CarRepository carRepository) {
		Car testCar = new Car();
		testCar.setVin("1HGCR2F37EA264447");
		testCar.setMake("HONDA");
		testCar.setModel("Civic");
		testCar.setRegistrationNumber("HZ10291825");
		testCar.setNrOfSeats(5);
		testCar.setCylinderCapacity(2000);
		testCar.setDateOfFirstRegistration(new Date());
		testCar.setDateOfRegistration(new Date());

		return args -> {
			carRepository.save(testCar);
		};
	}
}

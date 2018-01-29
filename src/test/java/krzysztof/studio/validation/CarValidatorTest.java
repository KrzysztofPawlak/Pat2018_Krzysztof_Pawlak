package krzysztof.studio.validation;

import krzysztof.studio.model.Car;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CarValidatorTest {

    private static Validator validator;
    private Date date;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        date = new GregorianCalendar(1950, 01, 01).getTime();
    }

    @Test
    public void testMakeValidator() {
        Car car = new Car();
        car.setVin("21231");
        car.setCylinderCapacity(2000);
        car.setDateOfRegistration(date);
        car.setDateOfFirstRegistration(date);
        car.setMake("TOYOTA");
        car.setRegistrationNumber("HS12151181");
        car.setModel("126p");

        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertEquals(1, violations.size());
    }

    @Test
    public void testCapacityValidator() {
        Car car = new Car();
        car.setVin("21231");
        car.setCylinderCapacity(5);
        car.setDateOfRegistration(date);
        car.setDateOfFirstRegistration(date);
        car.setMake("HONDA");
        car.setRegistrationNumber("HS12151181");
        car.setModel("126p");

        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertEquals(1, violations.size());
    }

    @Test
    public void testNrOfSeatsValidator() {
        Car car = new Car();
        car.setVin("21231");
        car.setCylinderCapacity(50);
        car.setDateOfRegistration(date);
        car.setDateOfFirstRegistration(date);
        car.setMake("HONDA");
        car.setNrOfSeats(50);
        car.setRegistrationNumber("HS12151181");
        car.setModel("126p");

        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertEquals(1, violations.size());
    }

    @Test
    public void testRegistrationSizeValidator() {
        Car car = new Car();
        car.setVin("21231");
        car.setCylinderCapacity(50);
        car.setDateOfRegistration(date);
        car.setDateOfFirstRegistration(date);
        car.setMake("HONDA");
        car.setRegistrationNumber("HS12151181111111111111111");
        car.setModel("126p");

        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertEquals(1, violations.size());
    }
}
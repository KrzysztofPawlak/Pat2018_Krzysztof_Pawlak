package krzysztof.studio.validation;

import krzysztof.studio.model.Car;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import org.junit.runners.model.TestClass;

import static org.junit.Assert.assertEquals;

public class DateValidatorTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testDateRange() {
        Car test = new Car();

        Date date = new GregorianCalendar(10001, 01, 01).getTime();
        test.setDateOfFirstRegistration(date);

//        Set<ConstraintViolation<TestClass>> validate = validator.validate(test);
//
//        System.out.println(validate);
//        assertEquals(1, validate.size());
//        assertEquals("date", validate.iterator().next().getPropertyPath().toString());
    }

}
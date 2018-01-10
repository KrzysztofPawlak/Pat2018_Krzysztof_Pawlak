package krzysztof.studio.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateValidator implements ConstraintValidator<InDateRange, Date>{

    @Override
    public void initialize(InDateRange inDateRange) {

    }

    @Override
    public boolean isValid(Date localDate, ConstraintValidatorContext constraintValidatorContext) {

        Date now = new Date();
        Date min = new GregorianCalendar(1900, 01, 01).getTime();

        return localDate == null || (localDate.after(min) && localDate.before(now));
    }
}
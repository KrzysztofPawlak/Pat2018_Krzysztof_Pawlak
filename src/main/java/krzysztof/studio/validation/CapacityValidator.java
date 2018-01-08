package krzysztof.studio.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CapacityValidator implements ConstraintValidator<InRange, Integer> {

    private int min;
    private int max;

    @Override
    public void initialize(InRange inRange) {
        this.min = inRange.min();
        this.max = inRange.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || (value >= min && value <= max);
    }
}
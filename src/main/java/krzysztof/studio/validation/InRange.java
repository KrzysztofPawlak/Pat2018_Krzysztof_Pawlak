package krzysztof.studio.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { CapacityValidator.class })
public @interface InRange {
    String message() default "value is out of range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
}
package krzysztof.studio.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { RegistrationNumberValidator.class })
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RegistrationNumber {
    String message() default "characters contains inappropriate characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}